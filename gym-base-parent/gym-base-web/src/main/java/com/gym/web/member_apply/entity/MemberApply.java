package com.gym.web.member_apply.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName member_apply
 */
@TableName(value ="member_apply")
@Data
public class MemberApply implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer applyId;

    /**
     * 会员id
     */
    private Integer memberId;

    /**
     * 会员类型
     */
    private String cardType;

    /**
     * 天数
     */
    private Integer cardDay;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createUser;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}