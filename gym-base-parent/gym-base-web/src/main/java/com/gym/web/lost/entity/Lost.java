package com.gym.web.lost.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName lost
 */
@TableName(value ="lost")
@Data
public class Lost implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer lostId;

    /**
     * 名称
     */
    private String lostName;

    /**
     * 捡到时间
     */
    private String foundTime;

    /**
     * 捡到地址
     */
    private String foundAddres;

    /**
     * 捡到人
     */
    private String foundPerson;

    /**
     * 捡到人电话
     */
    private String foundPhone;

    /**
     *  认领状态 0：未认领 1：已认领
     */
    private String status;

    /**
     * 认领人
     */
    private String lostPerson;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}