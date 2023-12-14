package com.cuit.studentskilldisplaysystem.controller;

import com.cuit.studentskilldisplaysystem.common.DeleteRequest;
import com.cuit.studentskilldisplaysystem.common.StatusResponse;
import com.cuit.studentskilldisplaysystem.common.StatusResponseCode;
import com.cuit.studentskilldisplaysystem.model.domain.Score;
import com.cuit.studentskilldisplaysystem.model.dto.score.ScoreAddRequest;
import com.cuit.studentskilldisplaysystem.model.dto.score.ScoreQueryRequest;
import com.cuit.studentskilldisplaysystem.model.dto.score.ScoreUpdateRequest;
import com.cuit.studentskilldisplaysystem.service.ScoreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/score")
public class ScoreController {
    @Resource
    private ScoreService scoreService;

    @PostMapping("/add")
    @ResponseBody
    public StatusResponse commentAdd(@RequestBody ScoreAddRequest scoreAddRequest) {
        StatusResponse statusResponse = new StatusResponse();
        Boolean result = scoreService.scoreAdd(scoreAddRequest);
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
    public StatusResponse commentUpdate(@RequestBody ScoreUpdateRequest scoreUpdateRequest) {
        StatusResponse statusResponse = new StatusResponse();
        Boolean result = scoreService.scoreUpdate(scoreUpdateRequest);
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
    public StatusResponse commentSelect(@RequestBody ScoreQueryRequest scoreQueryRequest) {
        StatusResponse statusResponse = new StatusResponse();
        List<Score> scoreList = scoreService.scoreSelect(scoreQueryRequest);
        if (scoreList != null) {
            statusResponse.setData(scoreList);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setData(null);
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

    @PostMapping("/delete")
    @ResponseBody
    public StatusResponse commentDelete(@RequestBody DeleteRequest deleteRequest) {
        StatusResponse statusResponse = new StatusResponse();
        Boolean result = scoreService.scoreDelete(deleteRequest);
        statusResponse.setData(result);
        if (result) {
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

}
