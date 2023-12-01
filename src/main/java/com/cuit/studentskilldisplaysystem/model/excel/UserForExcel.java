package com.cuit.studentskilldisplaysystem.model.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class UserForExcel {

    /**
     * 用户姓名
     */
    @ExcelProperty("用户姓名")
    private String userName;

    /**
     * 用户角色
     */
    @ExcelProperty("用户角色")
    private String userRole;

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
    @ExcelProperty("学生班级")
    private String studentGrade;

    /**
     * 学生头像
     */
    @ExcelProperty("学生头像")
    private String studentPhoto;
}
