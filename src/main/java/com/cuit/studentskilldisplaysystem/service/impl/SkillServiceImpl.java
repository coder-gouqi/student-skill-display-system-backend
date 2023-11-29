package com.cuit.studentskilldisplaysystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.studentskilldisplaysystem.model.domain.Skill;
import com.cuit.studentskilldisplaysystem.service.SkillService;
import com.cuit.studentskilldisplaysystem.mapper.SkillMapper;
import org.springframework.stereotype.Service;

/**
* @description 针对表【skill】的数据库操作Service实现
*/
@Service
public class SkillServiceImpl extends ServiceImpl<SkillMapper, Skill>
    implements SkillService{

}




