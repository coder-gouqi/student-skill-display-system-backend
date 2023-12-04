package com.cuit.studentskilldisplaysystem.controller;

import com.alibaba.excel.EasyExcel;
import com.cuit.studentskilldisplaysystem.common.StatusResponse;
import com.cuit.studentskilldisplaysystem.common.StatusResponseCode;
import com.cuit.studentskilldisplaysystem.service.CourseService;
import com.cuit.studentskilldisplaysystem.service.ScoreService;
import com.cuit.studentskilldisplaysystem.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/excel")
public class ExcelController {


    @Resource
    private ScoreService scoreService;

    @Resource
    private CourseService courseService;

    @Resource
    private UserService userService;


    /**
     * 导入信息
     *
     * @param file 上传的文件
     * @param type 导出的类
     * @return
     */
    @PostMapping("/import")
    public StatusResponse importExcel(@RequestPart(value = "file") MultipartFile file, @RequestParam("type") String type) {
        StatusResponse statusResponse = new StatusResponse();
        Boolean result = false;
        switch (type) {
            case "user":
                result = userService.importData(file);
                break;
            case "score":
                result = scoreService.importData(file);
                break;
            case "course":
                result = courseService.importData(file);
                break;
        }
        if (result) {
            statusResponse.setData(result);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

    /**
     * 导出信息
     *
     * @param type
     * @return
     */
    @RequestMapping("/export")
    public void exportExcel(@RequestParam("type") String type, HttpServletResponse response) {
        StatusResponse statusResponse = new StatusResponse();
        Boolean result = false;
        switch (type) {
            case "user":
                result = userService.exportData(response);
                break;
            case "score":
                result = scoreService.exportData(response);
                break;
            case "course":
                result = courseService.exportData(response);
                break;
        }
        if (result) {
            statusResponse.setData(result);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
    }
}
