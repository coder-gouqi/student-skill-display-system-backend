package com.cuit.studentskilldisplaysystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cuit.studentskilldisplaysystem.common.DeleteRequest;
import com.cuit.studentskilldisplaysystem.model.domain.Score;
import com.cuit.studentskilldisplaysystem.model.dto.score.ScoreQueryRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description 针对表【score】的数据库操作Mapper
 */
@Mapper
public interface ScoreMapper extends BaseMapper<Score> {
    int insert(Score score);

    List<Score> select(ScoreQueryRequest scoreQueryRequest);

    Score selectById(String id);

    int deleteById(DeleteRequest deleteRequest);

    int updateById(Score score);

}




