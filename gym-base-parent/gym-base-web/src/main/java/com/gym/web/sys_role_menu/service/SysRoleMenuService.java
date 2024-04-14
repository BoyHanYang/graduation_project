package com.gym.web.sys_role_menu.service;

import com.gym.web.sys_role.entity.SaveMenuParm;
import com.gym.web.sys_role_menu.entity.SysRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author AdminHan
* @description 针对表【sys_role_menu】的数据库操作Service
* @createDate 2024-04-14 19:49:11
*/
public interface SysRoleMenuService extends IService<SysRoleMenu> {
    //保存角色权限
    void saveMenu(SaveMenuParm parm);
}
