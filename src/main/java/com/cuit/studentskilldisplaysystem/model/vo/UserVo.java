package com.cuit.studentskilldisplaysystem.model.vo;

import lombok.Data;

@Data
public class UserVo {

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 用户角色
     */
    private String userRole;

    /**
     * 学生学号
     */
    private Integer studentNumber;

    /**
     * 学生学院
     */
    private String studentAcademy;

    /**
     * 学生班级
     */
    private String studentClass;

    /**
     * 学生年级
     */
    private String studentGrade;

    /**
     * 学生头像
     */
    private String studentPhoto;
}
