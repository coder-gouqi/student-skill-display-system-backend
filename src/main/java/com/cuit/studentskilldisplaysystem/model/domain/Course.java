package com.cuit.studentskilldisplaysystem.model.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName course
 */
@TableName(value ="course")
@Data
public class Course implements Serializable {
    /**
     * 课程id
     */
    @TableId(value = "id")
    private String id;

    /**
     * 课程名称
     */
    @TableField(value = "course_name")
    private String courseName;

    /**
     * 课程所属技能指标id
     */
    @TableField(value = "course_skill_index_id")
    private String courseSkillIndexId;

    /**
     * 权重
     */
    @TableField(value = "course_weight")
    private Double courseWeight;

    /**
     * 逻辑删除，0未删除，1已删除
     */
    @TableLogic(value = "is_delete")
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}