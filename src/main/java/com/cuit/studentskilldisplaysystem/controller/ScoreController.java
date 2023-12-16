package com.cuit.studentskilldisplaysystem.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.studentskilldisplaysystem.common.StatusResponse;
import com.cuit.studentskilldisplaysystem.common.StatusResponseCode;
import com.cuit.studentskilldisplaysystem.model.domain.Score;
import com.cuit.studentskilldisplaysystem.model.dto.score.ScoreQueryRequest;
import com.cuit.studentskilldisplaysystem.model.vo.ScoreVo;
import com.cuit.studentskilldisplaysystem.service.ScoreService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/score")
public class ScoreController {
    @Resource
    private ScoreService scoreService;


    @PostMapping("/add")
    public StatusResponse scoreAdd(@RequestBody Score score) {
        StatusResponse statusResponse = new StatusResponse();
        boolean result = scoreService.scoreAdd(score);
        if (result) {
            statusResponse.setData(result);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

    @PostMapping("/list/page")
    public StatusResponse scoreSelectByPage(@RequestBody ScoreQueryRequest scoreQueryRequest) {
        StatusResponse statusResponse = new StatusResponse();
        long current = scoreQueryRequest.getCurrent();
        long pageSize = scoreQueryRequest.getPageSize();
        IPage<ScoreVo> scoreList = scoreService.selectScoreJoinPage(new Page<>(current, pageSize), ScoreVo.class, scoreService.getQueryWrapper(scoreQueryRequest));
        if (scoreList != null) {
            statusResponse.setData(scoreList);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

    @PostMapping("/update")
    public StatusResponse scoreUpdate(@RequestBody Score score) {
        StatusResponse statusResponse = new StatusResponse();
        boolean result = scoreService.scoreUpdate(score);
        if (result) {
            statusResponse.setData(result);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

    @PostMapping("/delete")
    public StatusResponse scoreDelete(@RequestBody Score score) {
        StatusResponse statusResponse = new StatusResponse();
        boolean result = scoreService.scoreDelete(score);
        if (result) {
            statusResponse.setData(result);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

    @GetMapping("/select")
    public StatusResponse scoreSelect() {
        StatusResponse statusResponse = new StatusResponse();
        List<Score> scoreList = scoreService.selectAll();
        if (scoreList != null) {
            statusResponse.setData(scoreList);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

}
