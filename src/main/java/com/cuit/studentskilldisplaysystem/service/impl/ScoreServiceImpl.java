package com.cuit.studentskilldisplaysystem.service.impl;

import cn.hutool.core.lang.UUID;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.studentskilldisplaysystem.mapper.CourseMapper;
import com.cuit.studentskilldisplaysystem.mapper.ScoreMapper;
import com.cuit.studentskilldisplaysystem.mapper.UserMapper;
import com.cuit.studentskilldisplaysystem.model.domain.Course;
import com.cuit.studentskilldisplaysystem.model.domain.Score;
import com.cuit.studentskilldisplaysystem.model.domain.User;
import com.cuit.studentskilldisplaysystem.model.excel.ScoreForExcel;
import com.cuit.studentskilldisplaysystem.service.ExcelService;
import com.cuit.studentskilldisplaysystem.service.ScoreService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description 针对表【score】的数据库操作Service实现
 */
@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements ScoreService, ExcelService {

    @Resource
    private ScoreMapper scoreMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private CourseMapper courseMapper;

    @Override
    public Boolean importData(MultipartFile file) {
        try {

            List<ScoreForExcel> scoreList = EasyExcel.read(file.getInputStream()).head(ScoreForExcel.class).sheet().doReadSync();

            QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
            List<User> userList = userMapper.selectList(userQueryWrapper);

            QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<Course>();
            List<Course> courseList = courseMapper.selectList(courseQueryWrapper);

            List<Score> list = new ArrayList<>();
            for (ScoreForExcel scoreForExcel : scoreList) {
                Score score = new Score();
                String id = UUID.randomUUID().toString().replace("-", "");
                score.setId(id);
                for (User user : userList) {
                    if (user.getUserName().equals(scoreForExcel.getStudentName())) {
                        score.setStudentId(user.getId());
                    }
                }
                for (Course course : courseList) {
                    if (course.getCourseName().equals(scoreForExcel.getCourseName())) {
                        score.setCourseId(course.getId());
                    }
                }
                score.setStudentScore(scoreForExcel.getStudentScore());
                list.add(score);
            }
            this.saveBatch(list);
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




