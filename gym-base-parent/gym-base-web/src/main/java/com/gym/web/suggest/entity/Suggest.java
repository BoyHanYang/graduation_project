package com.gym.web.suggest.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName suggest
 */
@TableName(value ="suggest")
@Data
public class Suggest implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 反馈内容
     */
    private String content;

    /**
     * 反馈时间
     */
    private Date dateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}