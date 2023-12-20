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
public class ScoreForExcel {

    /**
     * 学生学号
     */
    @ExcelProperty("学生学号")
    private Integer studentNumber;

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
