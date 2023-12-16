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
     * @param courseVoPage  用于分页的对象，可能是用于存储课程信息的页面。
     * @param courseVoClass   课程信息的类，可能是用于表示课程的数据结构。
     * @param courseMPJLambdaWrapper   用于查询的包装器，包含了一些查询条件信息。
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
    /*
    创建一个名为 MPJLambdaWrapper 的类，该类将包含用于构建查询条件的方法。
    在这个类中，创建一个名为 getQueryWrapper 的方法，该方法接受一个 CourseQueryRequest 对象作为参数。
    在 getQueryWrapper 方法中，解析 courseQueryRequest 对象中的条件，例如课程名称、课程类型、课程时间等。
    根据解析出的条件，使用 MPJLambdaWrapper 类中的方法构建相应的查询逻辑，例如 equal、like、between 等。
    将构建好的查询逻辑封装在一个 MPJLambdaWrapper 对象中，并将其返回。
    */
}
