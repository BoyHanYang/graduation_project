package com.gym.web.member.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @TableName member
 */
@TableName(value = "member")
@Data
public class Member implements UserDetails {
    /**
     * 会员id
     */
    @TableId(type = IdType.AUTO)
    private Integer memberId;
    @TableField(exist = false)
    private Integer roleId;
    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 电话
     */
    private String phone;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 身高
     */
    private Integer height;

    /**
     * 体重
     */
    private Integer weight;

    /**
     * 腰围
     */
    private Integer waist;

    /**
     * 加入时间
     */
    private String joinTime;

    /**
     * 到期时间
     */
    private String endTime;

    /**
     * 会员卡号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态 1：启用  0：停用
     */
    private String status;

    /**
     * 充值金额
     */
    private BigDecimal money;

    /**
     * 会员类型
     */
    private String cardType;

    /**
     * 天数
     */
    private Integer cardDay;

    /**
     * 会员价格
     */
    private BigDecimal price;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    //用户权限字段的集合
    //表明authorities字段不属于sys_user表，需要排除
    @TableField(exist = false)
    Collection<? extends GrantedAuthority> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}