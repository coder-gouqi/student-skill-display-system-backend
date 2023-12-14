package com.cuit.studentskilldisplaysystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cuit.studentskilldisplaysystem.model.domain.SkillIndex;

import java.util.List;

/**
 * @description 针对表【skill_index】的数据库操作Service
 */
public interface SkillIndexService extends IService<SkillIndex> {
    /**
     * 查询所有技能指标的数据
     *
     * @return
     */
    List<SkillIndex> selectAll();
}
