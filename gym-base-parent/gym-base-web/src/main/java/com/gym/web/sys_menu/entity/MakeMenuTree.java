package com.gym.web.sys_menu.entity;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author yangbohan
 * @Date 2024/4/2 20:56
 */

public class MakeMenuTree {
    public static List<SysMenu> makeTree(List<SysMenu> menuList,Integer pid) {
        List<SysMenu> list = new ArrayList<>();
        Optional.ofNullable(menuList).orElse(new ArrayList<>())
                .stream()
                .filter(item->item!=null&&item.getParentId().equals(pid))
                .forEach(item->{
                    SysMenu sysMenu = new SysMenu();
                    BeanUtils.copyProperties(item,sysMenu);
                    List<SysMenu> children = makeTree(menuList, item.getMenuId());
                    sysMenu.setChildren(children);
                    list.add(sysMenu);
                });
        return list;
    }
}
