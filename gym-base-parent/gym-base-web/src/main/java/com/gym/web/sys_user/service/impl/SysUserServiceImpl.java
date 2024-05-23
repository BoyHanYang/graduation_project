package com.gym.web.sys_user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.web.sys_user.entity.PageParm;
import com.gym.web.sys_user.entity.SysUser;
import com.gym.web.sys_user.service.SysUserService;
import com.gym.web.sys_user.mapper.SysUserMapper;
import com.gym.web.sys_user_role.entity.SysUserRole;
import com.gym.web.sys_user_role.service.SysUserRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author AdminHan
 * @description 针对表【sys_user】的数据库操作Service实现
 * @createDate 2024-03-27 17:33:44
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
        implements SysUserService {
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    @Transactional
    public void addUser(SysUser sysUser) {
        //先保存用户，然后保存用户对应的角色
        int insert = this.baseMapper.insert(sysUser);
        if(insert >0){
            //保存用户的角色
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(sysUser.getRoleId());
            sysUserRole.setUserId(sysUser.getUserId());
            sysUserRoleService.save(sysUserRole);
        }
    }

    @Override
    @Transactional
    public void editUser(SysUser sysUser) {
        //先编辑用户，然后原来的角色删除
        int insert = this.baseMapper.updateById(sysUser);
        if(insert >0){
            //先删除原来的角色
            QueryWrapper<SysUserRole> query = new QueryWrapper<>();
            query.lambda().eq(SysUserRole::getUserId,sysUser.getUserId());
            sysUserRoleService.remove(query);
            //再保存用户的角色
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(sysUser.getRoleId());
            sysUserRole.setUserId(sysUser.getUserId());
            sysUserRoleService.save(sysUserRole);
        }
    }

    @Override
    @Transactional
    public void deleteUser(Integer userId) {
        int i = this.baseMapper.deleteById(userId);
        if(i >0){
            //删除用户原来的角色
            QueryWrapper<SysUserRole> query = new QueryWrapper<>();
            query.lambda().eq(SysUserRole::getUserId,userId);
            sysUserRoleService.remove(query);
        }
    }

    @Override
    public IPage<SysUser> getList(PageParm parm) {
        //构造分页对象
        IPage<SysUser> page = new Page<>(parm.getCurrentPage(),parm.getPageSize());
        //构造查询条件
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(parm.getPhone())){
            query.lambda().like(SysUser::getPhone,parm.getPhone());
        }
        if(StringUtils.isNotEmpty(parm.getNickName())){
            query.lambda().like(SysUser::getNickName,parm.getNickName());
        }
        return this.baseMapper.selectPage(page,query);
    }

    @Override
    public SysUser loadUser(String username) {
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.lambda().eq(SysUser::getUsername,username);
        return this.baseMapper.selectOne(query);
    }
}




