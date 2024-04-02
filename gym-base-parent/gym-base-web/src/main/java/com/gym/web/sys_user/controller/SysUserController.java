package com.gym.web.sys_user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gym.utils.ResultUtils;
import com.gym.utils.ResultVo;
import com.gym.web.sys_user.entity.PageParm;
import com.gym.web.sys_user.entity.SysUser;
import com.gym.web.sys_user.service.SysUserService;
import com.gym.web.sys_user_role.entity.SysUserRole;
import com.gym.web.sys_user_role.service.SysUserRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    //新增员工
    @PostMapping
    public ResultVo addUser(@RequestBody SysUser sysUser) {
        //判断用户名是否存在
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.lambda().eq(SysUser::getUsername, sysUser.getUsername());
        SysUser one = sysUserService.getOne(query);
        if (one != null) {
            return ResultUtils.error("账户已经被占用");
        }
        //密码加密
        if (StringUtils.isNotEmpty(sysUser.getPassword())) {
            sysUser.setPassword(DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes()));
        }
        sysUser.setIsAdmin("0");
        sysUser.setCreateTime(new Date());
        //存入数据库
        boolean save = sysUserService.save(sysUser);
        if (save) {
            return ResultUtils.success("新增用户成功!");
        }
        return ResultUtils.error("新增用户失败!");
    }

    //编辑员工
    @PutMapping
    public ResultVo editUser(@RequestBody SysUser sysUser) {
        //判断用户名是否存在
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.lambda().eq(SysUser::getUsername, sysUser.getUsername());
        SysUser one = sysUserService.getOne(query);
        if (one != null && !one.getUserId().equals(sysUser.getUserId())) {
            return ResultUtils.error("账户已经被占用");
        }
        //密码加密
        if (StringUtils.isNotEmpty(sysUser.getPassword())) {
            sysUser.setPassword(DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes()));
        }
        sysUser.setUpdateTime(new Date());
        //存入数据库
        boolean save = sysUserService.updateById(sysUser);
        if (save) {
            return ResultUtils.success("编辑用户成功!");
        }
        return ResultUtils.error("编辑用户失败!");
    }

    //删除用户
    @DeleteMapping("/{userId}")
    public ResultVo deleteUser(@PathVariable("userId") Long userId) {
        boolean remove = sysUserService.removeById(userId);
        if (remove) {
            return ResultUtils.success("删除用户成功!");
        }
        return ResultUtils.error("删除用户失败!");
    }

    //用户列表
    @GetMapping("/list")
    public ResultVo getList(PageParm parm) {
        IPage<SysUser> list = sysUserService.getList(parm);
        if (list.getRecords().size() > 0) {
            list.getRecords().forEach(item -> {
                item.setPassword("");
            });
        }
        return ResultUtils.success("查询成功", list);
    }

    // 根据用户id查询角色id
    @GetMapping("/role")
    public ResultVo getRole(Long userId) {
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUserRole::getUserId, userId);
        SysUserRole one = sysUserRoleService.getOne(queryWrapper);
        return ResultUtils.success("查询成功", one);
    }
}
