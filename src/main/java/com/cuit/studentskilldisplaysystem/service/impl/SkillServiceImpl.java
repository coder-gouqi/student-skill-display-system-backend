package com.cuit.studentskilldisplaysystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.studentskilldisplaysystem.mapper.SkillMapper;
import com.cuit.studentskilldisplaysystem.model.domain.Skill;
import com.cuit.studentskilldisplaysystem.service.SkillService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description 针对表【skill】的数据库操作Service实现
 */
@Service
public class SkillServiceImpl extends ServiceImpl<SkillMapper, Skill> implements SkillService {

    @Resource
    private SkillMapper skillMapper;

    @Override
    public List<Skill> selectAll() {
        QueryWrapper<Skill> skillQueryWrapper = new QueryWrapper<>();
        List<Skill> selectList = skillMapper.selectList(skillQueryWrapper);
        return selectList;
    }
}




