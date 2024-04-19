package com.gym.web.member_recharge.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gym.web.member_recharge.entity.MemberRecharge;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author AdminHan
* @description 针对表【member_recharge】的数据库操作Mapper
* @createDate 2024-04-07 21:15:52
* @Entity com.gym.web.member_recharge.entity.MemberRecharge
*/
public interface MemberRechargeMapper extends BaseMapper<MemberRecharge> {
    IPage<MemberRecharge> getRechargeList(IPage<MemberRecharge> page);
    IPage<MemberRecharge> getRechargeByMember(IPage<MemberRecharge> page,@Param("memberId") Long memberId);
}




