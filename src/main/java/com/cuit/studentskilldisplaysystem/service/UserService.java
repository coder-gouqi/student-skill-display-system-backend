package com.cuit.studentskilldisplaysystem.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuit.studentskilldisplaysystem.model.domain.User;
import com.cuit.studentskilldisplaysystem.model.dto.user.UserLoginRequest;
import com.cuit.studentskilldisplaysystem.model.dto.user.UserQueryRequest;
import com.cuit.studentskilldisplaysystem.model.vo.UserVo;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
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

    /**
     * 查询用户（学生）信息（联表查询并分页）
     *
     * @param userVoPage
     * @param userVoClass
     * @param userMPJLambdaWrapper
     * @return
     */
    IPage<UserVo> selectUserJoinPage(Page<UserVo> userVoPage, Class<UserVo> userVoClass, MPJLambdaWrapper<User> userMPJLambdaWrapper);

    /**
     * 获取查询条件
     *
     * @param userQueryRequest
     * @return
     */
    MPJLambdaWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest);
}
