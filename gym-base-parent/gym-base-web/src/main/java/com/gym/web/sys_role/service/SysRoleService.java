package com.gym.web.sys_role.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gym.web.sys_role.entity.RoleAssignParm;
import com.gym.web.sys_role.entity.RoleParm;
import com.gym.web.sys_role.entity.RolePermissionVo;
import com.gym.web.sys_role.entity.SysRole;

/**
 * @Author yangbohan
 * @Date 2024/3/20 21:30
 */

public interface SysRoleService extends IService<SysRole> {
    IPage<SysRole> getList(RoleParm parm);
    // 查询权限树回显
    RolePermissionVo getMenuTree(RoleAssignParm parm);
}
