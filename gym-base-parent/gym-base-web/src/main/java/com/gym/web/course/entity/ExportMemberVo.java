package com.gym.web.course.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * @Author yangbohan
 * @Date 2024/4/24 1:23
 */

@Data
public class ExportMemberVo {
    @Excel(name = "姓名", orderNum = "1", width=30)
    private String name;
    @Excel(name = "性别", replace = {"男_0", "女_1"}, orderNum = "1", width=30)
    private String sex;
    @Excel(name = "电话", orderNum = "1", width=50)
    private String phone;
    @Excel(name = "年龄", orderNum = "1", width=30)
    private Integer age;
    @Excel(name = "身高", orderNum = "1", width=30)
    private Integer height;
    @Excel(name = "体重", orderNum = "1", width=30)
    private Integer weight;
    @Excel(name = "腰围", orderNum = "1", width=30)
    private String waist;
}