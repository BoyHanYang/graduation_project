package com.gym.web.sys_role.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gym.utils.ResultUtils;
import com.gym.utils.ResultVo;
import com.gym.web.sys_role.entity.RoleParm;
import com.gym.web.sys_role.entity.SysRole;
import com.gym.web.sys_role.service.SysRoleService;
import com.gym.web.sys_user.entity.SelectType;
import com.gym.web.sys_user.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    // 员工新增列表
    @GetMapping("/getSelect")
    public ResultVo getSelect() {
        List<SysRole> list = sysRoleService.list();
        ArrayList<Object> selectTypeList = new ArrayList<>();
        if (list.size()>0){
            list.stream().forEach(item->{
                SelectType selectType = new SelectType();
                selectType.setLabel(item.getRoleName());
                selectType.setValue(item.getRoleId());
                selectTypeList.add(selectType);
            });
        }
        return ResultUtils.success("查询成功",selectTypeList);
    }
}
