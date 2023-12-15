package com.cuit.studentskilldisplaysystem.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.studentskilldisplaysystem.common.StatusResponse;
import com.cuit.studentskilldisplaysystem.common.StatusResponseCode;
import com.cuit.studentskilldisplaysystem.model.domain.User;
import com.cuit.studentskilldisplaysystem.model.dto.user.UserQueryRequest;
import com.cuit.studentskilldisplaysystem.model.vo.UserVo;
import com.cuit.studentskilldisplaysystem.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private UserService userService;

    @PostMapping("/add")
    public StatusResponse userAdd(@RequestBody User user) {
        StatusResponse statusResponse = new StatusResponse();
        boolean result = userService.userAdd(user);
        if (result) {
            statusResponse.setData(result);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

    /**
     * 查询学生信息（分页）
     *
     * @param userQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    public StatusResponse userSelectByPage(@RequestBody UserQueryRequest userQueryRequest) {
        StatusResponse statusResponse = new StatusResponse();
        long current = userQueryRequest.getCurrent();
        long pageSize = userQueryRequest.getPageSize();
        IPage<UserVo> userList = userService.selectUserJoinPage(new Page<>(current, pageSize), UserVo.class, userService.getQueryWrapper(userQueryRequest));
        if (userList != null) {
            statusResponse.setData(userList);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

    @GetMapping("/select")
    public StatusResponse userSelect() {
        StatusResponse statusResponse = new StatusResponse();
        List<User> userList = userService.selectAll();
        if (userList != null) {
            statusResponse.setData(userList);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

    @PostMapping("/update")
    public StatusResponse userUpdate(@RequestBody User user) {
        StatusResponse statusResponse = new StatusResponse();
        boolean result = userService.userUpdate(user);
        if (result) {
            statusResponse.setData(result);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

    @PostMapping("/delete")
    public StatusResponse userDelete(@RequestBody User user) {
        StatusResponse statusResponse = new StatusResponse();
        boolean result = userService.userDelete(user);
        if (result) {
            statusResponse.setData(result);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

}
