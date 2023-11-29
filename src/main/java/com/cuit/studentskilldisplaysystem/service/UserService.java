package com.cuit.studentskilldisplaysystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cuit.studentskilldisplaysystem.model.domain.User;
import com.cuit.studentskilldisplaysystem.model.dto.UserLoginRequest;
import com.cuit.studentskilldisplaysystem.model.vo.UserVo;

import javax.servlet.http.HttpServletRequest;

/**
 * @description 针对表【user】的数据库操作Service
 */
public interface UserService extends IService<User> {

    /**
     * 用户登录
     *
     * @param userLoginRequest
     * @return
     */
    UserVo userLogin(UserLoginRequest userLoginRequest, HttpServletRequest request);
}
