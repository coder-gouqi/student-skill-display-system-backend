package com.cuit.studentskilldisplaysystem.model.dto.skillLevel;

import com.cuit.studentskilldisplaysystem.common.PageRequest;
import lombok.Data;

@Data
public class SkillLevelQueryRequest extends PageRequest {
    /**
     * 技能等级
     */
    private String skillLevel;

    /**
     * 结束值
     */
    private Integer end;

    /**
     * 开始值
     */
    private Integer start;
}
