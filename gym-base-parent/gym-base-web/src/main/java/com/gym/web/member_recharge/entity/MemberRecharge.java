package com.gym.web.member_recharge.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * @TableName member_recharge
 */
@TableName(value ="member_recharge")
@Data
public class MemberRecharge implements Serializable {
    /**
     * 充值id
     */
    @TableId(type = IdType.AUTO)
    private Integer rechargeId;

    /**
     * 会员id
     */
    private Integer memberId;

    /**
     * 充值金额
     */
    private BigDecimal money;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;

    /**
     * 创建人
     */
    private String createUser;
    @TableField(exist = false)
    private String name;
    @TableField(exist = false)
    private String username;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}