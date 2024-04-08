package com.gym.web.sys_user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * @TableName sys_user
 */
@TableName(value = "sys_user")
@Data
public class SysUser implements Serializable {
    /**
     * 员工id
     */
    @TableId(type = IdType.AUTO)
    private Integer userId;
    // 表明roleId字段不属于sys_user表，需要排除
    @TableField(exist = false)
    private Integer roleId;
    /**
     * 账户(员工编号)
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别  0：男 1：女
     */
    private String sex;

    /**
     * 工资
     */
    private BigDecimal salary;

    /**
     * 类型（1：员工 2：教练）
     */
    private String userType;

    /**
     * 状态 0：停用 1：启用
     */
    private String status;

    /**
     * 是否是管理员 （1：是 0 ：否）
     */
    private String isAdmin;

    /**
     * 帐户是否过期(1 未过期，0已过期)
     */
    private Integer isAccountNonExpired;

    /**
     * 帐户是否被锁定(1 未锁定，0已锁定)
     */
    private Integer isAccountNonLocked;

    /**
     * 密码是否过期(1 未过期，0已过期)
     */
    private Integer isCredentialsNonExpired;

    /**
     * 帐户是否可用(1 可用，0 删除用户)
     */
    private Integer isEnabled;

    /**
     * 姓名
     */
    private String nickName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}