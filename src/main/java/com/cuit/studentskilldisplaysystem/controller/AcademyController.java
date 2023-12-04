package com.cuit.studentskilldisplaysystem.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.studentskilldisplaysystem.common.StatusResponse;
import com.cuit.studentskilldisplaysystem.common.StatusResponseCode;
import com.cuit.studentskilldisplaysystem.model.domain.Academy;
import com.cuit.studentskilldisplaysystem.model.dto.academy.AcademyQueryRequest;
import com.cuit.studentskilldisplaysystem.service.AcademyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/academy")
public class AcademyController {

    @Resource
    private AcademyService academyService;

    @PostMapping("/add")
    public StatusResponse academyAdd(@RequestBody Academy academy) {
        StatusResponse statusResponse = new StatusResponse();
        boolean result = academyService.academyAdd(academy);
        if (result) {
            statusResponse.setData(result);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

    @PostMapping("/list/page")
    public StatusResponse academySelect(@RequestBody AcademyQueryRequest academyQueryRequest) {
        StatusResponse statusResponse = new StatusResponse();
        long current = academyQueryRequest.getCurrent();
        long pageSize = academyQueryRequest.getPageSize();
        Page<Academy> academyList = academyService.page(new Page<>(current, pageSize), academyService.getQueryWrapper(academyQueryRequest));
        if (academyList != null) {
            statusResponse.setData(academyList);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

    @PostMapping("/update")
    public StatusResponse academyUpdate(@RequestBody Academy academy) {
        StatusResponse statusResponse = new StatusResponse();
        boolean result = academyService.academyUpdate(academy);
        if (result) {
            statusResponse.setData(result);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

    @PostMapping("/delete")
    public StatusResponse academyDelete(@RequestBody Academy academy) {
        StatusResponse statusResponse = new StatusResponse();
        boolean result = academyService.academyDelete(academy);
        if (result) {
            statusResponse.setData(result);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }
}
