package com.cuit.studentskilldisplaysystem.model.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

@Data
public class CourseForExcel {
    /**
     * 课程名称
     */
    @ExcelProperty("课程名称")
    @ColumnWidth(20)
    private String courseName;

    /**
     * 课程所属技能指标名称
     */
    @ExcelProperty("课程所属技能指标名称")
    @ColumnWidth(20)
    private String courseSkillIndexName;

    /**
     * 权重
     */
    @ExcelProperty("权重")
    @ColumnWidth(20)
    private Double courseWeight;
}
