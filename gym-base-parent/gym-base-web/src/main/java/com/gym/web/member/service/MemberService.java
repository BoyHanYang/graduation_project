package com.gym.web.member.service;

import com.gym.web.member.entity.JoinParm;
import com.gym.web.member.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gym.web.member.entity.RechargeParm;

import java.io.Serializable;
import java.text.ParseException;

/**
* @author AdminHan
* @description 针对表【member】的数据库操作Service
* @createDate 2024-04-07 19:58:32
*/
public interface MemberService extends IService<Member> {
    void addMember(Member member);
    void editMember(Member member);
    void deleteMember(Long memberId);
    // 办卡
    void joinApply(JoinParm joinParm) throws ParseException;
    // 充值
    void recharge(RechargeParm parm);
    //根据用户名查用户
    Member loadUser(String username);
}
