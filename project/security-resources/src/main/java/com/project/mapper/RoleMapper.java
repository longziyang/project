package com.project.mapper;

import com.project.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper {

    @Select("select * from role where id = #{id}")
    Role selectById(@Param("id") String id);

    @Select("select * from role where id = 1")
    Role selectUserRole();

    @Select("select r.* from role r left join user_role ur on ur.role_id = r.id where ur.user_id =#{userId}")
    List<Role> selectByUserId(@Param("userId") Long userId);
}
