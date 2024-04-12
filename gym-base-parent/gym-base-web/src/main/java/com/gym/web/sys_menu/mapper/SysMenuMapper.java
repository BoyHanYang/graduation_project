package com.gym.web.sys_menu.mapper;

import com.gym.web.sys_menu.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author AdminHan
 * @description 针对表【sys_menu】的数据库操作Mapper
 * @createDate 2024-04-02 20:53:10
 * @Entity com.gym.web.sys_menu.entity.SysMenu
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    // 根据员工id查询菜单
    List<SysMenu> getMenuByUserId(@Param("userId") Integer userId);
    // 根据会员id查询菜单
    List<SysMenu> getMenuByMemberId(@Param("userId") Integer userId);
    // 根据角色id查询菜单
    List<SysMenu> getMenuByRoleId(@Param("roleId") Integer roleId);
}




