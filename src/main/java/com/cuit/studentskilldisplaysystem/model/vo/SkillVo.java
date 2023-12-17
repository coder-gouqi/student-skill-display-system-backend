package com.cuit.studentskilldisplaysystem.model.vo;

import cn.hutool.json.JSONUtil;
import com.cuit.studentskilldisplaysystem.model.domain.Skill;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Map;

@Data
public class SkillVo {

    /**
     * 技能展示id
     */
    private String id;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 学生技能成绩（json）
     */
    private List<Map> studentSkillScore;

    /**
     * 包装类转对象
     *
     * @param skillVo
     * @return
     */
    public static Skill voToObj(SkillVo skillVo) {
        if (skillVo == null) {
            return null;
        }
        Skill skill = new Skill();
        BeanUtils.copyProperties(skillVo, skill);
        List<Map> voStudentSkillScore = skillVo.getStudentSkillScore();
        if (voStudentSkillScore != null) {
            skill.setStudentSkillScore(JSONUtil.toJsonStr(voStudentSkillScore));
        }
        System.out.println(skill.getStudentSkillScore());
        return skill;
    }

    /**
     * 对象转包装类
     *
     * @param skill
     * @return
     */
    public static SkillVo objToVo(Skill skill) {
        if (skill == null) {
            return null;
        }
        SkillVo skillVo = new SkillVo();
        BeanUtils.copyProperties(skill, skillVo);
        String studentSkillScoreStr = skill.getStudentSkillScore();
        List<Map> voStudentSkillScore = JSONUtil.parse(studentSkillScoreStr).toBean(List.class);
        skillVo.setStudentSkillScore(voStudentSkillScore);
        System.out.println(skillVo.getStudentSkillScore());
        return skillVo;
    }
}
