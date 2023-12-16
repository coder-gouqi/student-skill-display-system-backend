package com.cuit.studentskilldisplaysystem.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.studentskilldisplaysystem.common.StatusResponse;
import com.cuit.studentskilldisplaysystem.common.StatusResponseCode;
import com.cuit.studentskilldisplaysystem.model.domain.Course;
import com.cuit.studentskilldisplaysystem.model.dto.course.CourseQueryRequest;
import com.cuit.studentskilldisplaysystem.model.vo.CourseVo;
import com.cuit.studentskilldisplaysystem.service.CourseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Resource
    private CourseService courseService;

//    返回一个名为StatusResponse的对象，它接受一个Course对象作为请求体，并命名为course。
//    @RequestBody注解表示将请求体中的JSON数据转换为Course对象。
    @PostMapping("/add")
    public StatusResponse courseAdd(@RequestBody Course course) {
        StatusResponse statusResponse = new StatusResponse();
        boolean result = courseService.courseAdd(course);
        if (result) {
            statusResponse.setData(result);  //根据result的值设置statusResponse对象的数据和消息代码。
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

    @PostMapping("/list/page")
    //处理 POST 请求，根据请求中的条件查询课程信息，并将查询结果封装到 statusResponse 对象中返回。
    public StatusResponse courseSelectByPage(@RequestBody CourseQueryRequest courseQueryRequest) {
        StatusResponse statusResponse = new StatusResponse();
        long current = courseQueryRequest.getCurrent();
        long pageSize = courseQueryRequest.getPageSize();
        //接收一个 Page 对象、CourseVo 类型和一个查询条件作为参数，返回一个 IPage<CourseVo> 对象。
        IPage<CourseVo> courseList = courseService.selectCourseJoinPage(new Page<>(current, pageSize), CourseVo.class, courseService.getQueryWrapper(courseQueryRequest));
        //判断 courseList 是否为 null，如果不为 null，则将其设置为 statusResponse 的数据，并设置状态码为成功；如果为 null，则将状态码设置为错误。
        if (courseList != null) {
            statusResponse.setData(courseList);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

    @PostMapping("/update")
    //创建了一个 StatusResponse 对象，然后调用了 courseService 的 courseUpdate 方法来更新课程信息。
    // 根据返回的结果，设置了相应的状态和数据，并将 StatusResponse 对象返回作为响应。
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

    @GetMapping("/select")
    public StatusResponse courseSelect() {
        StatusResponse statusResponse = new StatusResponse();
        List<Course> courseList = courseService.selectAll();
        if (courseList != null) {
            statusResponse.setData(courseList);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

}
