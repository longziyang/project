package com.mystery.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mystery.entity.Task;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@Transactional
public class TaskMapperTest {

	@Autowired
	private TaskMapper taskMapper;

	@Test
	public void testSelectByState() {

		List<Task> list = taskMapper.selectByState();

		for (Task task : list) {
			System.out.println(task.toString());
		}
		// fail("Not yet implemented");
	}

	@Test
	public void testUpdateByState() {
		fail("Not yet implemented");
	}

}
