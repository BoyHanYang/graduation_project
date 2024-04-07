package com.gym.web.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.web.member.entity.Member;
import com.gym.web.member.service.MemberService;
import com.gym.web.member.mapper.MemberMapper;
import com.gym.web.member_role.entity.MemberRole;
import com.gym.web.member_role.service.MemberRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author AdminHan
* @description 针对表【member】的数据库操作Service实现
* @createDate 2024-04-07 19:58:32
*/
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member>
    implements MemberService{

    @Autowired
    private MemberRoleService memberRoleService;

    @Override
    @Transactional
    public void addMember(Member member) {
        //新增会员
        int insert = this.baseMapper.insert(member);
        //设置会员角色
        if(insert >0){
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
        if(i>0){
            //删除
            QueryWrapper<MemberRole> query = new QueryWrapper<>();
            query.lambda().eq(MemberRole::getMemberId,member.getMemberId());
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
        if(i > 0){
            QueryWrapper<MemberRole> query = new QueryWrapper<>();
            query.lambda().eq(MemberRole::getMemberId,memberId);
            memberRoleService.remove(query);
        }
    }
}




