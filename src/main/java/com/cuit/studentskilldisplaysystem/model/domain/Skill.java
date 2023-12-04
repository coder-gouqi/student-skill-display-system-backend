package com.cuit.studentskilldisplaysystem.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName skill
 */
@TableName(value = "skill")
@Data
public class Skill implements Serializable {
    /**
     * 技能展示id
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 学生姓名
     */
    @TableField(value = "student_id")
    private String studentId;

    /**
     * 学生技能成绩（json）
     */
    @TableField(value = "student_skill_score")
    private String studentSkillScore;

    /**
     * 逻辑删除，0未删除，1已删除
     */
    @TableField(value = "is_delete")
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}