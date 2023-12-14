package com.cuit.studentskilldisplaysystem.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName skill_level
 */
@TableName(value = "skill_level")
@Data
public class SkillLevel implements Serializable {
    /**
     * 技能等级id
     */
    @TableId(value = "id")
    private String id;

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
    @TableField(value = "is_delete")
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}