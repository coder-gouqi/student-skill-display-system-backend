package com.cuit.studentskilldisplaysystem.model.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 用户id
     */
    @TableId(value = "id")
    private String id;

    /**
     * 用户姓名
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 用户账号
     */
    @TableField(value = "user_account")
    private String userAccount;

    /**
     * 用户密码
     */
    @TableField(value = "user_password")
    private String userPassword;

    /**
     * 用户角色
     */
    @TableField(value = "user_role")
    private String userRole;

    /**
     * 学生学号
     */
    @TableField(value = "student_number")
    private Integer studentNumber;

    /**
     * 学生学院
     */
    @TableField(value = "student_academy_id")
    private String studentAcademyId;

    /**
     * 学生班级
     */
    @TableField(value = "student_class")
    private String studentClass;

    /**
     * 学生年级
     */
    @TableField(value = "student_grade")
    private String studentGrade;

    /**
     * 学生头像
     */
    @TableField(value = "student_photo")
    private String studentPhoto;

    /**
     * 逻辑删除，0未删除，1已删除
     */
    @TableLogic(value = "is_delete")
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}