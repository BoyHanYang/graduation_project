package com.gym.web.goods_order.entity;

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
 * @TableName goods_order
 */
@TableName(value ="goods_order")
@Data
public class GoodsOrder implements Serializable {
    /**
     * 订单id
     */
    @TableId(type = IdType.AUTO)
    private Integer orderId;

    /**
     * 商品id
     */
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

    /**
     * 数量
     */
    private Integer num;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 操作人
     */
    private String controlUser;

    /**
     * 下单时间
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}