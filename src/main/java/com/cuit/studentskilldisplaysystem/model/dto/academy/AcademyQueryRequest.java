package com.cuit.studentskilldisplaysystem.model.dto.academy;

import com.cuit.studentskilldisplaysystem.common.PageRequest;
import lombok.Data;

@Data
public class AcademyQueryRequest extends PageRequest {
    /**
     * 学院名称
     */
    private String academyName;

    /**
     * 学院简介
     */
    private String academyInfo;
}
