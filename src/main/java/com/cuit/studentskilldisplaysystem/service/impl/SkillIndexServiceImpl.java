package com.cuit.studentskilldisplaysystem.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.studentskilldisplaysystem.common.StatusResponseCode;
import com.cuit.studentskilldisplaysystem.contant.CommonConstant;
import com.cuit.studentskilldisplaysystem.exception.BusinessException;
import com.cuit.studentskilldisplaysystem.mapper.SkillIndexMapper;
import com.cuit.studentskilldisplaysystem.model.domain.SkillIndex;
import com.cuit.studentskilldisplaysystem.model.dto.skillIndex.SkillIndexQueryRequest;
import com.cuit.studentskilldisplaysystem.service.SkillIndexService;
import com.cuit.studentskilldisplaysystem.utils.SqlUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @description 针对表【skill_index】的数据库操作Service实现
 */
@Service
public class SkillIndexServiceImpl extends ServiceImpl<SkillIndexMapper, SkillIndex>
        implements SkillIndexService {

    @Resource
    private SkillIndexMapper skillIndexMapper;

    @Override
    public Boolean skillIndexAdd(SkillIndex skillIndex) {
        String id = UUID.randomUUID().toString().replace("-", "");
        skillIndex.setId(id);
        skillIndex.setIsDelete(0);
        int result = skillIndexMapper.insert(skillIndex);
        return result > 0;
    }



    @Override
    public QueryWrapper<SkillIndex> getQueryWrapper(SkillIndexQueryRequest skillIndexQueryRequest) {
        if (skillIndexQueryRequest == null) {
            throw new BusinessException(StatusResponseCode.PARAMS_ERROR, "请求参数为空");
        }
        String skillIndexName = skillIndexQueryRequest.getSkillIndexName();
        String sortField = skillIndexQueryRequest.getSortField();
        String sortOrder = skillIndexQueryRequest.getSortOrder();

        QueryWrapper<SkillIndex> skillIndexQueryWrapper = new QueryWrapper<>();
        skillIndexQueryWrapper.like(StrUtil.isNotBlank(skillIndexName),"skillIndex_name", skillIndexName);
        skillIndexQueryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        return skillIndexQueryWrapper;

    }

    @Override
    public Boolean skillIndexUpdate(SkillIndex skillIndex) {
        int result = skillIndexMapper.updateById(skillIndex);
        return result > 0;
    }

    @Override
    public Boolean skillIndexDelete(SkillIndex skillIndex) {
        int result = skillIndexMapper.deleteById(skillIndex);
        return result > 0;
    }
    
    @Override
    public List<SkillIndex> selectAll() {
        QueryWrapper<SkillIndex> skillIndexQueryWrapper = new QueryWrapper<>();
        List<SkillIndex> skillIndexList = skillIndexMapper.selectList(skillIndexQueryWrapper);
        return skillIndexList;
    }
}




