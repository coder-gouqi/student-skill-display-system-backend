package com.cuit.studentskilldisplaysystem.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName academy
 */
@TableName(value = "academy")
@Data
public class Academy implements Serializable {
    /**
     * 学院id
     */
    @TableId(value = "id")
    private String id;

    /**
     * 学院名称
     */
    @TableField(value = "academy_name")
    private String academyName;

    /**
     * 学院简介
     */
    @TableField(value = "academy_info")
    private String academyInfo;

    /**
     * 学院头像
     */
    @TableField(value = "academy_photo")
    private String academyPhoto;

    /**
     * 逻辑删除，0未删除，1已删除
     */
    @TableField(value = "is_delete")
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}