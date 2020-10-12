package com.mystery.dao;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mystery.entity.Student;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-app.xml,spring-mvc.xml,spring-redis.xml"})
@Transactional
public class StudentMapperTest {

	@Autowired
	private StudentMapper studentMapper;

	@Test
	public void testGetStudentNumber() {
		List<Student> list = studentMapper.getStudentNumber(null, "男");
		System.out.println(list.size());
		for (Student student : list) {
			System.out.println(student.toString());
		}
		 //fail("Not yet implemented");
	}

}
