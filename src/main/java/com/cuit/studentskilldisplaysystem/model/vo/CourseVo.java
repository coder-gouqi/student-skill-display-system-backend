package com.cuit.studentskilldisplaysystem.model.vo;

import lombok.Data;

@Data
public class CourseVo {

    /**
     * 课程id
     */
    private String id;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 课程所属技能指标名称
     */
    private String courseSkillIndexName;

    /**
     * 权重
     */
    private Double courseWeight;
}
