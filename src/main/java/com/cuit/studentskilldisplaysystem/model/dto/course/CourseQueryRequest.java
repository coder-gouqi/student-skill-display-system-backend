package com.cuit.studentskilldisplaysystem.model.dto.course;

import com.cuit.studentskilldisplaysystem.common.PageRequest;
import lombok.Data;

@Data
public class CourseQueryRequest extends PageRequest {
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

    /**
     *权重
     */
    private  Double courseWeight;
}
