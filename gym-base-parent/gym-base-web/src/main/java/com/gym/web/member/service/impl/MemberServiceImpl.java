package com.gym.web.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.web.member.entity.JoinParm;
import com.gym.web.member.entity.Member;
import com.gym.web.member.entity.RechargeParm;
import com.gym.web.member.service.MemberService;
import com.gym.web.member.mapper.MemberMapper;
import com.gym.web.member_apply.entity.MemberApply;
import com.gym.web.member_apply.service.MemberApplyService;
import com.gym.web.member_card.entity.MemberCard;
import com.gym.web.member_card.service.MemberCardService;
import com.gym.web.member_recharge.entity.MemberRecharge;
import com.gym.web.member_recharge.service.MemberRechargeService;
import com.gym.web.member_role.entity.MemberRole;
import com.gym.web.member_role.service.MemberRoleService;
import com.gym.web.sys_user.entity.SysUser;
import com.gym.web.sys_user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author AdminHan
 * @description 针对表【member】的数据库操作Service实现
 * @createDate 2024-04-07 19:58:32
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member>
        implements MemberService {

    @Autowired
    private MemberRoleService memberRoleService;
    @Autowired
    private MemberCardService memberCardService;
    @Autowired
    private MemberApplyService memberApplyService;
    @Autowired
    private MemberRechargeService memberRechargeService;
    @Autowired
    private SysUserService sysUserService;

    @Override
    @Transactional
    public void addMember(Member member) {
        //新增会员
        int insert = this.baseMapper.insert(member);
        //设置会员角色
        if (insert > 0) {
            MemberRole role = new MemberRole();
            role.setMemberId(member.getMemberId());
            role.setRoleId(member.getRoleId());
            memberRoleService.save(role);
        }
    }

    @Override
    @Transactional
    public void editMember(Member member) {
        int i = this.baseMapper.updateById(member);
        //设置角色  先删除，再插入
        if (i > 0) {
            //删除
            QueryWrapper<MemberRole> query = new QueryWrapper<>();
            query.lambda().eq(MemberRole::getMemberId, member.getMemberId());
            memberRoleService.remove(query);
            //重新插入
            MemberRole role = new MemberRole();
            role.setMemberId(member.getMemberId());
            role.setRoleId(member.getRoleId());
            memberRoleService.save(role);
        }

    }

    @Override
    @Transactional
    public void deleteMember(Long memberId) {
        //删除
        int i = this.baseMapper.deleteById(memberId);
        if (i > 0) {
            QueryWrapper<MemberRole> query = new QueryWrapper<>();
            query.lambda().eq(MemberRole::getMemberId, memberId);
            memberRoleService.remove(query);
        }
    }

    @Override
    public void joinApply(JoinParm joinParm) throws ParseException {
        // 根据会员id查询会员信息
        Member select = this.baseMapper.selectById(joinParm.getMemberId());
        MemberCard card = memberCardService.getById(joinParm.getCardId());
        // 更新会员信息
        Member member = new Member();
        member.setMemberId(joinParm.getMemberId());
        member.setCardType(card.getTitle());
        member.setCardDay(card.getCardDay());
        member.setPrice(card.getPrice());
        String endTime = select.getEndTime();
        Calendar calendar = Calendar.getInstance();
        // 判断结束时间是否为空
        if (StringUtils.isNotEmpty(endTime)) {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(select.getEndTime());
            calendar.setTime(date);
            calendar.add(Calendar.DATE, card.getCardDay());
        } else {
            // 当前时间加上卡的天数
            Date data = new Date();
            calendar.setTime(data);
            calendar.add(Calendar.DATE, card.getCardDay());
        }
        member.setEndTime(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
        int i = this.baseMapper.updateById(member);
        if (i > 0) {
            //插入明细
            MemberApply memberApply = new MemberApply();
            memberApply.setCardDay(card.getCardDay());
            memberApply.setCardType(card.getTitle());
            memberApply.setMemberId(joinParm.getMemberId());
            memberApply.setPrice(card.getPrice());
            memberApply.setCreateTime(new Date());
            memberApplyService.save(memberApply);
        }

    }

    @Override
    public void recharge(RechargeParm parm) {
        Integer userId = parm.getUserId();
        SysUser user = sysUserService.getById(userId);
        // 生成充值明细
        MemberRecharge recharge = new MemberRecharge();
        recharge.setMemberId(parm.getMemberId());
        recharge.setMoney(parm.getMoney());
        recharge.setCreateTime(new Date());
        recharge.setCreateUser(user.getNickName());
        boolean save = memberRechargeService.save(recharge);
        if (save) {
            //更新会员账号的金额
            this.baseMapper.addMoney(parm);
        }
    }

    @Override
    public Member loadUser(String username) {
        QueryWrapper<Member> query = new QueryWrapper<>();
        query.lambda().eq(Member::getUsername,username);
        return this.baseMapper.selectOne(query);
    }
}




