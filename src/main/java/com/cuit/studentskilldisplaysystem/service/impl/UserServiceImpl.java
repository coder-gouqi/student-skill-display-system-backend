package com.cuit.studentskilldisplaysystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.studentskilldisplaysystem.common.StatusResponseCode;
import com.cuit.studentskilldisplaysystem.contant.CommonConstant;
import com.cuit.studentskilldisplaysystem.exception.BusinessException;
import com.cuit.studentskilldisplaysystem.mapper.AcademyMapper;
import com.cuit.studentskilldisplaysystem.mapper.SkillMapper;
import com.cuit.studentskilldisplaysystem.mapper.UserMapper;
import com.cuit.studentskilldisplaysystem.model.domain.Academy;
import com.cuit.studentskilldisplaysystem.model.domain.Skill;
import com.cuit.studentskilldisplaysystem.model.domain.User;
import com.cuit.studentskilldisplaysystem.model.dto.user.UserLoginRequest;
import com.cuit.studentskilldisplaysystem.model.dto.user.UserQueryRequest;
import com.cuit.studentskilldisplaysystem.model.excel.UserForExcel;
import com.cuit.studentskilldisplaysystem.model.vo.UserVo;
import com.cuit.studentskilldisplaysystem.service.UserService;
import com.cuit.studentskilldisplaysystem.utils.SqlUtils;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static com.cuit.studentskilldisplaysystem.common.StatusResponseCode.NOT_LOGIN_ERROR;
import static com.cuit.studentskilldisplaysystem.contant.UserConstant.ROLE_STUDENT;
import static com.cuit.studentskilldisplaysystem.contant.UserConstant.USER_LOGIN_STATE;

