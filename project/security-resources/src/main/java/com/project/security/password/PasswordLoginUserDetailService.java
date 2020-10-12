package com.project.security.password;

import com.project.entity.Role;
import com.project.entity.SysUser;
import com.project.mapper.RoleMapper;
import com.project.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordLoginUserDetailService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUser user = userMapper.selectUserByUsername(username);
        List<Role> roleList = roleMapper.selectByUserId(user.getId());
        user.setAuthorities(roleList);
        return user;
    }
}
