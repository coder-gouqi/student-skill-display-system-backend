package com.cuit.studentskilldisplaysystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cuit.studentskilldisplaysystem.model.domain.Score;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @description 针对表【score】的数据库操作Service
 */
public interface ScoreService extends IService<Score> {
    /**
     * 查询所有学生成绩的数据
     *
     * @return
     */
    List<Score> selectAll();

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
}
