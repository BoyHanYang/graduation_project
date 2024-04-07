package com.gym.web.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.web.member.entity.Member;
import com.gym.web.member.service.MemberService;
import com.gym.web.member.mapper.MemberMapper;
import org.springframework.stereotype.Service;

/**
* @author AdminHan
* @description 针对表【member】的数据库操作Service实现
* @createDate 2024-04-07 19:58:32
*/
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member>
    implements MemberService{

}




