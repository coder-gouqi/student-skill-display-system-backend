package com.cuit.studentskilldisplaysystem.model.dto.skill;

import com.cuit.studentskilldisplaysystem.common.PageRequest;
import lombok.Data;

@Data
public class SkillQueryRequest extends PageRequest {

    /**
     * 学生id
     */
    private String studentId;
}
