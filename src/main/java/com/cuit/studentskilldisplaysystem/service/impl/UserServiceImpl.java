package com.cuit.studentskilldisplaysystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.studentskilldisplaysystem.exception.BusinessException;
import com.cuit.studentskilldisplaysystem.mapper.UserMapper;
import com.cuit.studentskilldisplaysystem.model.domain.User;
import com.cuit.studentskilldisplaysystem.model.dto.UserLoginRequest;
import com.cuit.studentskilldisplaysystem.model.excel.UserForExcel;
import com.cuit.studentskilldisplaysystem.model.vo.UserVo;
import com.cuit.studentskilldisplaysystem.service.ExcelService;
import com.cuit.studentskilldisplaysystem.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

import static com.cuit.studentskilldisplaysystem.common.StatusResponseCode.NOT_LOGIN_ERROR;
import static com.cuit.studentskilldisplaysystem.contant.UserConstant.USER_LOGIN_STATE;

/**
 * @description 针对表【user】的数据库操作Service实现
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, ExcelService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserVo userLogin(UserLoginRequest userLoginRequest, HttpServletRequest request) {
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_account", userAccount);
        userQueryWrapper.eq("user_password", userPassword);
        User user = userMapper.selectOne(userQueryWrapper);
        if (ObjectUtil.isNotEmpty(user)) {
            request.getSession().setAttribute(USER_LOGIN_STATE, user);
            return this.getSafeUser(user);
        }
        return null;
    }

    private UserVo getSafeUser(User user) {
        UserVo safeUser = new UserVo();
        //使用hutool工具类拷贝用户对象的属性给Vo类对象，进行脱敏
        BeanUtil.copyProperties(user, safeUser);
        return safeUser;
    }

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    @Override
    public UserVo getLoginUser(HttpServletRequest request) {
        // 先判断是否已登录
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null || currentUser.getId() == null) {
            throw new BusinessException(NOT_LOGIN_ERROR);
        }
        String userId = currentUser.getId();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("id", userId);
        currentUser = userMapper.selectOne(userQueryWrapper);
        if (currentUser == null) {
            throw new BusinessException(NOT_LOGIN_ERROR);
        }
        return this.getSafeUser(currentUser);
    }

    @Override
    public Boolean userLogout(HttpServletRequest request) {
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return true;
    }

    @Override
    public Boolean importData(MultipartFile file) {
        try {
            List<UserForExcel> userlist = EasyExcel.read(file.getInputStream()).head(UserForExcel.class).sheet().doReadSync();
            //学院名称转学院id，批量保存在数据库中
            System.out.println(userlist);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Boolean exportData() {
        return null;
    }
}




