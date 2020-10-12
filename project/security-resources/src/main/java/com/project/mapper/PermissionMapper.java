package com.project.mapper;

import com.project.entity.Permission;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PermissionMapper extends Mapper<Permission> {

    @Select("select * from permission")
    List<Permission> getPermissions();
}
