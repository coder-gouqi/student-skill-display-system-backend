package com.cuit.studentskilldisplaysystem.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName skill_index
 */
@TableName(value = "skill_index")
@Data
public class SkillIndex implements Serializable {
    /**
     * 技能指标id
     */
    @TableId(value = "id")
    private String id;

    /**
     * 技能指标名称
     */
    @TableField(value = "skill_index_name")
    private String skillIndexName;

    /**
     * 逻辑删除，0未删除，1已删除
     */
    @TableField(value = "is_delete")
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}