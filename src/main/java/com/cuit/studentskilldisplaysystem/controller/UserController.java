package com.cuit.studentskilldisplaysystem.controller;

import com.cuit.studentskilldisplaysystem.common.StatusResponse;
import com.cuit.studentskilldisplaysystem.common.StatusResponseCode;
import com.cuit.studentskilldisplaysystem.model.dto.UserLoginRequest;
import com.cuit.studentskilldisplaysystem.model.vo.UserVo;
import com.cuit.studentskilldisplaysystem.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户登录
     *
     * @param userLoginRequest 用户登录请求体
     * @param request
     * @return
     */
    @PostMapping("/login")
    public StatusResponse userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        StatusResponse statusResponse = new StatusResponse();
        UserVo user = userService.userLogin(userLoginRequest, request);
        if (user != null) {
            statusResponse.setData(user);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    @GetMapping("/get/login")
    public StatusResponse getLoginUser(HttpServletRequest request) {
        StatusResponse statusResponse = new StatusResponse();
        UserVo user = userService.getLoginUser(request);
        if (user != null) {
            statusResponse.setData(user);
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }

    /**
     * 用户注销登录
     *
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public StatusResponse userLogout(HttpServletRequest request) {
        StatusResponse statusResponse = new StatusResponse();
        Boolean result = userService.userLogout(request);
        if (result) {
            statusResponse.setMsgAndCode(StatusResponseCode.SUCCESS);
        } else {
            statusResponse.setMsgAndCode(StatusResponseCode.ERROR);
        }
        return statusResponse;
    }
}
