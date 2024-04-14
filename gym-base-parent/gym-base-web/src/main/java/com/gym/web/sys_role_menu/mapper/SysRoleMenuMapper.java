package com.gym.web.sys_role_menu.mapper;

import com.gym.web.sys_role_menu.entity.SysRoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author AdminHan
* @description 针对表【sys_role_menu】的数据库操作Mapper
* @createDate 2024-04-14 19:49:11
* @Entity com.gym.web.sys_role_menu.entity.SysRoleMenu
*/
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {
    //保存
    boolean saveRoleMenu(@Param("roleId") Integer roleId, @Param("menuIds") List<Integer> menuIds);
}




