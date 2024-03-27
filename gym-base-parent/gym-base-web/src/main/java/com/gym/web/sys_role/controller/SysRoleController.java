package com.gym.web.sys_role.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gym.web.sys_role.entity.RoleParm;
import com.gym.web.sys_role.entity.SysRole;
import com.gym.web.sys_role.service.SysRoleService;
import com.itmk.utils.ResultUtils;
import com.itmk.utils.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @Author yangbohan
 * @Date 2024/3/20 21:33
 */
@RestController
@RequestMapping("/api/role")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;
    // 新增
    @PostMapping
    public ResultVo add(@RequestBody SysRole sysRole){
        sysRole.setCreateTime(new Date());
        boolean save = sysRoleService.save(sysRole);
        if (save){
            return ResultUtils.success("新增角色成功");
        }
        return ResultUtils.error("新增角色失败");
    }
    // 编辑
    @PutMapping("")
    public ResultVo edit(@RequestBody SysRole sysRole){
        sysRole.setUpdateTime(new Date());
        boolean updateById = sysRoleService.updateById(sysRole);
        if (updateById){
            return ResultUtils.success("编辑角色成功");
        }
        return ResultUtils.error("编辑角色失败");
    }
    // 删除
    @DeleteMapping("/{roleId}")
    public ResultVo delete(@PathVariable("roleId") long roleId){
        boolean deleteById = sysRoleService.removeById(roleId);
        if (deleteById){
            return ResultUtils.success("删除角色成功");
        }
        return ResultUtils.error("删除角色失败");
    }
    // 查询
    @GetMapping("/{roleId}")
    public ResultVo list(RoleParm parm){
        IPage<SysRole> list = sysRoleService.getList(parm);
        return ResultUtils.success("查询角色成功", list);
    }
}
