package com.cuit.studentskilldisplaysystem.model.dto.skillIndex;

import com.cuit.studentskilldisplaysystem.common.PageRequest;
import lombok.Data;

@Data
public class SkillIndexQueryRequest extends PageRequest {
    /**
     * 技能指标名称
     */
    private String skillIndexName;
}
