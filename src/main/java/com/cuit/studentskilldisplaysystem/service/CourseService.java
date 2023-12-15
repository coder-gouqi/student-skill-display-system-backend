package com.cuit.studentskilldisplaysystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.studentskilldisplaysystem.common.DeleteRequest;
import com.cuit.studentskilldisplaysystem.model.domain.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuit.studentskilldisplaysystem.model.domain.SkillIndex;
import com.cuit.studentskilldisplaysystem.model.domain.User;
import com.cuit.studentskilldisplaysystem.model.dto.course.CourseQueryRequest;
import com.cuit.studentskilldisplaysystem.model.dto.skillIndex.SkillIndexQueryRequest;
import com.cuit.studentskilldisplaysystem.model.dto.user.UserQueryRequest;
import com.cuit.studentskilldisplaysystem.model.vo.CourseVo;
import com.cuit.studentskilldisplaysystem.model.vo.UserVo;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
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
     * 查询所有课程的数据
     *
     * @return
     */
    List<Course> selectAll();

    /**
     * 增加课程
     * @param course
     * @return
     */
    Boolean courseAdd(Course course);

    /**
     * 更新课程
     * @param course
     * @return
     */
    Boolean courseUpdate(Course course);

    /**
     * 删除课程
     * @param course
     * @return
     */
    Boolean courseDelete(Course course);

    /**
     * 查询课程信息（联表查询并分页）
     *
     * @param courseVoPage
     * @param courseVoClass
     * @param courseMPJLambdaWrapper
     * @return
     */
    IPage<CourseVo> selectCourseJoinPage(Page<CourseVo> courseVoPage, Class<CourseVo> courseVoClass, MPJLambdaWrapper<Course> courseMPJLambdaWrapper);

    /**
     * 获取查询条件
     *
     * @param courseQueryRequest
     * @return
     */
    MPJLambdaWrapper<Course> getQueryWrapper(CourseQueryRequest courseQueryRequest);
}
