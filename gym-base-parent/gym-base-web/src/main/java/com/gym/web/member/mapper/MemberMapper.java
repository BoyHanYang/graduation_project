package com.gym.web.member.mapper;

import com.gym.web.member.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gym.web.member.entity.RechargeParm;
import org.apache.ibatis.annotations.Param;

/**
* @author AdminHan
* @description 针对表【member】的数据库操作Mapper
* @createDate 2024-04-07 19:58:32
* @Entity com.gym.web.member.entity.Member
*/
public interface MemberMapper extends BaseMapper<Member> {
    int addMoney(@Param("parm") RechargeParm parm);
    int subMoney(@Param("parm") RechargeParm parm);
}




