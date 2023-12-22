package com.cuit.studentskilldisplaysystem.job;

import cn.hutool.json.JSONUtil;
import com.cuit.studentskilldisplaysystem.common.StatusResponseCode;
import com.cuit.studentskilldisplaysystem.exception.ThrowUtils;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        double average;
        HashMap<String, String> hashMap;
        List<Map> mapList;
        for (Skill skill : skillList) {
            String studentId = skill.getStudentId();
            mapList = new ArrayList<>();
            for (SkillIndex skillIndex : skillIndexList) {
                String skillIndexId = skillIndex.getId();
                hashMap = new HashMap<>();
                sum = 0;
                count = 0;
                for (Score score : scoreList) {
                    if (score.getStudentId().equals(studentId)) {
                        for (Course course : courseList) {
                            if (course.getId().equals(score.getCourseId()) && course.getCourseSkillIndexId().equals(skillIndexId)) {
                                sum += score.getStudentScore() * course.getCourseWeight();
                                count++;
                            }
                        }
                    }
                }
                String skillIndexName = skillIndex.getSkillIndexName();
                if (sum == 0) {
                    average = 0;
                } else {
                    average = (sum / count);
                }
                hashMap.put("skillIndexName", skillIndexName);
                hashMap.put("skillIndexScore", String.format("%.2f", average));
                mapList.add(hashMap);
            }
            skill.setStudentSkillScore(JSONUtil.toJsonStr(mapList));
            boolean result = skillService.updateById(skill);
            ThrowUtils.throwIf(!result, StatusResponseCode.SYSTEM_ERROR);
        }
    }
}
