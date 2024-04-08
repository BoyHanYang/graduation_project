package com.gym.web.course.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gym.utils.ResultUtils;
import com.gym.utils.ResultVo;
import com.gym.web.course.entity.Course;
import com.gym.web.course.entity.CourseList;
import com.gym.web.course.service.CourseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @Author yangbohan
 * @Date 2024/4/8 16:16
 */
@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    // 新增
    @PostMapping
    public ResultVo add(@RequestBody Course course){
        if (courseService.save(course)){
            return ResultUtils.success("新增成功");
        }
        return ResultUtils.error("新增失败");
    }
    // 编辑
    @PutMapping
    public ResultVo edit(@RequestBody Course course){
        if (courseService.updateById(course)){
            return ResultUtils.success("编辑成功");
        }
        return ResultUtils.error("编辑失败");
    }
    // 删除
    @DeleteMapping("/{coureId}")
    public ResultVo delete(@PathVariable Long coureId){
        if (courseService.removeById(coureId)){
            return ResultUtils.success("删除成功");
        }
        return ResultUtils.error("删除失败");
    }
    // 列表
    @GetMapping("/list")
    public ResultVo list(CourseList courseList){
        Page<Course> page = new Page<>(courseList.getCurrentPage(),courseList.getPageSize());
        // 构造查询条件
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(courseList.getCourseName())){
            queryWrapper.lambda().like(Course::getCourseName,courseList.getCourseName());
        }
        if (StringUtils.isNotEmpty(courseList.getTeacherName())){
            queryWrapper.lambda().like(Course::getTeacherName,courseList.getTeacherName());
        }
        Page<Course> list = courseService.page(page, queryWrapper);
        return ResultUtils.success("查询成功",list);
    }
}
