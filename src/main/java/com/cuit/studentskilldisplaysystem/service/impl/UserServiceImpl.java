package com.cuit.studentskilldisplaysystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.studentskilldisplaysystem.mapper.UserMapper;
import com.cuit.studentskilldisplaysystem.model.domain.User;
import com.cuit.studentskilldisplaysystem.model.dto.UserLoginRequest;
import com.cuit.studentskilldisplaysystem.model.vo.UserVo;
import com.cuit.studentskilldisplaysystem.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.cuit.studentskilldisplaysystem.contant.UserConstant.USER_LOGIN_STATE;

/**
 * @description 针对表【user】的数据库操作Service实现
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

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
        BeanUtil.copyProperties(user, safeUser);
        return safeUser;
    }

}




