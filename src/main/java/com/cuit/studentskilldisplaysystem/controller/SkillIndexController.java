package com.cuit.studentskilldisplaysystem.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.studentskilldisplaysystem.common.StatusResponse;
import com.cuit.studentskilldisplaysystem.common.StatusResponseCode;
import com.cuit.studentskilldisplaysystem.model.domain.SkillIndex;
import com.cuit.studentskilldisplaysystem.model.dto.skillIndex.SkillIndexQueryRequest;
import com.cuit.studentskilldisplaysystem.service.SkillIndexService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/skillIndex")
public class SkillIndexController {

    @Resource
    private SkillIndexService skillIndexService;

    @PostMapping("/add")
    public StatusResponse skillIndexAdd(@RequestBody SkillIndex skillIndex) {
        StatusResponse statusResponse = new StatusResponse();
        boolean result = skillIndexService.skillIndexAdd(skillIndex);
        if (result) {
            statusResponse.setData(result);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

    @PostMapping("/list/page")
    public StatusResponse skillIndexSelectByPage(@RequestBody SkillIndexQueryRequest skillIndexQueryRequest) {
        StatusResponse statusResponse = new StatusResponse();
        long current = skillIndexQueryRequest.getCurrent();
        long pageSize = skillIndexQueryRequest.getPageSize();
        Page<SkillIndex> skillIndexList = skillIndexService.page(new Page<>(current, pageSize), skillIndexService.getQueryWrapper(skillIndexQueryRequest));
        if (skillIndexList != null) {
            statusResponse.setData(skillIndexList);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

    @PostMapping("/update")
    public StatusResponse skillIndexUpdate(@RequestBody SkillIndex skillIndex) {
        StatusResponse statusResponse = new StatusResponse();
        boolean result = skillIndexService.skillIndexUpdate(skillIndex);
        if (result) {
            statusResponse.setData(result);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

    @PostMapping("/delete")
    public StatusResponse skillIndexDelete(@RequestBody SkillIndex skillIndex) {
        StatusResponse statusResponse = new StatusResponse();
        boolean result = skillIndexService.skillIndexDelete(skillIndex);
        if (result) {
            statusResponse.setData(result);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

    @GetMapping("/select")
    public StatusResponse skillIndexSelect( ) {
        StatusResponse statusResponse = new StatusResponse();
        List<SkillIndex> skillIndexList = skillIndexService.selectAll();
        if (skillIndexList != null) {
            statusResponse.setData(skillIndexList);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }
}
