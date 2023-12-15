package com.cuit.studentskilldisplaysystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuit.studentskilldisplaysystem.common.DeleteRequest;
import com.cuit.studentskilldisplaysystem.model.domain.Course;
import com.cuit.studentskilldisplaysystem.model.domain.Score;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuit.studentskilldisplaysystem.model.dto.course.CourseQueryRequest;
import com.cuit.studentskilldisplaysystem.model.dto.score.ScoreQueryRequest;
import com.cuit.studentskilldisplaysystem.model.vo.CourseVo;
import com.cuit.studentskilldisplaysystem.model.vo.ScoreVo;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @description 针对表【score】的数据库操作Service
 */
public interface ScoreService extends IService<Score> {

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
     * 查询所有成绩的数据
     *
     * @return
     */
    List<Score> selectAll();

    /**
     * 增加成绩
     * @param score
     * @return
     */
    Boolean scoreAdd(Score score);

    /**
     * 更新成绩
     * @param score
     * @return
     */
    Boolean scoreUpdate(Score score);

    /**
     * 删除成绩
     * @param score
     * @return
     */
    Boolean scoreDelete(Score score);

    /**
     * 查询课程信息（联表查询并分页）
     *
     * @param scoreVoPage
     * @param scoreVoClass
     * @param scoreMPJLambdaWrapper
     * @return
     */
    IPage<ScoreVo> selectScoreJoinPage(Page<ScoreVo> scoreVoPage, Class<ScoreVo> scoreVoClass, MPJLambdaWrapper<Score> scoreMPJLambdaWrapper);

    /**
     * 获取查询条件
     *
     * @param scoreQueryRequest
     * @return
     */
    MPJLambdaWrapper<Score> getQueryWrapper(ScoreQueryRequest scoreQueryRequest);
}
