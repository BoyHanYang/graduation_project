package com.gym.web.member_recharge.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gym.web.member.entity.RechargeParmList;
import com.gym.web.member_recharge.entity.MemberRecharge;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author AdminHan
* @description 针对表【member_recharge】的数据库操作Service
* @createDate 2024-04-07 21:15:52
*/
public interface MemberRechargeService extends IService<MemberRecharge> {
    IPage<MemberRecharge> getRechargeList(RechargeParmList parmList);
    IPage<MemberRecharge> getRechargeByMember(RechargeParmList parmList);
}
