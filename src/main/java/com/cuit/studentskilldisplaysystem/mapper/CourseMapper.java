package com.cuit.studentskilldisplaysystem.mapper;

import com.cuit.studentskilldisplaysystem.common.DeleteRequest;
import com.cuit.studentskilldisplaysystem.model.domain.Course;
import com.cuit.studentskilldisplaysystem.model.dto.course.CourseQueryRequest;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description 针对表【course】的数据库操作Mapper
 */
@Mapper
public interface CourseMapper extends MPJBaseMapper<Course> {
    int insert(Course course);

    List<Course> select(CourseQueryRequest courseQueryRequest);

    Course selectById(String id);

    int deleteById(DeleteRequest deleteRequest);

    int updateById(Course course);

}




