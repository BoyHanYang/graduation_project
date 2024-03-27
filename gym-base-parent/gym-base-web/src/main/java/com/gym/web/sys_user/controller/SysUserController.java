package com.gym.web.sys_user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gym.web.sys_user.entity.PageParm;
import com.gym.web.sys_user.entity.SysUser;
import com.gym.web.sys_user.service.SysUserService;
import com.itmk.utils.ResultUtils;
import com.itmk.utils.ResultVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @Author yangbohan
 * @Date 2024/3/27 17:41
 */
@RestController
@RequestMapping("/api/user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    // 添加
    @PostMapping
    public ResultVo add(@RequestBody SysUser sysUser) {
        sysUser.setCreateTime(new Date());
        if (StringUtils.isNotEmpty(sysUser.getPassword())){
            sysUser.setPassword(DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes()));
        }
        // 设置是否是管理员
        sysUser.setIsAdmin("0");
        // 判断用户是否存在
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUser::getUsername,sysUser.getUsername());
        SysUser one = sysUserService.getOne(queryWrapper);
        if (one != null){
            return ResultUtils.error("该账户已被注册");
        }
        // 保存
        sysUserService.addUser(sysUser);
        return ResultUtils.success("添加成功");

    }
    // 编辑
    @PutMapping
    public ResultVo edit(@RequestBody SysUser sysUser) {
        sysUser.setCreateTime(new Date());
        if (StringUtils.isNotEmpty(sysUser.getPassword())){
            sysUser.setPassword(DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes()));
        }
        // 判断用户是否存在
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUser::getUsername,sysUser.getUsername());
        SysUser one = sysUserService.getOne(queryWrapper);
        if (one != null&& sysUser.getUserId().equals(one.getUserId())){
            return ResultUtils.error("该账户已被注册");
        }
        // 更新
        sysUserService.updateById(sysUser);
        return ResultUtils.success("更新成功");
    }
    // 删除
    @DeleteMapping("/{userId}")
    public ResultVo delete(@PathVariable("userId") Long userId) {
        sysUserService.deleteUser(userId);
        return ResultUtils.success("删除成功");
    }
    // 查询列表
    @GetMapping
    public ResultVo getList(PageParm parm) {
        IPage<SysUser> list = sysUserService.getList(parm);
        return ResultUtils.success("查询成功",list);
    }
}
