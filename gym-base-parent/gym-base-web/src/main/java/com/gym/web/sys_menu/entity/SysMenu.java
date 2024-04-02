package com.gym.web.sys_menu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 
 * @TableName sys_menu
 */
@TableName(value ="sys_menu")
@Data
public class SysMenu implements Serializable {
    /**
     * 菜单id
     */
    @TableId(type = IdType.AUTO)
    private Integer menuId;

    /**
     * 父级id
     */
    private Integer parentId;

    /**
     * 菜单名称
     */
    private String title;

    /**
     * 权限字段
     */
    private String code;

    /**
     * 路由名称
     */
    private String name;

    /**
     * 路由path
     */
    private String path;

    /**
     * 组件路径
     */
    private String url;

    /**
     * 类型(0 目录 1菜单，2按钮)
     */
    private String type;

    /**
     * 图标
     */
    private String icon;

    /**
     * 上级菜单名称
     */
    private String parentName;

    /**
     * 序号
     */
    private Integer orderNum;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
    @TableField(exist = false)
    private List<SysMenu> children = new ArrayList<>();

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}