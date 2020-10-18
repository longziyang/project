package com.project.mapper;

import com.project.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

public interface OrderMapper {

    @Insert("insert into t_order(price,name,user_id) values(#{price},#{name},#{userId}) ")
    int insert(@Param("price")double price,@Param("name")String name,@Param("userId")Long userId);

    @Select("select * from t_order")
    List<Order> selectAll();
}
