package com.gym.web.goods.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 
 * @TableName goods
 */
@TableName(value ="goods")
@Data
public class Goods implements Serializable {
    /**
     * 商品id
     */
    @TableId(type = IdType.AUTO)
    private Integer goodsId;

    /**
     * 名称
     */
    private String name;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 库存
     */
    private Integer store;

    /**
     * 图片
     */
    private String image;

    /**
     * 描述
     */
    private String details;

    /**
     * 单位
     */
    private String unit;

    /**
     * 规格
     */
    private String specs;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}