package com.gym.web.sys_menu.service;

import com.gym.web.sys_menu.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author AdminHan
* @description 针对表【sys_menu】的数据库操作Service
* @createDate 2024-04-02 20:53:10
*/
public interface SysMenuService extends IService<SysMenu> {
    List<SysMenu> getParent();
}
