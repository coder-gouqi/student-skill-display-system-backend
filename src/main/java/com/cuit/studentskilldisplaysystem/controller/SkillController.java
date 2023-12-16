package com.cuit.studentskilldisplaysystem.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.studentskilldisplaysystem.common.StatusResponse;
import com.cuit.studentskilldisplaysystem.common.StatusResponseCode;
import com.cuit.studentskilldisplaysystem.model.domain.Skill;
import com.cuit.studentskilldisplaysystem.model.dto.skill.SkillQueryRequest;
import com.cuit.studentskilldisplaysystem.model.vo.SkillVo;
import com.cuit.studentskilldisplaysystem.service.SkillService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/skill")
public class SkillController {

    @Resource
    private SkillService skillService;

    @PostMapping("/list/page")
    public StatusResponse skillSelect(@RequestBody SkillQueryRequest skillQueryRequest) {
        StatusResponse statusResponse = new StatusResponse();
        long current = skillQueryRequest.getCurrent();
        long pageSize = skillQueryRequest.getPageSize();
        Page<Skill> skillPage = skillService.page(new Page<>(current, pageSize), skillService.getQueryWrapper(skillQueryRequest));
        Page<SkillVo> skillVoPage = skillService.getSkillVoPage(skillPage);
        if (skillVoPage != null) {
            statusResponse.setData(skillVoPage);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

}
