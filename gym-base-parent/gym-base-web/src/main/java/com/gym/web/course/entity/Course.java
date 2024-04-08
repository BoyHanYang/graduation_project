package com.gym.web.course.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 
 * @TableName course
 */
@TableName(value ="course")
@Data
public class Course implements Serializable {
    /**
     * 课程id
     */
    @TableId(type = IdType.AUTO)
    private Integer courseId;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 封面图
     */
    private String image;

    /**
     * 授课教师
     */
    private String teacherName;

    /**
     * 课时
     */
    private Integer courseHour;

    /**
     * 课程详情
     */
    private String courseDetails;

    /**
     * 课程价格
     */
    private BigDecimal coursePrice;

    /**
     * 教师
     */
    private Integer teacherId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}