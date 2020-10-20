package com.project.mapper;

import com.project.entity.Poetry;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PoetryMapper extends Mapper<Poetry> {

    @Select("select * from poetry")
    List<Poetry> selectAll();

    @Select("select count(id) from poetry where content=#{content}")
    int selectCountByContent(@Param("content") String content);
}
