package com.gym.web.course.entity;

import lombok.Data;

/**
 * @Author yangbohan
 * @Date 2024/4/8 16:22
 */

@Data
public class CourseList {
    private Long currentPage;
    private Long pageSize;
    private String courseName;
    private String teacherName;
}