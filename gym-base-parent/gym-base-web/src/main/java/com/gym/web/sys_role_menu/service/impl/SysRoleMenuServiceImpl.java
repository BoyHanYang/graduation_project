package com.gym.web.sys_role_menu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.web.sys_role.entity.SaveMenuParm;
import com.gym.web.sys_role_menu.entity.SysRoleMenu;
import com.gym.web.sys_role_menu.service.SysRoleMenuService;
import com.gym.web.sys_role_menu.mapper.SysRoleMenuMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author AdminHan
 * @description 针对表【sys_role_menu】的数据库操作Service实现
 * @createDate 2024-04-14 19:49:11
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu>
        implements SysRoleMenuService {
    @Transactional
    @Override
    public void saveMenu(SaveMenuParm parm) {
        //先删除角色原来的权限
        QueryWrapper<SysRoleMenu> query = new QueryWrapper<>();
        query.lambda().eq(SysRoleMenu::getRoleId, parm.getRoleId());
        this.baseMapper.delete(query);
        //重新保存
        this.baseMapper.saveRoleMenu(parm.getRoleId(), parm.getList());
    }
}




