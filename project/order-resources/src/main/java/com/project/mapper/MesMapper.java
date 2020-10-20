package com.project.mapper;



import com.project.entity.Mes;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MesMapper extends Mapper<Mes> {

    @Select("select * from mes where content= #{content}")
    Mes getMesByContent(@Param("content") String content);

    List<Mes> getMesByState();
}
