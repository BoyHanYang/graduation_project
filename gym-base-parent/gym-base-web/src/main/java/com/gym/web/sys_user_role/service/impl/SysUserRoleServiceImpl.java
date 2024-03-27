package com.gym.web.sys_user_role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.web.sys_user.entity.SysUser;
import com.gym.web.sys_user.entity.PageParm;
import com.gym.web.sys_user_role.entity.SysUserRole;
import com.gym.web.sys_user_role.service.SysUserRoleService;
import com.gym.web.sys_user_role.mapper.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author AdminHan
* @description 针对表【sys_user_role】的数据库操作Service实现
* @createDate 2024-03-27 18:04:34
*/
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole>
    implements SysUserRoleService{

}




