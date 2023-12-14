package com.cuit.studentskilldisplaysystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuit.studentskilldisplaysystem.model.domain.SkillIndex;
import com.cuit.studentskilldisplaysystem.model.dto.skillIndex.SkillIndexQueryRequest;

import java.util.List;

/**
 * @description 针对表【skill_index】的数据库操作Service
 */
public interface SkillIndexService extends IService<SkillIndex> {
    /**
     * 增加技能指标
     * @param skillIndex
     * @return
     */
    Boolean skillIndexAdd(SkillIndex skillIndex);

    /**
     * 获取查询条件
     * @param skillIndexQueryRequest
     * @return
     */
    QueryWrapper<SkillIndex> getQueryWrapper(SkillIndexQueryRequest skillIndexQueryRequest);

    /**
     * 更新技能指标
     * @param skillIndex
     * @return
     */
    Boolean skillIndexUpdate(SkillIndex skillIndex);

    /**
     * 删除技能指标
     * @param skillIndex
     * @return
     */
    Boolean skillIndexDelete(SkillIndex skillIndex);

    /**
     * 查询所有技能指标的数据
     *
     * @return
     */
    List<SkillIndex> selectAll();
}
