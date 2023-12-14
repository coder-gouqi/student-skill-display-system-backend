package com.cuit.studentskilldisplaysystem.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.studentskilldisplaysystem.common.StatusResponse;
import com.cuit.studentskilldisplaysystem.common.StatusResponseCode;
import com.cuit.studentskilldisplaysystem.model.domain.SkillLevel;
import com.cuit.studentskilldisplaysystem.model.dto.skillLevel.SkillLevelQueryRequest;
import com.cuit.studentskilldisplaysystem.service.SkillLevelService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/skillLevel")
public class SkillLevelController {

    @Resource
    private SkillLevelService skillLevelService;

    @PostMapping("/add")
    public StatusResponse skillLevelAdd(@RequestBody SkillLevel skillLevel) {
        StatusResponse statusResponse = new StatusResponse();
        boolean result = skillLevelService.skillLevelAdd(skillLevel);
        if (result) {
            statusResponse.setData(result);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

    @PostMapping("/list/page")
    public StatusResponse skillLevelSelectByPage(@RequestBody SkillLevelQueryRequest skillLevelQueryRequest) {
        StatusResponse statusResponse = new StatusResponse();
        long current = skillLevelQueryRequest.getCurrent();
        long pageSize = skillLevelQueryRequest.getPageSize();
        Page<SkillLevel> skillLevelList = skillLevelService.page(new Page<>(current, pageSize), skillLevelService.getQueryWrapper(skillLevelQueryRequest));
        if (skillLevelList != null) {
            statusResponse.setData(skillLevelList);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

    @PostMapping("/update")
    public StatusResponse skillLevelUpdate(@RequestBody SkillLevel skillLevel) {
        StatusResponse statusResponse = new StatusResponse();
        boolean result = skillLevelService.skillLevelUpdate(skillLevel);
        if (result) {
            statusResponse.setData(result);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

    @PostMapping("/delete")
    public StatusResponse skillLevelDelete(@RequestBody SkillLevel skillLevel) {
        StatusResponse statusResponse = new StatusResponse();
        boolean result = skillLevelService.skillLevelDelete(skillLevel);
        if (result) {
            statusResponse.setData(result);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

    @GetMapping("/select")
    public StatusResponse skillLevelSelect() {
        StatusResponse statusResponse = new StatusResponse();
        List<SkillLevel> skillLevelList = skillLevelService.selectAll();
        if (skillLevelList != null) {
            statusResponse.setData(skillLevelList);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }
}
