package com.cuit.studentskilldisplaysystem.model.dto.course;

import lombok.Data;

@Data
public class CourseAddRequest {
    /**
     * 课程id
     */
    private String id;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     *课程所属技能指标id
     */
    private String courseSkillIndexId;
}