/**
 * @description 针对表【user】的数据库操作Service实现
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private AcademyMapper academyMapper;

    @Resource
    private SkillMapper skillMapper;

    /**
     * 用户登录
     *
     * @param userLoginRequest
     * @param request
     * @return
     */
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

    /**
     * 用户信息脱敏
     *
     * @param user
     * @return
     */
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

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    @Override
    public Boolean userLogout(HttpServletRequest request) {
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return true;
    }

    /**
     * 导入学生信息
     *
     * @param file
     * @return
     */
    @Override
    public Boolean importData(MultipartFile file) {
        try {
            //读取excel数据,返回数组
            List<UserForExcel> userlist = EasyExcel.read(file.getInputStream()).head(UserForExcel.class).sheet().doReadSync();
            //将导入信息中的学院名称转学院id，批量保存在数据库中
            //查询学院信息，方便后面进行学院名称比对赋值
            QueryWrapper<Academy> academyQueryWrapper = new QueryWrapper<>();
            List<Academy> academyList = academyMapper.selectList(academyQueryWrapper);
            //创建一个优化数组，要批量插入的数据
            List<User> list = new ArrayList<>();
            //遍历读取到的用户数组
            for (UserForExcel userForExcel : userlist) {
                User user = new User();
                //创建UUID
                String id = UUID.randomUUID().toString().replace("-", "");
                user.setId(id);
                //遍历，将学院名称匹配的学院id，赋值给用户的学院id属性
                for (Academy academy : academyList) {
                    if (academy.getAcademyName().equals(userForExcel.getStudentAcademy())) {
                        user.setStudentAcademyId(academy.getId());
                    }
                }
                //其他属性赋值
                user.setUserName(userForExcel.getUserName());
                user.setUserRole(ROLE_STUDENT);
                user.setStudentNumber(userForExcel.getStudentNumber());
                user.setStudentClass(user.getStudentClass());
                user.setStudentGrade(user.getStudentGrade());
                user.setIsDelete(0);
                //将完成赋值的用户数据添加到数组
                list.add(user);
            }
            //批量插入，实际上还是一条一条插入数据，不是真正的批量插入
            this.saveBatch(list);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 导出学生信息
     *
     * @param response
     * @return
     */
    @Override
    public Boolean exportData(HttpServletResponse response) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        List<User> userList = userMapper.selectList(userQueryWrapper);
        QueryWrapper<Academy> academyQueryWrapper = new QueryWrapper<>();
        List<Academy> academyList = academyMapper.selectList(academyQueryWrapper);
        try {
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setCharacterEncoding("utf-8");
            // 设置防止中文名乱码
            String filename = null;
            filename = URLEncoder.encode("user", "utf-8");
            // 文件下载方式(附件下载还是在当前浏览器打开)
            response.setHeader("Content-disposition", "attachment;filename=" + filename + ".xlsx");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            List<UserForExcel> userForExcelList = new ArrayList<>();
            for (User user : userList) {
                UserForExcel userForExcel = new UserForExcel();
                for (Academy academy : academyList) {
                    if (user.getStudentAcademyId().equals(academy.getId())) {
                        userForExcel.setStudentAcademy(academy.getAcademyName());
                    }
                }
                userForExcel.setUserName(user.getUserName());
                userForExcel.setStudentNumber(user.getStudentNumber());
                userForExcel.setStudentClass(user.getStudentClass());
                userForExcel.setStudentGrade(user.getStudentGrade());
                userForExcelList.add(userForExcel);
            }
            EasyExcel.write(response.getOutputStream(), UserForExcel.class).sheet("user").doWrite(userForExcelList);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 查询用户（学生）信息（联表查询并分页）
     *
     * @param userVoPage
     * @param userVoClass
     * @param userMPJLambdaWrapper
     * @return
     */
    @Override
    public IPage<UserVo> selectUserJoinPage(Page<UserVo> userVoPage, Class<UserVo> userVoClass, MPJLambdaWrapper<User> userMPJLambdaWrapper) {
        IPage<UserVo> userVoIPage = userMapper.selectJoinPage(userVoPage, userVoClass, userMPJLambdaWrapper);
        return userVoIPage;
    }

    /**
     * 获取查询条件
     *
     * @param userQueryRequest
     * @return
     */
    @Override
    public MPJLambdaWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest) {
        if (userQueryRequest == null) {
            throw new BusinessException(StatusResponseCode.PARAMS_ERROR, "请求参数为空");
        }

        String userName = userQueryRequest.getUserName();
        Integer studentNumber = userQueryRequest.getStudentNumber();
        String studentAcademyId = userQueryRequest.getStudentAcademyId();
        String sortField = userQueryRequest.getSortField();
        String sortOrder = userQueryRequest.getSortOrder();

        MPJLambdaWrapper<User> userQueryWrapper = new MPJLambdaWrapper<>();
        userQueryWrapper.selectAll(User.class);
        userQueryWrapper.selectAs(Academy::getAcademyName, UserVo::getStudentAcademy);
        userQueryWrapper.leftJoin(Academy.class, Academy::getId, User::getStudentAcademyId);
        userQueryWrapper.like(StrUtil.isNotBlank(userName), User::getUserName, userName);
        userQueryWrapper.eq(User::getUserRole, ROLE_STUDENT);
        userQueryWrapper.eq(studentNumber != null, User::getStudentNumber, studentNumber);
        userQueryWrapper.eq(StrUtil.isNotBlank(studentAcademyId), User::getStudentAcademyId, studentAcademyId);
        userQueryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), User::getUserName);
        return userQueryWrapper;
    }

    @Override
    public List<User> selectAll() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        List<User> userList = userMapper.selectList(userQueryWrapper);
        return userList;
    }

    @Override
    public Boolean userAdd(User user) {
        String studentId = java.util.UUID.randomUUID().toString().replace("-", "");
        user.setId(studentId);
        user.setIsDelete(0);
        Skill skill = new Skill();
        String skillId = java.util.UUID.randomUUID().toString().replace("-", "");
        skill.setId(skillId);
        skill.setStudentId(studentId);
        skill.setIsDelete(0);
        int skillResult = skillMapper.insert(skill);
        int userResult = userMapper.insert(user);
        return userResult > 0 && skillResult > 0;
    }

    @Override
    public Boolean userUpdate(User user) {
        int result = userMapper.updateById(user);
        return result > 0;
    }

    @Override
    public Boolean userDelete(User user) {
        int result = userMapper.deleteById(user);
        return result > 0;
    }

}




