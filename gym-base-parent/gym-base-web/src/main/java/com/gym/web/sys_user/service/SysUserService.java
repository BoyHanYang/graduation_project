package com.gym.web.sys_user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gym.web.sys_user.entity.PageParm;
import com.gym.web.sys_user.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author AdminHan
* @description 针对表【sys_user】的数据库操作Service
* @createDate 2024-03-27 17:33:44
*/
public interface SysUserService extends IService<SysUser> {
    // 新增
    void addUser(SysUser sysUser);
    // 删除
    void deleteUser(Integer userId);
    // 修改
    void editUser(SysUser sysUser);
    // 列表
    IPage<SysUser> getList(PageParm parm);
}
