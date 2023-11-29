package com.cuit.studentskilldisplaysystem.model.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName score
 */
@TableName(value ="score")
@Data
public class Score implements Serializable {
    /**
     * 成绩id
     */
    @TableId(value = "id")
    private String id;

    /**
     * 学生id
     */
    @TableField(value = "student_id")
    private String studentId;

    /**
     * 课程id
     */
    @TableField(value = "course_id")
    private String courseId;

    /**
     * 学生成绩
     */
    @TableField(value = "student_score")
    private Double studentScore;

    /**
     * 逻辑删除，0未删除，1已删除
     */
    @TableLogic(value = "is_delete")
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}