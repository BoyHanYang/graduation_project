package com.gym.web.sys_menu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gym.utils.ResultUtils;
import com.gym.utils.ResultVo;
import com.gym.web.sys_menu.entity.MakeMenuTree;
import com.gym.web.sys_menu.entity.SysMenu;
import com.gym.web.sys_menu.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Author yangbohan
 * @Date 2024/4/2 21:22
 */
@RestController
@RequestMapping("/api/menu")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;
    // 新增
    @PostMapping
    @PreAuthorize("hasAuthority('sys:menu:add')")
    public ResultVo add(@RequestBody SysMenu sysMenu){
        sysMenu.setCreateTime(new Date());
        if (sysMenuService.save(sysMenu)){
            return ResultUtils.success("添加成功");
        }
        return ResultUtils.error("添加失败");
    }
    // 编辑
    @PutMapping
    @PreAuthorize("hasAuthority('sys:menu:edit')")
    public ResultVo edit(@RequestBody SysMenu sysMenu){
        sysMenu.setUpdateTime(new Date());
        if (sysMenuService.updateById(sysMenu)){
            return ResultUtils.success("编辑成功");
        }
        return ResultUtils.error("编辑失败");
    }
    // 删除
    @DeleteMapping("/{menuId}")
    @PreAuthorize("hasAuthority('sys:menu:delete')")
    public ResultVo delete(@PathVariable("menuId") long menuId){
        if (sysMenuService.removeById(menuId)){
            return ResultUtils.success("删除成功");
        }
        return ResultUtils.error("删除失败");
    }
    // 查询
    @GetMapping("/list")
    public ResultVo list(){
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().orderByAsc(SysMenu::getOrderNum);
        List<SysMenu> list = sysMenuService.list();
        List<SysMenu> menuList = MakeMenuTree.makeTree(list, 0);
        return ResultUtils.success("查询成功",menuList);
    }
    // 查询上级树
    @GetMapping("/parent")
    public ResultVo getParent(){
        List<SysMenu> parent = sysMenuService.getParent();
        return ResultUtils.success("查询成功",parent);
    }

}
