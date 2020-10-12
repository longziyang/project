package com.project.security.email;

import com.project.entity.SysUser;
import com.project.mapper.UserMapper;
import com.project.security.exception.DefaultAuthException;
import com.project.common.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmailLoginUserDetailService implements UserDetailsService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUser user = userMapper.selectUserByEmail(username);
        if (user == null) {

            throw new BadCredentialsException("用户不存在");
        }
        String code = redisTemplate.opsForValue().get(Constant.EMAIL_CODE + user.getUsername());
        if (StringUtils.isBlank(code)) {

            throw new DefaultAuthException("验证码错误");
        }
        user.setCode(code);
        return user;
    }
}
