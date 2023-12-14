package com.cuit.studentskilldisplaysystem.controller;

import com.cuit.studentskilldisplaysystem.common.DeleteRequest;
import com.cuit.studentskilldisplaysystem.common.StatusResponse;
import com.cuit.studentskilldisplaysystem.common.StatusResponseCode;
import com.cuit.studentskilldisplaysystem.model.domain.Course;
import com.cuit.studentskilldisplaysystem.model.dto.course.CourseAddRequest;
import com.cuit.studentskilldisplaysystem.model.dto.course.CourseQueryRequest;
import com.cuit.studentskilldisplaysystem.model.dto.course.CourseUpdateRequest;
import com.cuit.studentskilldisplaysystem.service.CourseService;
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
    @ResponseBody
    public StatusResponse essayAdd(@RequestBody CourseAddRequest courseAddRequest) {
        StatusResponse statusResponse = new StatusResponse();
        Boolean result = courseService.courseAdd(courseAddRequest);
        statusResponse.setData(result);
        if (result) {
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

    @PostMapping("/update")
    @ResponseBody
    public StatusResponse essayUpdate(@RequestBody CourseUpdateRequest courseUpdateRequest) {
        StatusResponse statusResponse = new StatusResponse();
        Boolean result = courseService.courseUpdate(courseUpdateRequest);
        statusResponse.setData(result);
        if (result) {
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

    @PostMapping("/select")
    @ResponseBody
    public StatusResponse essaySelect(@RequestBody CourseQueryRequest courseQueryRequest) {
        StatusResponse statusResponse = new StatusResponse();
        List<Course> essayList = courseService.courseSelect(courseQueryRequest);
        if (essayList != null) {
            statusResponse.setData(essayList);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setData(null);
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

    @PostMapping("/delete")
    @ResponseBody
    public StatusResponse essayDelete(@RequestBody DeleteRequest deleteRequest) {
        StatusResponse statusResponse = new StatusResponse();
        Boolean result = courseService.courseDelete(deleteRequest);
        statusResponse.setData(result);
        if (result) {
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }
}
