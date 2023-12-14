package com.cuit.studentskilldisplaysystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.studentskilldisplaysystem.common.DeleteRequest;
import com.cuit.studentskilldisplaysystem.mapper.CourseMapper;
import com.cuit.studentskilldisplaysystem.mapper.ScoreMapper;
import com.cuit.studentskilldisplaysystem.mapper.UserMapper;
import com.cuit.studentskilldisplaysystem.model.domain.Course;
import com.cuit.studentskilldisplaysystem.model.domain.Score;
import com.cuit.studentskilldisplaysystem.model.domain.User;
import com.cuit.studentskilldisplaysystem.model.dto.score.ScoreAddRequest;
import com.cuit.studentskilldisplaysystem.model.dto.score.ScoreQueryRequest;
import com.cuit.studentskilldisplaysystem.model.dto.score.ScoreUpdateRequest;
import com.cuit.studentskilldisplaysystem.model.excel.ScoreForExcel;
import com.cuit.studentskilldisplaysystem.service.ScoreService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description 针对表【score】的数据库操作Service实现
 */
@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements ScoreService {

    @Resource
    private ScoreMapper scoreMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private CourseMapper courseMapper;

    @Override
    public Boolean importData(MultipartFile file) {
        try {
            //读取excel数据,返回数组
            List<ScoreForExcel> scoreList = EasyExcel.read(file.getInputStream()).head(ScoreForExcel.class).sheet().doReadSync();
            //将导入信息中的学生姓名转学生id，课程名称转课程id，批量保存在数据库中
            //查询学生信息，方便后面进行学生姓名比对赋值
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
            List<User> userList = userMapper.selectList(userQueryWrapper);
            //查询课程信息，方便后面进行课程名称比对赋值
            QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<Course>();
            List<Course> courseList = courseMapper.selectList(courseQueryWrapper);
            //创建一个优化数组，要批量插入的数据
            List<Score> list = new ArrayList<>();
            //遍历读取到的学生成绩数组
            for (ScoreForExcel scoreForExcel : scoreList) {
                Score score = new Score();
                //创建UUID
                String id = UUID.randomUUID().toString().replace("-", "");
                score.setId(id);
                //遍历，将学生姓名匹配的学生id，赋值给学生成绩的学生id属性
                for (User user : userList) {
                    if (user.getUserName().equals(scoreForExcel.getStudentName())) {
                        score.setStudentId(user.getId());
                    }
                }
                //遍历，将课程名称匹配的课程id，赋值给学生成绩的课程id属性
                for (Course course : courseList) {
                    if (course.getCourseName().equals(scoreForExcel.getCourseName())) {
                        score.setCourseId(course.getId());
                    }
                }
                //其他属性赋值
                score.setStudentScore(scoreForExcel.getStudentScore());
                score.setIsDelete(0);
                //将完成赋值的学生成绩数据添加到数组
                list.add(score);
            }
            //批量插入，实际上还是一条一条插入数据，不是真正的批量插入
            this.saveBatch(list);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Boolean exportData(HttpServletResponse response) {
        QueryWrapper<Score> scoreQueryWrapper = new QueryWrapper<>();
        List<Score> scoreList = scoreMapper.selectList(scoreQueryWrapper);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        List<User> userList = userMapper.selectList(userQueryWrapper);
        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
        List<Course> courseList = courseMapper.selectList(courseQueryWrapper);
        try {
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setCharacterEncoding("utf-8");
            // 设置防止中文名乱码
            String filename = null;
            filename = URLEncoder.encode("score", "utf-8");
            // 文件下载方式(附件下载还是在当前浏览器打开)
            response.setHeader("Content-disposition", "attachment;filename=" +
                    filename + ".xlsx");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            List<ScoreForExcel> scoreForExcelList = new ArrayList<>();
            for (Score score : scoreList) {
                ScoreForExcel scoreForExcel = new ScoreForExcel();
                for (User user : userList) {
                    if (score.getStudentId().equals(user.getId())) {
                        scoreForExcel.setStudentName(user.getUserName());
                    }
                }
                for (Course course : courseList) {
                    if (score.getCourseId().equals(course.getId())) {
                        scoreForExcel.setCourseName(course.getCourseName());
                    }
                }
                scoreForExcel.setStudentScore(score.getStudentScore());
                scoreForExcelList.add(scoreForExcel);
            }
            EasyExcel.write(response.getOutputStream(), ScoreForExcel.class)
                    .sheet("score")
                    .doWrite(scoreForExcelList);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Boolean scoreAdd(ScoreAddRequest scoreAddRequest) {
        Score score = new Score();
        BeanUtil.copyProperties(scoreAddRequest,score);
        String id = UUID.randomUUID().toString().replace("-", "");
        score.setId(id);
        score.setIsDelete(0);
        int insert = scoreMapper.insert(score);
        return insert > 0;
    }

    @Override
    public Boolean scoreUpdate(ScoreUpdateRequest scoreUpdateRequest) {
        Score score = scoreMapper.selectById(scoreUpdateRequest.getId());
        BeanUtil.copyProperties(scoreUpdateRequest, score);
        int result = scoreMapper.updateById(score);
        return result > 0;
    }

    @Override
    public List<Score> scoreSelect(ScoreQueryRequest scoreQueryRequest) {
        List<Score> scoreList = scoreMapper.select(scoreQueryRequest);
        return scoreList;
    }

    @Override
    public Boolean scoreDelete(DeleteRequest deleteRequest) {
        int delete = scoreMapper.deleteById(deleteRequest);
        return delete > 0;
    }

    @Override
    public Score selectById(String id) {
        Score score = scoreMapper.selectById(id);
        return score;
    }
}




