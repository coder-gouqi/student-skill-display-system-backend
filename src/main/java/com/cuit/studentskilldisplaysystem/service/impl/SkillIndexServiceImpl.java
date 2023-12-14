package com.cuit.studentskilldisplaysystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.studentskilldisplaysystem.mapper.ScoreMapper;
import com.cuit.studentskilldisplaysystem.mapper.SkillIndexMapper;
import com.cuit.studentskilldisplaysystem.model.domain.SkillIndex;
import com.cuit.studentskilldisplaysystem.service.SkillIndexService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description 针对表【skill_index】的数据库操作Service实现
 */
@Service
public class SkillIndexServiceImpl extends ServiceImpl<SkillIndexMapper, SkillIndex>
        implements SkillIndexService {

    @Resource
    private SkillIndexMapper skillIndexMapper;

    @Override
    public List<SkillIndex> selectAll() {
        QueryWrapper<SkillIndex> skillIndexQueryWrapper = new QueryWrapper<>();
        List<SkillIndex> skillIndexList = skillIndexMapper.selectList(skillIndexQueryWrapper);
        return skillIndexList;
    }
}




