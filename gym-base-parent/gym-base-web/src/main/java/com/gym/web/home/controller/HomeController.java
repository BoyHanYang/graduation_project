package com.gym.web.home.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gym.utils.ResultUtils;
import com.gym.utils.ResultVo;
import com.gym.web.goods_order.entity.GoodsOrder;
import com.gym.web.goods_order.service.GoodsOrderService;
import com.gym.web.home.entity.Echarts;
import com.gym.web.home.entity.EchartsItem;
import com.gym.web.home.entity.ResetPassword;
import com.gym.web.home.entity.TotalCount;
import com.gym.web.material.service.MaterialService;
import com.gym.web.member.entity.Member;
import com.gym.web.member.service.MemberService;
import com.gym.web.suggest.entity.Suggest;
import com.gym.web.suggest.service.SuggestService;
import com.gym.web.sys_user.entity.SysUser;
import com.gym.web.sys_user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("/api/home")
@RestController
public class HomeController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private GoodsOrderService goodsOrderService;
    @Autowired
    private MaterialService materialService;
    @Autowired
    private SuggestService suggestService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //统计总数
    @GetMapping("/getTotal")
    public ResultVo getTotal(){
        TotalCount totalCount = new TotalCount();
        long memberCount = memberService.count();
        totalCount.setMemberCount(memberCount);
        long userCount = sysUserService.count();
        totalCount.setUserCount(userCount);
        long materCount = materialService.count();
        totalCount.setMaterCount(materCount);
        QueryWrapper<GoodsOrder> query = new QueryWrapper<>();
        // select * from goods_order where to_days(now())-to_days(create_time)=1
        query.lambda().apply(true, "TO_DAYS(NOW())-TO_DAYS(create_time) = 1");
        long orderCount = goodsOrderService.count(query);
        totalCount.setOrderCount(orderCount);
        return ResultUtils.success("查询成功",totalCount);
    }

    @GetMapping("/getSuggestList")
    public ResultVo getSuggestList(){
        QueryWrapper<Suggest> query = new QueryWrapper<>();
        query.lambda().orderByDesc(Suggest::getDateTime).last(" limit 3");
        List<Suggest> list = suggestService.list(query);
        return ResultUtils.success("查询成功",list);
    }
    //热销商品
    @GetMapping("/getHotGoods")
    public ResultVo getHotGoods(){
        List<EchartsItem> echartItems = goodsOrderService.hotGoods();
        Echarts echart = new Echarts();
        if(echartItems.size() >0){
            for(int i=0;i<echartItems.size();i++){
                echart.getNames().add(echartItems.get(i).getName());
                echart.getValues().add(echartItems.get(i).getValue());
            }
        }
        return ResultUtils.success("查询成功",echart);
    }

    //热销卡
    @GetMapping("/getHotCards")
    public ResultVo getHotCards(){
        List<EchartsItem> echartsItems = goodsOrderService.hotCards();
        return ResultUtils.success("查询成功",echartsItems);
    }

    //热销课程
    @GetMapping("/getHotCourse")
    public ResultVo getHotCourse(){
        List<EchartsItem> echartsItems = goodsOrderService.hotCourse();
        return ResultUtils.success("查询成功",echartsItems);
    }
    //修改密码
    @PostMapping("/resetPassword")
    public ResultVo resetPassword(@RequestBody ResetPassword resetPassword){
        if(resetPassword.getUserType().equals("1")){
            Member member = new Member();
            member.setMemberId(resetPassword.getUserId());
            member.setPassword(passwordEncoder.encode("666666"));
            memberService.updateById(member);
            return ResultUtils.success("密码重置成功!");
        }else if(resetPassword.getUserType().equals("2")){
            SysUser user = new SysUser();
            user.setUserId(resetPassword.getUserId());
//            String password = DigestUtils.md5DigestAsHex("666666".getBytes());
            // 现在使用的是安全框架中的加密方式
            user.setPassword(passwordEncoder.encode("666666"));
            sysUserService.updateById(user);
            return ResultUtils.success("密码重置成功!");
        }else{
            return ResultUtils.error("用户类型错误!");
        }
    }
    //修改密码
    @PostMapping("/updatePassword")
    public ResultVo updatePassword(@RequestBody ResetPassword resetPassword) {
        if (resetPassword.getUserType().equals("1")) {

            //验证原密码是否正确
            Member member1 = memberService.getById(resetPassword.getUserId());
            // 获取原密码
            String dbPassWord =member1.getPassword();
            if (!passwordEncoder.matches(resetPassword.getOldPassword(), dbPassWord)) {
                return ResultUtils.error("原密码不正确!");
            }
            //修改
            Member member = new Member();
            member.setMemberId(resetPassword.getUserId());
//            member.setPassword(resetPassword.getPassword());
            member.setPassword(passwordEncoder.encode(resetPassword.getPassword()));
            memberService.updateById(member);
            return ResultUtils.success("修改密码成功!");
        } else if (resetPassword.getUserType().equals("2")) {
            //验证原密码
            SysUser sysUser = sysUserService.getById(resetPassword.getUserId());
            /*String oldPas = DigestUtils.md5DigestAsHex(resetPassword.getOldPassword().getBytes());
            if(!oldPas.equals(sysUser.getPassword())){
                return ResultUtils.error("原密码不正确!");
            }*/
            String dbPassWord = sysUser.getPassword();
            if (!passwordEncoder.matches(resetPassword.getOldPassword(), dbPassWord)) {
                return ResultUtils.error("原密码不正确!");
            }
            SysUser user = new SysUser();
            user.setUserId(resetPassword.getUserId());
//            String password = DigestUtils.md5DigestAsHex(resetPassword.getPassword().getBytes());
            String password = passwordEncoder.encode(resetPassword.getPassword());
            user.setPassword(password);
            sysUserService.updateById(user);
            return ResultUtils.success("修改密码成功!");
        } else {
            return ResultUtils.error("用户类型错误!");
        }
    }
    /**
     * 退出登录
     */
    @PostMapping("/loginOut")
    public ResultVo loginOut(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return ResultUtils.success("退出登录成功!");
    }
}