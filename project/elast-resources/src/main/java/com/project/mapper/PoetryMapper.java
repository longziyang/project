package com.project.mapper;

import com.project.entity.Poetry;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PoetryMapper {

    @Select("select * from poetry")
    List<Poetry> selectAll();

}
