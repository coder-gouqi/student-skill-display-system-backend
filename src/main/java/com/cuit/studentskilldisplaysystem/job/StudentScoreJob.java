package com.cuit.studentskilldisplaysystem.job;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cuit.studentskilldisplaysystem.model.domain.Skill;
import com.cuit.studentskilldisplaysystem.service.ScoreService;
import com.cuit.studentskilldisplaysystem.service.SkillService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@EnableScheduling
public class StudentScoreJob {

    @Resource
    private SkillService skillService;

    //每十分钟执行同步收藏数
    @Scheduled(cron = "0 */10 * * * ?")
    public void  calculateScoreJob(){
        List<Skill> skillList = skillService.selectAll();

    }



    //取出对应的指标id

    //课程表中指标id为这个的课程id
    //到成绩表中查找课程id,学生id关联起来的成绩进行计算
}
