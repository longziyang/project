package com.project.mapper;

import com.project.entity.Mesg;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface MesgMapper extends Mapper<Mesg> {

    @Select("select * from mesg where content= #{content}")
    Mesg getMesgByContent(@Param("content") String content);
}
