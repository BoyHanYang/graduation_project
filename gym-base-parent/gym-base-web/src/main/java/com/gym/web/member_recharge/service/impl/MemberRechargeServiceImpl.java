package com.gym.web.member_recharge.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.web.member.entity.RechargeParmList;
import com.gym.web.member_recharge.entity.MemberRecharge;
import com.gym.web.member_recharge.service.MemberRechargeService;
import com.gym.web.member_recharge.mapper.MemberRechargeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author AdminHan
* @description 针对表【member_recharge】的数据库操作Service实现
* @createDate 2024-04-07 21:15:52
*/
@Service
public class MemberRechargeServiceImpl extends ServiceImpl<MemberRechargeMapper, MemberRecharge>
    implements MemberRechargeService{
    @Override
    public IPage<MemberRecharge> getRechargeList(RechargeParmList parmList) {
        //构造分页对象
        IPage<MemberRecharge> page = new Page<>(parmList.getCurrentPage(),parmList.getPageSize());
        return this.baseMapper.getRechargeList(page);
    }

    @Override
    public IPage<MemberRecharge> getRechargeByMember(RechargeParmList parmList) {
        //构造分页对象
        IPage<MemberRecharge> page = new Page<>(parmList.getCurrentPage(),parmList.getPageSize());
        return this.baseMapper.getRechargeByMember(page,parmList.getMemberId());
    }
}




