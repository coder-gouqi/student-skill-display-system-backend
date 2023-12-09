package com.cuit.studentskilldisplaysystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cuit.studentskilldisplaysystem.model.domain.Skill;

import java.util.List;

/**
 * @description 针对表【skill】的数据库操作Service
 */
public interface SkillService extends IService<Skill> {
    /**
     * 查询所有技能展示的数据
     * @return
     */
    List<Skill> selectAll();

}
