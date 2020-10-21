package com.project.mapper;

import com.project.entity.Dict;
import com.project.entity.SysDict;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface DictMapper {

    @Insert("insert into t_dict(column1,column2,column3) values('column1','column2','column3') ")
    int insert();

    @Select("select * from t_dict")
    List<Dict> selectAll();

    @Insert("INSERT INTO t_sys_dict ( dict_kind, dict_key, dict_value, dis_order)VALUES(#{dictKind},#{dictKey},#{dictValue},#{disOrder})")
    int insertDict(@Param("dictKind") String dictKind, @Param("dictKey") String dictKey, @Param("dictValue") String dictValue, @Param("disOrder") int disOrder);

    @Select("select * from t_sys_dict")
    List<SysDict> selectAllDict();
}
