package com.cuit.studentskilldisplaysystem.model.dto.score;

import com.cuit.studentskilldisplaysystem.common.PageRequest;
import lombok.Data;

@Data
public class ScoreQueryRequest extends PageRequest {
    /**
     * 成绩
     */
    private String id;

    /**
     * 学生
     */
    private String studentId;

    /**
     *课程id
     */
    private String courseId;

    /**
     *成绩
     */
    private Double studentScore;
}
