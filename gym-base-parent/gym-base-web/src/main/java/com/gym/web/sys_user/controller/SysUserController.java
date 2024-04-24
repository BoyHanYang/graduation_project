package com.gym.web.sys_user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gym.utils.ResultUtils;
import com.gym.utils.ResultVo;
import com.gym.web.sys_user.entity.PageParm;
import com.gym.web.sys_user.entity.SelectType;
import com.gym.web.sys_user.entity.SysUser;
import com.gym.web.sys_user.service.SysUserService;
import com.gym.web.sys_user_role.entity.SysUserRole;
import com.gym.web.sys_user_role.service.SysUserRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    //新增员工
    @PostMapping
    @PreAuthorize("hasAuthority('sys:user:add')")
    public ResultVo addUser(@RequestBody SysUser sysUser) {
        sysUser.setCreateTime(new Date());
        sysUser.setIsAdmin("0");
        //判断用户名是否存在
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.lambda().eq(SysUser::getUsername, sysUser.getUsername());
        SysUser one = sysUserService.getOne(query);
        if (one != null) {
            return ResultUtils.error("账户已经被占用");
        }
        //密码加密
        /*if (StringUtils.isNotEmpty(sysUser.getPassword())) {
            sysUser.setPassword(DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes()));
        }
        sysUser.setIsAdmin("0");
        sysUser.setCreateTime(new Date());*/
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        //存入数据库
//        boolean save = sysUserService.save(sysUser);
        sysUserService.addUser(sysUser);
        /*if (save) {
            return ResultUtils.success("新增用户成功!");
        }
        return ResultUtils.error("新增用户失败!");*/
        return ResultUtils.success("新增用户成功!");
    }

    //编辑员工
    @PutMapping
    @PreAuthorize("hasAuthority('sys:user:edit')")
    public ResultVo editUser(@RequestBody SysUser sysUser) {
        sysUser.setUpdateTime(new Date());
        //判断用户名是否存在
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.lambda().eq(SysUser::getUsername, sysUser.getUsername());
        SysUser one = sysUserService.getOne(query);
        if (one != null && !one.getUserId().equals(sysUser.getUserId())) {
            return ResultUtils.error("账户已经被注册");
        }
        /*//密码加密
        if (StringUtils.isNotEmpty(sysUser.getPassword())) {
            sysUser.setPassword(DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes()));
        }
        sysUser.setUpdateTime(new Date());
        //存入数据库
        boolean save = sysUserService.updateById(sysUser);
        if (save) {
            return ResultUtils.success("编辑用户成功!");
        }
        return ResultUtils.error("编辑用户失败!");*/
        // 更新
        sysUserService.editUser(sysUser);
        return ResultUtils.success("编辑用户成功!");
    }

    //删除用户
    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('sys:user:delete')")
    public ResultVo deleteUser(@PathVariable("userId") Integer userId) {
        /*boolean remove = sysUserService.removeById(userId);
        if (remove) {
            return ResultUtils.success("删除用户成功!");
        }
        return ResultUtils.error("删除用户失败!");*/
        sysUserService.deleteUser(userId);
        return ResultUtils.success("删除用户成功!");
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
    //查询课程教练
    @GetMapping("/getTeacher")
    public ResultVo getTeacher(){
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.lambda().eq(SysUser::getUserType,"2");
        List<SysUser> list = sysUserService.list(query);
        //组装后的select数据
        List<SelectType> selectTypeList = new ArrayList<>();
        if(list.size() >0){
            list.stream().forEach(item ->{
                SelectType selectType = new SelectType();
                selectType.setLabel(item.getNickName());
                selectType.setValue(item.getUserId());
                selectTypeList.add(selectType);
            });
        }
        return ResultUtils.success("查询成功",selectTypeList);
    }
}
