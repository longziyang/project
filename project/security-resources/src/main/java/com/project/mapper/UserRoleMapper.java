package com.project.mapper;

import com.project.entity.UserRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserRoleMapper {

    @Select("select role_id from user_role where user_id = #{id}")
    List<String> getRoleIdByUserId(@Param("id") Long id);

    int insert(@Param("userRole") UserRole userRole);
}
