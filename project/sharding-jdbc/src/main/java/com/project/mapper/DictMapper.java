package com.project.mapper;

import com.project.entity.Dict;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface DictMapper {

    @Insert("insert into t_dict(column1,column2,column3) values('column1','column2','column3') ")
    int insert();

    @Select("select * from t_dict")
    List<Dict> selectAll();
}
