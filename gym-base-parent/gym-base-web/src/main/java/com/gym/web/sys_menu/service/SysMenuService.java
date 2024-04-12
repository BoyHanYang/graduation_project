package com.gym.web.sys_menu.service;

import com.gym.web.sys_menu.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author AdminHan
* @description 针对表【sys_menu】的数据库操作Service
* @createDate 2024-04-02 20:53:10
*/
public interface SysMenuService extends IService<SysMenu> {
    List<SysMenu> getParent();
    // 根据员工id查询菜单
    List<SysMenu> getMenuByUserId(Integer userId);
    // 根据会员id查询菜单
    List<SysMenu> getMenuByMemberId(Integer userId);
    // 根据角色id查询菜单
    List<SysMenu> getMenuByRoleId(Integer roleId);
}
