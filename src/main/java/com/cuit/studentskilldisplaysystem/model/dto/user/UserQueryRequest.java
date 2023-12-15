package com.cuit.studentskilldisplaysystem.model.dto.user;

import com.cuit.studentskilldisplaysystem.common.PageRequest;
import lombok.Data;

@Data
public class UserQueryRequest extends PageRequest {
    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 学生学号
     */
    private Integer studentNumber;

    /**
     * 学生学院
     */
    private String studentAcademyId;
}
