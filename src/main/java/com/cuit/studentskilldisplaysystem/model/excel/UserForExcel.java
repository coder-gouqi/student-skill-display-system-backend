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
public class UserForExcel {

    /**
     * 用户姓名
     */
    @ExcelProperty("用户姓名")
    private String userName;

    /**
     * 学生学号
     */
    @ExcelProperty("学生学号")
    private Integer studentNumber;

    /**
     * 学生学院
     */
    @ExcelProperty("学生学院")
    private String studentAcademy;

    /**
     * 学生班级
     */
    @ExcelProperty("学生班级")
    private String studentClass;

    /**
     * 学生年级
     */
    @ExcelProperty("学生年级")
    private String studentGrade;

}
