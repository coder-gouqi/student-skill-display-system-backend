package com.cuit.studentskilldisplaysystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cuit.studentskilldisplaysystem.common.StatusResponseCode;
import com.cuit.studentskilldisplaysystem.contant.CommonConstant;
import com.cuit.studentskilldisplaysystem.exception.BusinessException;
import com.cuit.studentskilldisplaysystem.mapper.AcademyMapper;
import com.cuit.studentskilldisplaysystem.model.domain.Academy;
import com.cuit.studentskilldisplaysystem.model.dto.academy.AcademyQueryRequest;
import com.cuit.studentskilldisplaysystem.service.AcademyService;
import com.cuit.studentskilldisplaysystem.utils.SqlUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @description 针对表【academy】的数据库操作Service实现
 */
@Service
public class AcademyServiceImpl extends ServiceImpl<AcademyMapper, Academy>
        implements AcademyService {

    @Resource
    private AcademyMapper academyMapper;

    @Override
    public Boolean academyAdd(Academy academy) {
        String id = UUID.randomUUID().toString().replace("-", "");
        academy.setId(id);
        academy.setIsDelete(0);
        int result = academyMapper.insert(academy);
        return result > 0;
    }



    @Override
    public QueryWrapper<Academy> getQueryWrapper(AcademyQueryRequest academyQueryRequest) {
        if (academyQueryRequest == null) {
            throw new BusinessException(StatusResponseCode.PARAMS_ERROR, "请求参数为空");
        }
        String academyName = academyQueryRequest.getAcademyName();
        String academyInfo = academyQueryRequest.getAcademyInfo();
        String sortField = academyQueryRequest.getSortField();
        String sortOrder = academyQueryRequest.getSortOrder();

        QueryWrapper<Academy> academyQueryWrapper = new QueryWrapper<>();

        academyQueryWrapper.like("academy_name", academyName);
        academyQueryWrapper.like("academy_info", academyInfo);
        academyQueryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        return academyQueryWrapper;

    }

    @Override
    public Boolean academyUpdate(Academy academy) {
        int result = academyMapper.updateById(academy);
        return result > 0;
    }

    @Override
    public Boolean academyDelete(Academy academy) {
        int result = academyMapper.deleteById(academy);
        return result > 0;
    }
}




