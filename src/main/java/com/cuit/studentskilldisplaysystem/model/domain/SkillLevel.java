package com.cuit.studentskilldisplaysystem.model.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName skill_level
 */
@TableName(value ="skill_level")
@Data
public class SkillLevel implements Serializable {
    /**
     * 技能等级id
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 技能等级
     */
    @TableField(value = "skill_level")
    private String skillLevel;

    /**
     * 结束值
     */
    @TableField(value = "end")
    private Integer end;

    /**
     * 开始值
     */
    @TableField(value = "start")
    private Integer start;

    /**
     * 逻辑删除，0未删除，1已删除
     */
    @TableLogic(value = "is_delete")
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}