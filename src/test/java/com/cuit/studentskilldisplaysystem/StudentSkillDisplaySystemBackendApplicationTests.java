package com.cuit.studentskilldisplaysystem;

import cn.hutool.json.JSONUtil;
import com.cuit.studentskilldisplaysystem.model.domain.Skill;
import com.cuit.studentskilldisplaysystem.model.vo.SkillVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
class StudentSkillDisplaySystemBackendApplicationTests {

    @Test
    void testVoToObj() {
        Skill skill = new Skill();
        SkillVo skillVo = new SkillVo();
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("123","123");
        skillVo.setStudentSkillScore(stringStringHashMap);
        HashMap<String, String> voStudentSkillScore = skillVo.getStudentSkillScore();
        if (voStudentSkillScore != null) {
            skill.setStudentSkillScore(JSONUtil.toJsonStr(voStudentSkillScore));
        }
        System.out.println(skill.getStudentSkillScore());
    }

    @Test
    void testObjToVo() {
        SkillVo skillVo = new SkillVo();
        Skill skill = new Skill();
        skill.setStudentSkillScore("{\"123\":\"123\",\"456\":\"666\"}");
        BeanUtils.copyProperties(skill, skillVo);
        String studentSkillScoreStr = skill.getStudentSkillScore();
        HashMap<String,String> voStudentSkillScore = JSONUtil.parse(studentSkillScoreStr).toBean(HashMap.class);
        skillVo.setStudentSkillScore(voStudentSkillScore);
        System.out.println(skillVo.getStudentSkillScore());
        System.out.println(skillVo.getStudentSkillScore().keySet());
        System.out.println(skillVo.getStudentSkillScore().values());
    }




}
