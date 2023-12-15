package com.cuit.studentskilldisplaysystem.mapper;

import com.cuit.studentskilldisplaysystem.common.DeleteRequest;
import com.cuit.studentskilldisplaysystem.model.domain.Score;
import com.cuit.studentskilldisplaysystem.model.dto.score.ScoreQueryRequest;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description 针对表【score】的数据库操作Mapper
 */
@Mapper
public interface ScoreMapper extends MPJBaseMapper<Score> {


}




