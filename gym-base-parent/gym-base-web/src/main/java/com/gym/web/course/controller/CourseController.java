package com.gym.web.course.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gym.utils.ResultUtils;
import com.gym.utils.ResultVo;
import com.gym.web.course.entity.Course;
import com.gym.web.course.entity.CourseList;
import com.gym.web.course.entity.ExportMemberVo;
import com.gym.web.course.entity.PageParm;
import com.gym.web.course.service.CourseService;
import com.gym.web.member.entity.Member;
import com.gym.web.member.service.MemberService;
import com.gym.web.member_course.entity.MemberCourse;
import com.gym.web.member_course.service.MemberCourseService;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author yangbohan
 * @Date 2024/4/8 16:16
 */
@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private MemberCourseService memberCourseService;
    @Autowired
    private MemberService memberService;

    //新增
    @PreAuthorize("hasAuthority('sys:courseList:add')")
    @PostMapping
    public ResultVo add(@RequestBody Course course) {
        if (courseService.save(course)) {
            return ResultUtils.success("新增成功!");
        }
        return ResultUtils.error("新增失败!");
    }

    //编辑
    @PreAuthorize("hasAuthority('sys:courseList:edit')")
    @PutMapping
    public ResultVo edit(@RequestBody Course course) {
        if (courseService.updateById(course)) {
            return ResultUtils.success("编辑成功!");
        }
        return ResultUtils.error("编辑失败!");
    }

    //删除
    @PreAuthorize("hasAuthority('sys:courseList:delete')")
    @DeleteMapping("/{courseId}")
    public ResultVo delete(@PathVariable("courseId") Long courseId) {
        if (courseService.removeById((courseId))) {
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("删除失败!");
    }

    //列表
    @GetMapping("/list")
    public ResultVo list(CourseList courseList) {
        //构造分页对象
        IPage<Course> page = new Page<>(courseList.getCurrentPage(), courseList.getPageSize());
        //构造查询条件
        QueryWrapper<Course> query = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(courseList.getCourseName())) {
            query.lambda().like(Course::getCourseName, courseList.getCourseName());
        }
        if (StringUtils.isNotEmpty(courseList.getTeacherName())) {
            query.lambda().like(Course::getTeacherName, courseList.getTeacherName());
        }
        IPage<Course> list = courseService.page(page, query);
        return ResultUtils.success("查询成功", list);
    }

    //报名
    @PreAuthorize("hasAuthority('sys:courseList:join')")
    @PostMapping("/joinCourse")
    public ResultVo joinCourse(@RequestBody MemberCourse memberCourse) {
        //查询是否报名该课程
        QueryWrapper<MemberCourse> query = new QueryWrapper<>();
        query.lambda().eq(MemberCourse::getCourseId, memberCourse.getCourseId())
                .eq(MemberCourse::getMemberId, memberCourse.getMemberId());
        MemberCourse one = memberCourseService.getOne(query);
        if (one != null) {
            return ResultUtils.error("您已经报名该课程,不用重复报名!");
        }
        //检查会员的金额是否充足
        Course course = courseService.getById(memberCourse.getCourseId());
        Member member = memberService.getById(memberCourse.getMemberId());
        int flag = member.getMoney().compareTo(course.getCoursePrice());
        if (flag == -1) {
            return ResultUtils.error("您的充值金额不足，请充值!");
        }
        memberCourseService.joinCourse(memberCourse);
        return ResultUtils.success("报名成功!");
    }

    //我的课程列表
    @GetMapping("/getMyCourseList")
    public ResultVo getMyCourseList(PageParm pageParm) {
        if (pageParm.getUserType().equals("1")) { //会员
            IPage<MemberCourse> page = new Page<>(pageParm.getCurrentPage(), pageParm.getPageSize());
            QueryWrapper<MemberCourse> query = new QueryWrapper<>();
            query.lambda().eq(MemberCourse::getMemberId, pageParm.getUserId());
            IPage<MemberCourse> list = memberCourseService.page(page, query);
            return ResultUtils.success("查询成功", list);
        } else {
            IPage<Course> page = new Page<>(pageParm.getCurrentPage(), pageParm.getPageSize());
            QueryWrapper<Course> query = new QueryWrapper<>();
            query.lambda().eq(Course::getTeacherId, pageParm.getUserId());
            IPage<Course> list = courseService.page(page, query);
            return ResultUtils.success("查询成功", list);
        }
    }

    @RequestMapping("/exportStuInfo")
    @PreAuthorize("hasAuthority('sys:mycourse:export')")
    public void exportStuInfo(HttpServletResponse response, Long teacherId, Long courseId) throws Exception {
        List<ExportMemberVo> memberVoList = new ArrayList<>();
        //根据教师和课程id查询学生id
        QueryWrapper<MemberCourse> query = new QueryWrapper<>();
        query.lambda().eq(MemberCourse::getCourseId, courseId).eq(MemberCourse::getTeacherId, teacherId);
        query.select("member_id");
        List<MemberCourse> list = memberCourseService.list(query);
        //学生id
        List<Integer> ids = list.stream().map(MemberCourse::getMemberId).collect(Collectors.toList());
        if (ids.size() > 0) {
            List<Member> members = memberService.listByIds(ids);
            //组装数据
            for (int i = 0; i < members.size(); i++) {
                ExportMemberVo vo = new ExportMemberVo();
                BeanUtils.copyProperties(members.get(i), vo);
                memberVoList.add(vo);
            }
        }
        //导出
        String fileName = "学生信息.xlsx";
        ExportParams exportParams = new ExportParams();
        exportParams.setType(ExcelType.XSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, ExportMemberVo.class, memberVoList);
        downloadExcel(fileName, workbook, response);

    }

    public static void downloadExcel(String fileName, Workbook workbook, HttpServletResponse response) throws Exception {
        try {
            if (StringUtils.isEmpty(fileName)) {
                throw new RuntimeException("导出文件名不能为空");
            }
            String encodeFileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel; charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + encodeFileName);
            response.setHeader("FileName", encodeFileName);
            response.setHeader("Access-Control-Expose-Headers", "FileName");
            workbook.write(response.getOutputStream());
            workbook.close();
        } catch (Exception e) {
            workbook.close();
        }
    }
}
