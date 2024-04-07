package com.gym.web.member_card.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gym.utils.ResultUtils;
import com.gym.utils.ResultVo;
import com.gym.web.member_card.entity.ListCard;
import com.gym.web.member_card.entity.MemberCard;
import com.gym.web.member_card.service.MemberCardService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Author yangbohan
 * @Date 2024/4/3 15:37
 */
@RestController
@RequestMapping("/api/memberCard")
public class MemberCardController {
    @Autowired
    private MemberCardService memberCardService;
    // 新增
    @PostMapping
    public ResultVo add(@RequestBody MemberCard memberCard){
        if (memberCardService.save(memberCard)){
            return ResultUtils.success("新增成功");
        }
        return ResultUtils.error("新增失败");
    }
    // 编辑
    @PutMapping
    public ResultVo edit(@RequestBody MemberCard memberCard){
        if (memberCardService.updateById(memberCard)){
            return ResultUtils.success("编辑成功");
        }
        return ResultUtils.error("编辑失败");
    }
    // 删除
    @DeleteMapping("/{cardId}")
    public ResultVo delete(@PathVariable("cardId") Integer cardId){
        if (memberCardService.removeById(cardId)){
            return ResultUtils.success("删除成功");
        }
        return ResultUtils.error("删除失败");
    }
    // 列表
    @GetMapping("/list")
    public ResultVo list(ListCard listCard){
        Page<MemberCard> page = new Page<>(listCard.getCurrentPage(),listCard.getPageSize());
        QueryWrapper<MemberCard> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(listCard.getTitle())){
            queryWrapper.lambda().like(MemberCard::getTitle,listCard.getTitle());
        }
        IPage<MemberCard> memberCardPage = memberCardService.page(page, queryWrapper);
        return ResultUtils.success("查询成功",memberCardPage);
    }
}
