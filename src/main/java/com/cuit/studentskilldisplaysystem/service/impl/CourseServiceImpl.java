package com.cuit.studentskilldisplaysystem.service.impl;

import cn.hutool.core.lang.UUID;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.studentskilldisplaysystem.mapper.CourseMapper;
import com.cuit.studentskilldisplaysystem.mapper.SkillIndexMapper;
import com.cuit.studentskilldisplaysystem.model.domain.Course;
import com.cuit.studentskilldisplaysystem.model.domain.SkillIndex;
import com.cuit.studentskilldisplaysystem.model.excel.CourseForExcel;
import com.cuit.studentskilldisplaysystem.service.CourseService;
import com.cuit.studentskilldisplaysystem.service.ExcelService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description 针对表【course】的数据库操作Service实现
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
        implements CourseService, ExcelService {

    @Resource
    private SkillIndexMapper skillIndexMapper;

    @Resource
    private CourseMapper courseMapper;

    @Override
    public Boolean importData(MultipartFile file) {
        try {
            //读取excel数据,返回数组
            List<CourseForExcel> courseList = EasyExcel.read(file.getInputStream()).head(CourseForExcel.class).sheet().doReadSync();
            //将导入信息中的技能指标名称转技能指标id，批量保存在数据库中
            //查询技能指标信息，方便后面进行技能指标名称比对赋值
            QueryWrapper<SkillIndex> skillIndexQueryWrapper = new QueryWrapper<>();
            List<SkillIndex> skillIndexList = skillIndexMapper.selectList(skillIndexQueryWrapper);
            //创建一个优化数组，要批量插入的数据
            List<Course> list = new ArrayList<>();
            //遍历读取到的课程数组
            for (CourseForExcel courseForExcel : courseList) {
                Course course = new Course();
                //创建UUID
                String id = UUID.randomUUID().toString().replace("-", "");
                course.setId(id);
                //遍历，将技能指标名称匹配的技能指标id，赋值给课程的技能指标id属性
                for (SkillIndex skillIndex : skillIndexList) {
                    if (skillIndex.getSkillIndexName().equals(courseForExcel.getCourseSkillIndexName())) {
                        course.setCourseSkillIndexId(skillIndex.getId());
                    }
                }
                //其他属性赋值
                course.setCourseName(courseForExcel.getCourseName());
                course.setCourseWeight(courseForExcel.getCourseWeight());
                //将完成赋值的课程数据添加到数组
                list.add(course);
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
    public Boolean exportData() {
        return null;
    }
}




