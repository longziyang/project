package com.project.mapper;

import com.project.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<SysUser> {

    @Select("select *  from sys_user where username =#{username} and password = #{password}")
    SysUser selectUser(@Param("username") String username, @Param("password") String password);

    @Select("select username from sys_user where email = #{mail}")
    String selectUserNameByEmail(@Param("mail") String mail);

    @Select("select username,password from sys_user where email = #{mail}")
    SysUser selectUserByEmail(@Param("mail") String mail);

    @Select("select * from sys_user where username = #{username}")
    SysUser selectUserByUsername(@Param("username") String username);

    @Select("select username from sys_user where username = #{username}")
    String selectUserExistsByUsername(@Param("username") String username);

    @Select("select id from sys_user where username = #{username}")
    Long selectUserId(@Param("username") String username);

    int insert(@Param("user") SysUser user);


    SysUser selectByUsername(@Param("username") String username);
}