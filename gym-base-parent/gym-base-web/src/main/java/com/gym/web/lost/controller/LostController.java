package com.gym.web.lost.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gym.utils.ResultUtils;
import com.gym.utils.ResultVo;
import com.gym.web.lost.entity.Lost;
import com.gym.web.lost.entity.LostParm;
import com.gym.web.lost.service.LostService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @Author yangbohan
 * @Date 2024/4/10 17:40
 */

@RestController
@RequestMapping("/api/lost")
public class LostController {
    @Autowired
    private LostService lostService;

    //新增
    @PreAuthorize("hasAuthority('sys:lostList:add')")
    @PostMapping
    public ResultVo add(@RequestBody Lost lost){
        if(lostService.save(lost)){
            return ResultUtils.success("插入成功!");
        }
        return ResultUtils.error("插入失败!");
    }

    //编辑
    @PreAuthorize("hasAuthority('sys:lostList:edit')")
    @PutMapping
    public ResultVo edit(@RequestBody Lost lost){
        if(lostService.updateById(lost)){
            return ResultUtils.success("编辑成功!");
        }
        return ResultUtils.error("编辑失败!");
    }

    //删除
    @PreAuthorize("hasAuthority('sys:lostList:delete')")
    @DeleteMapping("/{lostId}")
    public ResultVo delete(@PathVariable("lostId") Long lostId){
        if(lostService.removeById(lostId)){
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("删除失败!");
    }

    //列表查询
    @GetMapping("/list")
    public ResultVo list(LostParm parm){
        //构造分页对象
        IPage<Lost> page = new Page<>(parm.getCurrentPage(),parm.getPageSize());
        //构造查询条件
        QueryWrapper<Lost> query = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(parm.getLostName())){
            query.lambda().like(Lost::getLostName,parm.getLostName());
        }
        IPage<Lost> list = lostService.page(page, query);
        return ResultUtils.success("查询成功",list);
    }
}
