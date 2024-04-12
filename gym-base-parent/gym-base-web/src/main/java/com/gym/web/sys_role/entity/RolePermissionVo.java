package com.gym.web.sys_role.entity;

import com.gym.web.sys_menu.entity.SysMenu;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yangbohan
 * @Date 2024/4/12 20:07
 */

@Data
public class RolePermissionVo {
    //当前登录系统用户的菜单数据
    List<SysMenu> listmenu = new ArrayList<>();
    //角色原来分配的菜单
    private Object[] checkList;
}