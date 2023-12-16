package com.cuit.studentskilldisplaysystem.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.studentskilldisplaysystem.common.DeleteRequest;
import com.cuit.studentskilldisplaysystem.common.StatusResponseCode;
import com.cuit.studentskilldisplaysystem.contant.CommonConstant;
import com.cuit.studentskilldisplaysystem.exception.BusinessException;
import com.cuit.studentskilldisplaysystem.mapper.CourseMapper;
import com.cuit.studentskilldisplaysystem.mapper.SkillIndexMapper;
import com.cuit.studentskilldisplaysystem.model.domain.Academy;
import com.cuit.studentskilldisplaysystem.model.domain.Course;
import com.cuit.studentskilldisplaysystem.model.domain.SkillIndex;
import com.cuit.studentskilldisplaysystem.model.domain.User;
import com.cuit.studentskilldisplaysystem.model.dto.course.CourseQueryRequest;
import com.cuit.studentskilldisplaysystem.model.dto.skillIndex.SkillIndexQueryRequest;
import com.cuit.studentskilldisplaysystem.model.dto.user.UserQueryRequest;
import com.cuit.studentskilldisplaysystem.model.excel.CourseForExcel;
import com.cuit.studentskilldisplaysystem.model.vo.CourseVo;
import com.cuit.studentskilldisplaysystem.model.vo.UserVo;
import com.cuit.studentskilldisplaysystem.service.CourseService;
import com.cuit.studentskilldisplaysystem.utils.SqlUtils;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static com.cuit.studentskilldisplaysystem.contant.UserConstant.ROLE_STUDENT;

/**
 * @description 针对表【course】的数据库操作Service实现
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
        implements CourseService {

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
                course.setIsDelete(0);
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
    public Boolean exportData(HttpServletResponse response) {
        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
        List<Course> courseList = courseMapper.selectList(courseQueryWrapper);
        QueryWrapper<SkillIndex> skillIndexQueryWrapper = new QueryWrapper<>();
        List<SkillIndex> skillIndexList = skillIndexMapper.selectList(skillIndexQueryWrapper);
        try {
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setCharacterEncoding("utf-8");
            // 设置防止中文名乱码
            String filename = null;
            filename = URLEncoder.encode("course", "utf-8");
            // 文件下载方式(附件下载还是在当前浏览器打开)
            response.setHeader("Content-disposition", "attachment;filename=" +
                    filename + ".xlsx");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            List<CourseForExcel> courseForExcelList = new ArrayList<>();
            for (Course course : courseList) {
                CourseForExcel courseForExcel = new CourseForExcel();
                for (SkillIndex skillIndex : skillIndexList) {
                    if (course.getCourseSkillIndexId().equals(skillIndex.getId())) {
                        courseForExcel.setCourseSkillIndexName(skillIndex.getSkillIndexName());
                    }
                }
                courseForExcel.setCourseName(course.getCourseName());
                courseForExcel.setCourseWeight(course.getCourseWeight());
                courseForExcelList.add(courseForExcel);
            }
            EasyExcel.write(response.getOutputStream(), CourseForExcel.class)
                    .sheet("course")
                    .doWrite(courseForExcelList);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 查询课程信息（联表查询并分页）
     *
     * @param courseVoPage
     * @param courseVoClass
     * @param courseMPJLambdaWrapper
     * @return
     */
    //从数据库中查询课程信息，并返回一个包含课程信息的分页对象。这个方法使用了MyBatis-Plus框架提供的便利方法来简化数据库查询操作。
    @Override
    public IPage<CourseVo> selectCourseJoinPage(Page<CourseVo> courseVoPage, Class<CourseVo> courseVoClass, MPJLambdaWrapper<Course> courseMPJLambdaWrapper){
        IPage<CourseVo> courseVoIPage = courseMapper.selectJoinPage(courseVoPage, courseVoClass, courseMPJLambdaWrapper);
        return courseVoIPage;
    }

    /**
     * 获取查询条件
     *
     * @param courseQueryRequest
     * @return
     */
    @Override
    public MPJLambdaWrapper<Course> getQueryWrapper(CourseQueryRequest courseQueryRequest) {
        if (courseQueryRequest == null) {
            throw new BusinessException(StatusResponseCode.PARAMS_ERROR, "请求参数为空");
        }
        String courseName = courseQueryRequest.getCourseName();
        String courseSkillIndexId = courseQueryRequest.getCourseSkillIndexId();
        String sortField = courseQueryRequest.getSortField();
        String sortOrder = courseQueryRequest.getSortOrder();


        MPJLambdaWrapper<Course> courseQueryWrapper = new MPJLambdaWrapper<>();
        courseQueryWrapper.selectAll(Course.class);
        courseQueryWrapper.selectAs(SkillIndex::getSkillIndexName, CourseVo::getSkillIndexName);
        courseQueryWrapper.leftJoin(SkillIndex.class, SkillIndex::getId, Course::getCourseSkillIndexId);
        courseQueryWrapper.like(StrUtil.isNotBlank(courseName), Course::getCourseName, courseName);
        courseQueryWrapper.eq(StrUtil.isNotBlank(courseSkillIndexId), Course::getCourseSkillIndexId, courseSkillIndexId);
        courseQueryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), Course::getCourseName);
        return courseQueryWrapper;
    }

    /*QueryWrapper 是 MyBatis-Plus 框架中的一个类，它用于构建查询条件。在这里，courseQueryWrapper 是一个用于查询课程的条件包装器。
    courseMapper.selectList(courseQueryWrapper) 的作用是执行一个查询操作，使用 courseQueryWrapper 中定义的条件来选择数据库中的课程数据。
    查询结果会以 List<Course> 的形式返回，
     */
    @Override
    public List<Course> selectAll() {
        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
        List<Course> courseList = courseMapper.selectList(courseQueryWrapper);
        return courseList;
    }


    /*使用java.util.UUID.randomUUID()方法生成一个UUID（通用唯一识别码），然后将这个UUID作为课程的ID。这样可以确保每个课程都有一个唯一的标识符。
    生成的UUID是一个128位的随机数，通常以36个字符的形式表示，包括32个十六进制数字和4个短划线。在生成UUID后，代码将短划线替换为空字符串，以便将UUID存储为一个32个字符的字符串。
     然后将课程对象插入数据库，并返回插入结果的布尔值。
     */
    @Override
    public Boolean courseAdd(Course course) {
        String id = java.util.UUID.randomUUID().toString().replace("-", "");
        course.setId(id);
        course.setIsDelete(0);
        int result = courseMapper.insert(course);
        return result > 0;
    }

    /*方法内部首先调用了courseMapper的updateById方法，传入course对象作为参数，然后将返回的结果存储在result变量中。
      接着，方法使用条件语句判断result的值是否大于0。如果是，就返回true，表示课程更新成功；如果不是，则返回false，表示课程更新失败。
     */
    @Override
    public Boolean courseUpdate(Course course) {
        int result = courseMapper.updateById(course);
        return result > 0;
    }

    @Override
    public Boolean courseDelete(Course course) {
        int result = courseMapper.deleteById(course);
        return result > 0;
    }

}




