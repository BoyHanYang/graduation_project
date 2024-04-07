package com.gym.web.member_role.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName member_role
 */
@TableName(value ="member_role")
@Data
public class MemberRole implements Serializable {
    /**
     * 会员角色id
     */
    @TableId(type = IdType.AUTO)
    private Integer memberRoleId;

    /**
     * 会员id
     */
    private Integer memberId;

    /**
     * 角色id
     */
    private Integer roleId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}