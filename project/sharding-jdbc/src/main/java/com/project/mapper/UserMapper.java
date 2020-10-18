package com.project.mapper;

import com.project.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    @Insert("insert into t_user values(NULL,#{fullName},#{userType})")
    int insert(@Param("fullName") String fullName, @Param("userType") char userType);

    @Select("SELECT * FROM T_USER")
    List<User> selectAll();
}
