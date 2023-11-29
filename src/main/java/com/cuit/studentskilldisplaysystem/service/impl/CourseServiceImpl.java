package com.cuit.studentskilldisplaysystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.studentskilldisplaysystem.model.domain.Course;
import com.cuit.studentskilldisplaysystem.service.CourseService;
import com.cuit.studentskilldisplaysystem.mapper.CourseMapper;
import org.springframework.stereotype.Service;

/**
* @description 针对表【course】的数据库操作Service实现
*/
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
    implements CourseService{

}




