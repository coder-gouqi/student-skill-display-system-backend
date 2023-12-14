package com.cuit.studentskilldisplaysystem.service;

import com.cuit.studentskilldisplaysystem.common.DeleteRequest;
import com.cuit.studentskilldisplaysystem.model.domain.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuit.studentskilldisplaysystem.model.dto.course.CourseAddRequest;
import com.cuit.studentskilldisplaysystem.model.dto.course.CourseQueryRequest;
import com.cuit.studentskilldisplaysystem.model.dto.course.CourseUpdateRequest;
import com.cuit.studentskilldisplaysystem.model.domain.SkillIndex;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
* @description 针对表【course】的数据库操作Service
*/
public interface CourseService extends IService<Course> {

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
     *添加课程
     *
     * @return
     */
    Boolean courseAdd(CourseAddRequest courseAddRequest);

    /**
     * 修改课程
     *
     * @return
     */
    Boolean courseUpdate(CourseUpdateRequest courseUpdateRequest);

    /**
     * 查看课程
     */
    List<Course> courseSelect(CourseQueryRequest courseQueryRequest);

    Course courseSelectById(String id);
    /**
     * 删除课程
     */
    Boolean courseDelete(DeleteRequest deleteRequest);
}
