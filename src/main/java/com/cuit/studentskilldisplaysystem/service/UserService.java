package com.cuit.studentskilldisplaysystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cuit.studentskilldisplaysystem.model.domain.User;
import com.cuit.studentskilldisplaysystem.model.dto.UserLoginRequest;
import com.cuit.studentskilldisplaysystem.model.vo.UserVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    UserVo getLoginUser(HttpServletRequest request);

    /**
     * 用户注销登录
     *
     * @return
     */
    Boolean userLogout(HttpServletRequest request);

    /**
     * 导入数据
     *
     * @param file
     * @return
     */
    Boolean importData(MultipartFile file);

    /**
     * 导出数据
     *
     * @return
     */
    Boolean exportData(HttpServletResponse response);
}
