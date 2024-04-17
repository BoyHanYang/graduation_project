package com.gym.web.sys_menu.entity;

import org.springframework.beans.BeanUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author yangbohan
 * @Date 2024/4/2 20:56
 */

public class MakeMenuTree {
    public static List<SysMenu> makeTree(List<SysMenu> menuList, Integer pid) {
        List<SysMenu> list = new ArrayList<>();
        Optional.ofNullable(menuList).orElse(new ArrayList<>())
                .stream()
                .filter(item -> item != null && item.getParentId().equals(pid))
                .forEach(item -> {
                    SysMenu sysMenu = new SysMenu();
                    BeanUtils.copyProperties(item, sysMenu);
                    List<SysMenu> children = makeTree(menuList, item.getMenuId());
                    sysMenu.setChildren(children);
                    list.add(sysMenu);
                });
        return list;
    }

    public static List<RouterVO> makeRouter(List<SysMenu> menuList, Integer pid) {
        ArrayList<RouterVO> list = new ArrayList<>();
        Optional.ofNullable(menuList).orElse(new ArrayList<>())
                .stream()
                .filter(item -> item != null && item.getParentId() == pid)
                .forEach(item -> {
                    RouterVO router = new RouterVO();
                    router.setName(item.getName());
                    router.setPath(item.getPath());
                    // 设置children每一项的下级
                    List<RouterVO> children = makeRouter(menuList, item.getMenuId());
                    router.setChildren(children);
                    // parent为0时，为一级菜单，component为layout
                    if ((item.getParentId() == 0)) {
                        router.setComponent("Layout");
                        //判断一级菜单是否为菜单类型 0：目录 1：菜单
                        if (item.getType().equals("1")) {
                            //一级菜单，设置redirect转发到他的children
                            router.setRedirect(item.getPath());
                            //如果一级菜单属于菜单类型，需要设置他的children,前端才会显示
                            List<RouterVO> listChild = new ArrayList<>();
                            //构造children
                            RouterVO child = new RouterVO();
                            child.setName(item.getName());
                            child.setPath(item.getPath());
                            child.setComponent(item.getUrl());
                            child.setMeta(child.new Meta(
                                    item.getTitle(),
                                    item.getIcon(),
                                    item.getCode().split(",")
                            ));
                            listChild.add(child);
                            //设置children
                            router.setChildren(listChild);
                            router.setPath(item.getPath() + "parent");
                            router.setName(item.getName() + "parent");
                        }
                    } else {
                        router.setComponent(item.getUrl());
                    }
                    // 设置meta
                    router.setMeta(router.new Meta(
                            item.getTitle(),
                            item.getIcon(),
                            item.getCode().split(",")
                    ));
                    list.add(router);
                });
        return list;
    }
}
