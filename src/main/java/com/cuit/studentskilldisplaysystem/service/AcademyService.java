package com.cuit.studentskilldisplaysystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuit.studentskilldisplaysystem.model.domain.Academy;
import com.cuit.studentskilldisplaysystem.model.dto.academy.AcademyQueryRequest;

/**
 * @description 针对表【academy】的数据库操作Service
 */
public interface AcademyService extends IService<Academy> {

    Boolean academyAdd(Academy academy);

    QueryWrapper<Academy> getQueryWrapper(AcademyQueryRequest academyQueryRequest);

    Boolean academyUpdate(Academy academy);

    Boolean academyDelete(Academy academy);

}
