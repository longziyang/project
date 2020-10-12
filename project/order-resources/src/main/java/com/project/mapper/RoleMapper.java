package com.project.mapper;

import com.project.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface RoleMapper {

    @Select("select * from role where id = #{id}")
    Role selectById(@Param("id") String id);

    @Select("select * from role where id = 1")
    Role selectUserRole();
}
