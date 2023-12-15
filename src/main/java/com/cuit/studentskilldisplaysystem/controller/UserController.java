package com.cuit.studentskilldisplaysystem.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.studentskilldisplaysystem.common.StatusResponse;
import com.cuit.studentskilldisplaysystem.common.StatusResponseCode;
import com.cuit.studentskilldisplaysystem.model.dto.user.UserLoginRequest;
import com.cuit.studentskilldisplaysystem.model.dto.user.UserQueryRequest;
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

    /**
     * 查询用户信息（分页）
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
}
