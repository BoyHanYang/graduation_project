package com.gym.web.login.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.gym.jwt.JwtUtils;
import com.gym.utils.ResultUtils;
import com.gym.utils.ResultVo;
import com.gym.web.login.entity.InfoParm;
import com.gym.web.login.entity.LoginParm;
import com.gym.web.login.entity.LoginResult;
import com.gym.web.login.entity.UserInfo;
import com.gym.web.member.entity.Member;
import com.gym.web.member.service.MemberService;
import com.gym.web.sys_menu.entity.MakeMenuTree;
import com.gym.web.sys_menu.entity.RouterVO;
import com.gym.web.sys_menu.entity.SysMenu;
import com.gym.web.sys_menu.service.SysMenuService;
import com.gym.web.sys_menu.service.impl.SysMenuServiceImpl;
import com.gym.web.sys_user.entity.SysUser;
import com.gym.web.sys_user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author yangbohan
 * @Date 2024/4/11 21:04
 */

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private DefaultKaptcha defaultKaptcha;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private MemberService memberService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysMenuService sysMenuService;

    @PostMapping("/image")
    public ResultVo imageCode(HttpServletRequest request) throws IOException {
        //获取验证码字符
        String text = defaultKaptcha.createText();
        //存储验证码到session
        HttpSession session = request.getSession();
        session.setAttribute("code", text);
        //生成图片
        BufferedImage bufferedImage = defaultKaptcha.createImage(text);
        ByteArrayOutputStream outputStream = null;
        try {
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);
            byte[] bytes = outputStream.toByteArray();
            String base64 = Base64.getEncoder().encodeToString(bytes);
            String captchaBase64 = "data:image/jpeg;base64," + base64.replaceAll("\r\n", "");
            @SuppressWarnings("unchecked")
            ResultVo result = new ResultVo("生成成功", 200, captchaBase64);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @PostMapping("/login")
    public ResultVo login(HttpServletRequest request, @RequestBody LoginParm loginParm) {
        // 获取session
        HttpSession session = request.getSession();
        // 获取session中的验证码
        String code = (String) session.getAttribute("code");
        if (!code.equals(loginParm.getCode())) {
            return ResultUtils.error("验证码错误！");
        }
        String password = DigestUtils.md5DigestAsHex(loginParm.getPassword().getBytes());
        // 获取密码
        if (loginParm.getUserType().equals("1")) {
            // 会员
            // 构造查询条件
            QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(Member::getUsername, loginParm.getUsername())
                    .eq(Member::getPassword, password);
            Member one = memberService.getOne(queryWrapper);
            if (one == null) {
                return ResultUtils.error("用户名或密码错误");
            }
            // 生成token
            Map<String, String> map = new HashMap<>();
            map.put("userId", Long.toString(one.getMemberId()));
            map.put("username", one.getUsername());
            String token = jwtUtils.generateToken(map);
            LoginResult loginResult = new LoginResult();
            loginResult.setToken(token);
            loginResult.setUserId(one.getMemberId());
            loginResult.setUsername(one.getName());
            loginResult.setUserType(loginParm.getUserType());
            return ResultUtils.success("登录成功", loginResult);
        } else if (loginParm.getUserType().equals("2")) {
            // 员工
            QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(SysUser::getUsername, loginParm.getUsername())
                    .eq(SysUser::getPassword, password);
            SysUser one = sysUserService.getOne(queryWrapper);
            if (one == null) {
                return ResultUtils.error("用户名或密码错误");
            }
            // 生成token
            Map<String, String> map = new HashMap<>();
            map.put("userId", Long.toString(one.getUserId()));
            map.put("username", one.getUsername());
            String token = jwtUtils.generateToken(map);
            LoginResult loginResult = new LoginResult();
            loginResult.setToken(token);
            loginResult.setUserId(one.getUserId());
            loginResult.setUsername(one.getNickName());
            loginResult.setUserType(loginParm.getUserType());
//            System.out.println("登陆成功");
            return ResultUtils.success("登录成功", loginResult);

        } else {
            return ResultUtils.error("用户类型错误");
        }
    }

    // 查询用户信息
    @GetMapping("/getInfo")
    public ResultVo getInfo(InfoParm parm) {
        UserInfo userInfo = new UserInfo();
        if (parm.getUserType().equals("1")) {// 会员
            List<SysMenu> menuList = sysMenuService.getMenuByMemberId(parm.getUserId());
            List<String> collect = Optional.ofNullable(menuList).orElse(new ArrayList<>())
                    .stream()
                    .map(item -> item.getCode()).filter(item -> item != null)
                    .collect(Collectors.toList());
            //转为数组
            String[] strings = collect.toArray(new String[collect.size()]);
            //查询用户信息
            Member member = memberService.getById(parm.getUserId());
            //设置返回信息
            userInfo.setName(member.getName());
            userInfo.setUserId(member.getMemberId());
            userInfo.setPermissions(strings);
            return ResultUtils.success("查询成功",userInfo);
        }else if(parm.getUserType().equals("2")){ //员工
            //查询用户信息
            SysUser user = sysUserService.getById(parm.getUserId());
            List<SysMenu> menuList = null;
            if(StringUtils.isNotEmpty(user.getIsAdmin()) && user.getIsAdmin().equals("1")){ //超级管理员
                menuList = sysMenuService.list();
            }else{
                menuList = sysMenuService.getMenuByUserId(user.getUserId());
            }
            //获取全部的code字段
            List<String> collect = Optional.ofNullable(menuList).orElse(new ArrayList<>())
                    .stream()
                    .map(item -> item.getCode())
                    .filter(item -> item != null)
                    .collect(Collectors.toList());
            //转为数组
            String[] strings = collect.toArray(new String[collect.size()]);

            //设置返回信息
            userInfo.setName(user.getNickName());
            userInfo.setUserId(user.getUserId());
            userInfo.setPermissions(strings);
            return ResultUtils.success("查询成功",userInfo);
        } else {
            return ResultUtils.error("用户类型错误");
        }
    }
    /**
     * 获取菜单数据
     */
    @GetMapping("/getMenuList")
    public ResultVo getMenuList(InfoParm parm) {
        if (parm.getUserType().equals("1")){
            List<SysMenu> menus = sysMenuService.getMenuByMemberId(parm.getUserId());
            //获取菜单和目录
            List<SysMenu> collect = Optional.ofNullable(menus).orElse(new ArrayList<>())
                    .stream()
                    .filter(item -> item != null && !item.getType().equals("2")).collect(Collectors.toList());
            List<RouterVO> rourer = MakeMenuTree.makeRouter(collect, 0);
            return ResultUtils.success("查询成功", rourer);
        } else if (parm.getUserType().equals("2")) { //员工
            SysUser user = sysUserService.getById(parm.getUserId());
            List<SysMenu> menuList = null;
            if (org.apache.commons.lang3.StringUtils.isNotEmpty(user.getIsAdmin()) && user.getIsAdmin().equals("1")) {
                menuList = sysMenuService.list();
            } else {
                menuList = sysMenuService.getMenuByUserId(parm.getUserId());
            }
            //获取菜单和目录
            List<SysMenu> collect = Optional.ofNullable(menuList).orElse(new ArrayList<>())
                    .stream()
                    .filter(item -> item != null && !item.getType().equals("2")).collect(Collectors.toList());
            List<RouterVO> rourer = MakeMenuTree.makeRouter(collect, 0);
            return ResultUtils.success("查询成功", rourer);
        } else {
            return ResultUtils.error("用户类型错误!");
        }
    }
}

