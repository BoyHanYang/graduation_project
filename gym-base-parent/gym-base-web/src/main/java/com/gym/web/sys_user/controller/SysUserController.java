package com.gym.web.sys_user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gym.utils.ResultUtils;
import com.gym.utils.ResultVo;
import com.gym.web.sys_role.entity.SelectType;
import com.gym.web.sys_user.entity.PageParm;
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

    //新增
    @PreAuthorize("hasAuthority('sys:user:add')")
    @PostMapping
    public ResultVo add(@RequestBody SysUser sysUser){
        sysUser.setCreateTime(new Date());
//        if(StringUtils.isNotEmpty(sysUser.getPassword())){
//            sysUser.setPassword(DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes()));
//        }
        //设置是否是管理员
        sysUser.setIsAdmin("0");
        //判断该用户是否存在
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.lambda().eq(SysUser::getUsername,sysUser.getUsername());
        SysUser one = sysUserService.getOne(query);
        if(one != null){
            return ResultUtils.error("该账户已经被注册!");
        }
        //密码加密
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        //保存
        sysUserService.addUser(sysUser);
        return ResultUtils.success("新增成功!");
    }

    //编辑
    @PutMapping
    @PreAuthorize("hasAuthority('sys:user:edit')")
    public ResultVo edit(@RequestBody SysUser sysUser){
        sysUser.setUpdateTime(new Date());
//        if(StringUtils.isNotEmpty(sysUser.getPassword())){
//            sysUser.setPassword(DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes()));
//        }
        //判断该用户是否存在
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.lambda().eq(SysUser::getUsername,sysUser.getUsername());
        SysUser one = sysUserService.getOne(query);
        if(one != null && !sysUser.getUserId().equals(one.getUserId())){
            return ResultUtils.error("该账户已经被注册!");
        }
        //更新
        sysUserService.editUser(sysUser);
        return ResultUtils.success("更新成功!");
    }

    @PreAuthorize("hasAuthority('sys:user:delete')")
    @DeleteMapping("/{userId}")
    public ResultVo delete(@PathVariable("userId") Integer userId){
        sysUserService.deleteUser(userId);
        return ResultUtils.success("删除成功!");
    }

    @GetMapping("/list")
    public ResultVo list(PageParm parm){
        IPage<SysUser> list = sysUserService.getList(parm);
        if(list.getRecords().size() >0){
            list.getRecords().forEach(item->{
                item.setPassword("");
            });
        }
        return ResultUtils.success("查询成功",list);
    }

    //根据用户id查询角色id
    @GetMapping("/role")
    public ResultVo getRole(Long userId){
        QueryWrapper<SysUserRole> query = new QueryWrapper<>();
        query.lambda().eq(SysUserRole::getUserId,userId);
        SysUserRole one = sysUserRoleService.getOne(query);
        return ResultUtils.success("查询成功",one);
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

