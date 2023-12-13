package com.cuit.studentskilldisplaysystem.service;

import com.cuit.studentskilldisplaysystem.model.domain.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuit.studentskilldisplaysystem.model.domain.SkillIndex;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
* @description 针对表【course】的数据库操作Service
*/
public interface CourseService extends IService<Course> {
    /**
     * 查询所有课程的数据
     *
     * @return
     */
    List<Course> selectAll();

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
