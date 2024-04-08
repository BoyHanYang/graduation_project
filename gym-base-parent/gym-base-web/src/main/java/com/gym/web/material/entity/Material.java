package com.gym.web.material.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName material
 */
@TableName(value ="material")
@Data
public class Material implements Serializable {
    /**
     * 器材id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 器材名称
     */
    private String name;

    /**
     * 数量
     */
    private Integer numTotal;

    /**
     * 简介
     */
    private String details;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}