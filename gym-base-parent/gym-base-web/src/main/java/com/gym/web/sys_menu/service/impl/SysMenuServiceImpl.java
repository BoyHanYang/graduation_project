package com.gym.web.sys_menu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.web.sys_menu.entity.MakeMenuTree;
import com.gym.web.sys_menu.entity.SysMenu;
import com.gym.web.sys_menu.service.SysMenuService;
import com.gym.web.sys_menu.mapper.SysMenuMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
* @author AdminHan
* @description 针对表【sys_menu】的数据库操作Service实现
* @createDate 2024-04-02 20:53:10
*/
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
    implements SysMenuService{

    @Override
    public List<SysMenu> getParent() {
        String[] type = {"0","1"};
        List<String> strings = Arrays.asList(type);
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(SysMenu::getType,strings).orderByAsc(SysMenu::getOrderNum);
        List<SysMenu> menus = this.baseMapper.selectList(queryWrapper);
        SysMenu sysMenu = new SysMenu();
        sysMenu.setMenuId(0);
        sysMenu.setParentId(-1);
        sysMenu.setTitle("顶级菜单");
        menus.add(sysMenu);
        return MakeMenuTree.makeTree(menus,-1);
    }
}




