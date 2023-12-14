package com.cuit.studentskilldisplaysystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuit.studentskilldisplaysystem.model.domain.SkillLevel;
import com.cuit.studentskilldisplaysystem.model.dto.skillLevel.SkillLevelQueryRequest;

import java.util.List;

/**
 * @description 针对表【skill_level】的数据库操作Service
 */
public interface SkillLevelService extends IService<SkillLevel> {
    /**
     * 增加技能等级
     * @param skillLevel
     * @return
     */
    Boolean skillLevelAdd(SkillLevel skillLevel);

    /**
     * 获取查询条件
     * @param skillLevelQueryRequest
     * @return
     */
    QueryWrapper<SkillLevel> getQueryWrapper(SkillLevelQueryRequest skillLevelQueryRequest);

    /**
     * 更新技能等级
     * @param skillLevel
     * @return
     */
    Boolean skillLevelUpdate(SkillLevel skillLevel);

    /**
     * 删除技能等级
     * @param skillLevel
     * @return
     */
    Boolean skillLevelDelete(SkillLevel skillLevel);

    /**
     * 查询所有技能等级的数据
     *
     * @return
     */
    List<SkillLevel> selectAll();
}
