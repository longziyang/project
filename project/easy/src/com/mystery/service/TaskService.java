package com.mystery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mystery.dao.TaskMapper;
import com.mystery.entity.Task;

@Transactional
@Service
public class TaskService {
	@Autowired
	private TaskMapper taskMapper;

	public TaskMapper getTaskMapper() {
		return taskMapper;
	}

	public List<Task> selectByState() {

		return taskMapper.selectByState();

	}

	public void updateByState(Integer id) {
		taskMapper.updateByState(id);

	}

}