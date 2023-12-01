package com.cuit.studentskilldisplaysystem.model.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class CourseForExcel {
    /**
     * 课程名称
     */
    @ExcelProperty("课程名称")
    private String courseName;

    /**
     * 课程所属技能指标名称
     */
    @ExcelProperty("课程所属技能指标名称")
    private String courseSkillIndexName;

    /**
     * 权重
     */
    @ExcelProperty("权重")
    private Double courseWeight;
}
