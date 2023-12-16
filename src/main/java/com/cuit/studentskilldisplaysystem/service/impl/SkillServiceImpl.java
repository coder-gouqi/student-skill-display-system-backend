package com.cuit.studentskilldisplaysystem.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.studentskilldisplaysystem.common.StatusResponseCode;
import com.cuit.studentskilldisplaysystem.contant.CommonConstant;
import com.cuit.studentskilldisplaysystem.exception.BusinessException;
import com.cuit.studentskilldisplaysystem.mapper.SkillMapper;
import com.cuit.studentskilldisplaysystem.mapper.UserMapper;
import com.cuit.studentskilldisplaysystem.model.domain.Skill;
import com.cuit.studentskilldisplaysystem.model.domain.User;
import com.cuit.studentskilldisplaysystem.model.dto.skill.SkillQueryRequest;
import com.cuit.studentskilldisplaysystem.model.vo.SkillVo;
import com.cuit.studentskilldisplaysystem.service.SkillService;
import com.cuit.studentskilldisplaysystem.utils.SqlUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description 针对表【skill】的数据库操作Service实现
 */
@Service
public class SkillServiceImpl extends ServiceImpl<SkillMapper, Skill> implements SkillService {

    @Resource
    private SkillMapper skillMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public List<Skill> selectAll() {
        QueryWrapper<Skill> skillQueryWrapper = new QueryWrapper<>();
        List<Skill> selectList = skillMapper.selectList(skillQueryWrapper);
        return selectList;
    }

    @Override
    public QueryWrapper<Skill> getQueryWrapper(SkillQueryRequest skillQueryRequest) {
        if (skillQueryRequest == null) {
            throw new BusinessException(StatusResponseCode.PARAMS_ERROR, "请求参数为空");
        }
        String studentId = skillQueryRequest.getStudentId();
        String sortField = skillQueryRequest.getSortField();
        String sortOrder = skillQueryRequest.getSortOrder();

        QueryWrapper<Skill> skillQueryWrapper = new QueryWrapper<>();
        skillQueryWrapper.eq(StrUtil.isNotBlank(studentId), "student_id", studentId);
        skillQueryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        return skillQueryWrapper;
    }

    @Override
    public Page<SkillVo> getSkillVoPage(Page<Skill> skillPage) {
        List<Skill> skillList = skillPage.getRecords();
        Page<SkillVo> skillVoPage = new Page<>(skillPage.getCurrent(), skillPage.getSize(), skillPage.getTotal());
        if (CollectionUtil.isEmpty(skillList)) {
            return skillVoPage;
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        List<User> userList = userMapper.selectList(userQueryWrapper);
        //填充信息
        List<SkillVo> skillVoList = skillList.stream().map(skill -> {
            SkillVo skillVo = SkillVo.objToVo(skill);
            for (User user : userList) {
                if (user.getId().equals(skill.getStudentId())) {
                    skillVo.setStudentName(user.getUserName());
                }
            }
            return skillVo;
        }).collect(Collectors.toList());
        skillVoPage.setRecords(skillVoList);
        return skillVoPage;
    }
}




