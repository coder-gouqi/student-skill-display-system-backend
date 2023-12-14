package com.cuit.studentskilldisplaysystem.job;

import cn.hutool.json.JSONUtil;
import com.cuit.studentskilldisplaysystem.model.domain.Course;
import com.cuit.studentskilldisplaysystem.model.domain.Score;
import com.cuit.studentskilldisplaysystem.model.domain.Skill;
import com.cuit.studentskilldisplaysystem.model.domain.SkillIndex;
import com.cuit.studentskilldisplaysystem.service.CourseService;
import com.cuit.studentskilldisplaysystem.service.ScoreService;
import com.cuit.studentskilldisplaysystem.service.SkillIndexService;
import com.cuit.studentskilldisplaysystem.service.SkillService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Component
@EnableScheduling
public class StudentScoreJob {

    @Resource
    private SkillService skillService;

    @Resource
    private ScoreService scoreService;

    @Resource
    private SkillIndexService skillIndexService;

    @Resource
    private CourseService courseService;


    //每一分钟执行学生技能分数计算
    @Scheduled(cron = "0 */1 * * * ?")
    public void calculateScoreJob() {

        List<Skill> skillList = skillService.selectAll();
        List<SkillIndex> skillIndexList = skillIndexService.selectAll();
        List<Score> scoreList = scoreService.selectAll();
        List<Course> courseList = courseService.selectAll();

        double sum;
        int count;
        HashMap<String, String> hashMap;

        for (Skill skill : skillList) {
            String studentId = skill.getStudentId();
            hashMap = new HashMap<>();
            for (SkillIndex skillIndex : skillIndexList) {
                String skillIndexId = skillIndex.getId();
                sum = 0;
                count = 0;
                for (Score score : scoreList) {
                    if (score.getStudentId().equals(studentId)) {
                        for (Course course : courseList) {
                            if (course.getId().equals(score.getCourseId()) && course.getCourseSkillIndexId().equals(skillIndexId)) {
                                sum += score.getStudentScore();
                                count++;
                            }
                        }
                    }
                }
                String skillIndexName = skillIndex.getSkillIndexName();
                double average = (sum / count);
                hashMap.put(skillIndexName, String.valueOf(average));
            }
            skill.setStudentSkillScore(JSONUtil.toJsonStr(hashMap));
            boolean byId = skillService.updateById(skill);
            System.out.println(byId);
        }
    }

    //取出对应的指标id

    //课程表中指标id为这个的课程id
    //到成绩表中查找课程id,学生id关联起来的成绩进行计算
}
