package com.cuit.studentskilldisplaysystem.service;

import com.cuit.studentskilldisplaysystem.common.DeleteRequest;
import com.cuit.studentskilldisplaysystem.model.domain.Score;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuit.studentskilldisplaysystem.model.dto.score.ScoreAddRequest;
import com.cuit.studentskilldisplaysystem.model.dto.score.ScoreQueryRequest;
import com.cuit.studentskilldisplaysystem.model.dto.score.ScoreUpdateRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
* @description 针对表【score】的数据库操作Service
*/
public interface ScoreService extends IService<Score> {
    /**
     * 导入数据
     *
     * @param file
     * @return
     */
    Boolean importData(MultipartFile file);

    /**
     * 导出数据
     *
     * @return
     */
    Boolean exportData(HttpServletResponse response);

    /**
     * 添加成绩
     *
     * @return
     */
    Boolean scoreAdd(ScoreAddRequest scoreAddRequest);

    /**
     * 修改成绩
     *
     * @return
     */
    Boolean scoreUpdate(ScoreUpdateRequest scoreUpdateRequest);

    /**
     * 查看成绩
     */
    List<Score> scoreSelect(ScoreQueryRequest scoreQueryRequest);

    /**
     * 删除成绩
     */
    Boolean scoreDelete(DeleteRequest deleteRequest);

    /**
     * 通过id查询成绩
     *
     * @param id
     * @return
     */
    Score selectById(String id);
}
