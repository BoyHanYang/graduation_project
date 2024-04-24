package com.gym.web.member_course.service;

import com.gym.web.member_course.entity.MemberCourse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author AdminHan
* @description 针对表【member_course】的数据库操作Service
* @createDate 2024-04-19 16:25:37
*/
public interface MemberCourseService extends IService<MemberCourse> {
    void joinCourse(MemberCourse memberCourse);
}
