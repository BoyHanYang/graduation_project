package com.gym.web.course.entity;

import lombok.Data;

/**
 * @Author yangbohan
 * @Date 2024/4/19 16:40
 */
@Data
public class PageParm {
    private Integer pageSize;
    private Integer currentPage;
    private String userType;
    private Integer userId;
}
