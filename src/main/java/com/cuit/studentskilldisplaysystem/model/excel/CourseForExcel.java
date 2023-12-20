package com.cuit.studentskilldisplaysystem.model.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

@Data
@ContentRowHeight(30)
@HeadRowHeight(20)
@ColumnWidth(25)
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
