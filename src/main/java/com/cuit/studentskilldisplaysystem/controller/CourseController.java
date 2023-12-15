package com.cuit.studentskilldisplaysystem.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.studentskilldisplaysystem.common.DeleteRequest;
import com.cuit.studentskilldisplaysystem.common.StatusResponse;
import com.cuit.studentskilldisplaysystem.common.StatusResponseCode;
import com.cuit.studentskilldisplaysystem.model.domain.Course;
import com.cuit.studentskilldisplaysystem.model.domain.SkillIndex;
import com.cuit.studentskilldisplaysystem.model.dto.course.CourseQueryRequest;
import com.cuit.studentskilldisplaysystem.model.dto.skillIndex.SkillIndexQueryRequest;
import com.cuit.studentskilldisplaysystem.service.CourseService;
import com.cuit.studentskilldisplaysystem.service.SkillIndexService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Resource
    private CourseService courseService;

    @PostMapping("/add")
    public StatusResponse courseAdd(@RequestBody Course course) {
        StatusResponse statusResponse = new StatusResponse();
        boolean result = courseService.courseAdd(course);
        if (result) {
            statusResponse.setData(result);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

    @PostMapping("/list/page")
    public StatusResponse courseSelectByPage(@RequestBody CourseQueryRequest courseQueryRequest) {
        StatusResponse statusResponse = new StatusResponse();
        long current = courseQueryRequest.getCurrent();
        long pageSize = courseQueryRequest.getPageSize();
        Page<Course> courseList = courseService.page(new Page<>(current, pageSize), courseService.getQueryWrapper(courseQueryRequest));
        if (courseList != null) {
            statusResponse.setData(courseList);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

    @PostMapping("/update")
    public StatusResponse courseUpdate(@RequestBody Course course) {
        StatusResponse statusResponse = new StatusResponse();
        boolean result = courseService.courseUpdate(course);
        if (result) {
            statusResponse.setData(result);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

    @PostMapping("/delete")
    public StatusResponse courseDelete(@RequestBody Course course) {
        StatusResponse statusResponse = new StatusResponse();
        boolean result = courseService.courseDelete(course);
        if (result) {
            statusResponse.setData(result);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

}
