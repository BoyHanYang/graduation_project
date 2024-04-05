package com.gym.web.member_card.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 
 * @TableName member_card
 */
@TableName(value ="member_card")
@Data
public class MemberCard implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer cardId;

    /**
     * 标题
     */
    private String title;

    /**
     * 卡类型  1:天卡  2：周卡 3：月卡 4：年卡
     */
    private String cardType;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 天数
     */
    private Integer cardDay;

    /**
     * 状态 1：启用 0：停用
     */
    private String status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}