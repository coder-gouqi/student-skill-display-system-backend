package com.cuit.studentskilldisplaysystem.model.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ScoreForExcel {
    /**
     * 学生姓名
     */
    @ExcelProperty("学生姓名")
    private String studentName;

    /**
     * 课程名称
     */
    @ExcelProperty("课程名称")
    private String courseName;

    /**
     * 学生成绩
     */
    @ExcelProperty("学生成绩")
    private Double studentScore;
}
