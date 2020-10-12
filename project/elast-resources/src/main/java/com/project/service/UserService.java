package com.project.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.entity.User;
import com.project.mapper.UserMapper;

@Transactional
@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;

	public UserMapper getUserMapper() {
		return userMapper;
	}

	public List<User> getUser() {

		return userMapper.selectAll();
	}

}