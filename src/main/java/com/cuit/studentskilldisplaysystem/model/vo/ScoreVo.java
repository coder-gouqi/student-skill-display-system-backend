package com.cuit.studentskilldisplaysystem.model.vo;

import lombok.Data;

@Data
public class ScoreVo {

    /**
     * 成绩id
     */
    private String id;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 学生成绩
     */
    private Double studentScore;
}
