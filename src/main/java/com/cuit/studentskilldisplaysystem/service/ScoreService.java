package com.cuit.studentskilldisplaysystem.service;

import com.cuit.studentskilldisplaysystem.model.domain.Score;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

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
}
