package com.gym.web.member.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gym.utils.ResultUtils;
import com.gym.utils.ResultVo;
import com.gym.web.member.entity.Member;
import com.gym.web.member.entity.PageParm;
import com.gym.web.member.service.MemberService;
import com.gym.web.member_role.entity.MemberRole;
import com.gym.web.member_role.service.MemberRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRoleService memberRoleService;

    //新增
    @PostMapping
    public ResultVo add(@RequestBody Member member){
        //判断卡号是否重复
        QueryWrapper<Member> query = new QueryWrapper<>();
        query.lambda().eq(Member::getUsername,member.getUsername());
        Member one = memberService.getOne(query);
        if(one != null){
            return ResultUtils.error("会员卡号被占用!");
        }
        memberService.addMember(member);
        return ResultUtils.success("新增成功!");
    }

    //编辑
    @PutMapping
    public ResultVo edit(@RequestBody Member member){
        //判断卡号是否重复
        QueryWrapper<Member> query = new QueryWrapper<>();
        query.lambda().eq(Member::getUsername,member.getUsername());
        Member one = memberService.getOne(query);
        if(one != null && !one.getMemberId().equals(member.getMemberId())){
            return ResultUtils.error("会员卡号被占用!");
        }
        memberService.editMember(member);
        return ResultUtils.success("编辑成功!");
    }

    //删除
    @DeleteMapping("/{memberId}")
    public ResultVo delete(@PathVariable("memberId") Long memberId){
        memberService.deleteMember(memberId);
        return ResultUtils.success("删除成功!");
    }

    //查询
    @GetMapping("/list")
    public ResultVo list(PageParm pageParm){
        //构造分页对象
        IPage<Member> page = new Page<>(pageParm.getCurrentPage(),pageParm.getPageSize());
        //构造查询条件
        QueryWrapper<Member> query = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(pageParm.getName())){
            query.lambda().like(Member::getName,pageParm.getName());
        }
        if(StringUtils.isNotEmpty(pageParm.getPhone())){
            query.lambda().like(Member::getPhone,pageParm.getPhone());
        }
        if(StringUtils.isNotEmpty(pageParm.getUsername())){
            query.lambda().like(Member::getUsername,pageParm.getUsername());
        }
        query.lambda().orderByDesc(Member::getJoinTime);
        IPage<Member> list = memberService.page(page, query);
        return ResultUtils.success("查询成功",list);
    }
    //根据会员id查询角色id
    @GetMapping("/getRoleByMemberId")
    public ResultVo getRoleByMemberId(Long memberId){
        QueryWrapper<MemberRole> query = new QueryWrapper<>();
        query.lambda().eq(MemberRole::getMemberId,memberId);
        MemberRole one = memberRoleService.getOne(query);
        return ResultUtils.success("查询成功",one);
    }
}