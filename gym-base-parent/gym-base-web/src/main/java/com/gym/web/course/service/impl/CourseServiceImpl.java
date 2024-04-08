package com.gym.web.course.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.web.course.entity.Course;
import com.gym.web.course.service.CourseService;
import com.gym.web.course.mapper.CourseMapper;
import org.springframework.stereotype.Service;

/**
* @author AdminHan
* @description 针对表【course】的数据库操作Service实现
* @createDate 2024-04-08 16:12:49
*/
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
    implements CourseService{

}




