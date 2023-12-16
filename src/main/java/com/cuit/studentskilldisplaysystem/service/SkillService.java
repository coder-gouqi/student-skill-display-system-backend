package com.cuit.studentskilldisplaysystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuit.studentskilldisplaysystem.model.domain.Skill;
import com.cuit.studentskilldisplaysystem.model.dto.skill.SkillQueryRequest;
import com.cuit.studentskilldisplaysystem.model.vo.SkillVo;

import java.util.List;

/**
 * @description 针对表【skill】的数据库操作Service
 */
public interface SkillService extends IService<Skill> {
    /**
     * 查询所有技能展示的数据
     *
     * @return
     */
    List<Skill> selectAll();

    /**
     * 获取查询条件
     *
     * @param skillQueryRequest
     * @return
     */
    QueryWrapper<Skill> getQueryWrapper(SkillQueryRequest skillQueryRequest);

    /**
     * 获取技能展示信息分页
     * @param skillPage
     * @return
     */
    Page<SkillVo> getSkillVoPage(Page<Skill> skillPage);
}
