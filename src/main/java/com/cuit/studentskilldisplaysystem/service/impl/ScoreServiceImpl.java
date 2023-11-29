package com.cuit.studentskilldisplaysystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.studentskilldisplaysystem.model.domain.Score;
import com.cuit.studentskilldisplaysystem.service.ScoreService;
import com.cuit.studentskilldisplaysystem.mapper.ScoreMapper;
import org.springframework.stereotype.Service;

/**
* @description 针对表【score】的数据库操作Service实现
*/
@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score>
    implements ScoreService{

}




