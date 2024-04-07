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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author yangbohan
 * @Date 2024/4/7 20:01
 */

@RestController
@RequestMapping("/api/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    //新增
    @PostMapping
    public ResultVo add(@RequestBody Member member){
        if(memberService.save(member)){
            return ResultUtils.success("新增成功!");
        }
        return ResultUtils.error("新增失败!");
    }

    //编辑
    @PutMapping
    public ResultVo edit(@RequestBody Member member){
        if(memberService.updateById(member)){
            return ResultUtils.success("编辑成功!");
        }
        return ResultUtils.error("编辑失败!");
    }

    //删除
    @DeleteMapping("/{memberId}")
    public ResultVo delete(@PathVariable("memberId") Long memberId){
        if(memberService.removeById(memberId)){
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("删除失败!");
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
        IPage<Member> list = memberService.page(page, query);
        return ResultUtils.success("查询成功",list);
    }
}
