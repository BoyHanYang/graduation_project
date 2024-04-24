package com.gym.web.member_course.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.web.course.entity.Course;
import com.gym.web.course.service.CourseService;
import com.gym.web.member.entity.RechargeParm;
import com.gym.web.member.mapper.MemberMapper;
import com.gym.web.member_course.entity.MemberCourse;
import com.gym.web.member_course.service.MemberCourseService;
import com.gym.web.member_course.mapper.MemberCourseMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author AdminHan
 * @description 针对表【member_course】的数据库操作Service实现
 * @createDate 2024-04-19 16:25:37
 */
@Service
public class MemberCourseServiceImpl extends ServiceImpl<MemberCourseMapper, MemberCourse>
        implements MemberCourseService {
    @Autowired
    private CourseService courseService;
    @Resource
    private MemberMapper memberMapper;

    @Override
    @Transactional
    public void joinCourse(MemberCourse memberCourse) {
        Course course = courseService.getById(memberCourse.getCourseId());
        BeanUtils.copyProperties(course, memberCourse);
        //插入报名表
        int insert = this.baseMapper.insert(memberCourse);
        if (insert > 0) {
            RechargeParm parm = new RechargeParm();
            parm.setMemberId(memberCourse.getMemberId());
            parm.setMoney(course.getCoursePrice());
            memberMapper.subMoney(parm);
        }
    }
}




