package com.cuit.studentskilldisplaysystem.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.studentskilldisplaysystem.common.StatusResponseCode;
import com.cuit.studentskilldisplaysystem.contant.CommonConstant;
import com.cuit.studentskilldisplaysystem.exception.BusinessException;
import com.cuit.studentskilldisplaysystem.mapper.SkillLevelMapper;
import com.cuit.studentskilldisplaysystem.model.domain.SkillLevel;
import com.cuit.studentskilldisplaysystem.model.dto.skillLevel.SkillLevelQueryRequest;
import com.cuit.studentskilldisplaysystem.service.SkillLevelService;
import com.cuit.studentskilldisplaysystem.utils.SqlUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @description 针对表【skill_level】的数据库操作Service实现
 */
@Service
public class SkillLevelServiceImpl extends ServiceImpl<SkillLevelMapper, SkillLevel>
        implements SkillLevelService {
    @Resource
    private SkillLevelMapper skillLevelMapper;

    @Override
    public Boolean skillLevelAdd(SkillLevel skillLevel) {
        String id = UUID.randomUUID().toString().replace("-", "");
        skillLevel.setId(id);
        skillLevel.setIsDelete(0);
        int result = skillLevelMapper.insert(skillLevel);
        return result > 0;
    }


    @Override
    public QueryWrapper<SkillLevel> getQueryWrapper(SkillLevelQueryRequest skillLevelQueryRequest) {
        if (skillLevelQueryRequest == null) {
            throw new BusinessException(StatusResponseCode.PARAMS_ERROR, "请求参数为空");
        }
        String skillLevelName = skillLevelQueryRequest.getSkillLevel();
        String sortField = skillLevelQueryRequest.getSortField();
        String sortOrder = skillLevelQueryRequest.getSortOrder();

        QueryWrapper<SkillLevel> skillLevelQueryWrapper = new QueryWrapper<>();
        skillLevelQueryWrapper.like(StrUtil.isNotBlank(skillLevelName), "skillLevel_name", skillLevelName);
        skillLevelQueryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        return skillLevelQueryWrapper;

    }

    @Override
    public Boolean skillLevelUpdate(SkillLevel skillLevel) {
        int result = skillLevelMapper.updateById(skillLevel);
        return result > 0;
    }

    @Override
    public Boolean skillLevelDelete(SkillLevel skillLevel) {
        int result = skillLevelMapper.deleteById(skillLevel);
        return result > 0;
    }

    @Override
    public List<SkillLevel> selectAll() {
        QueryWrapper<SkillLevel> skillLevelQueryWrapper = new QueryWrapper<>();
        List<SkillLevel> skillLevelList = skillLevelMapper.selectList(skillLevelQueryWrapper);
        return skillLevelList;
    }
}




