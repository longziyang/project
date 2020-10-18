package com.mystery;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.mystery.entity.Student;
import com.mystery.service.StudentService;

public class Test {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring-app.xml");
		StudentService ss = ac.getBean(StudentService.class);
		List<Student> list = ss.selectAll();

		for (Student student : list) {
			System.out.println(student.toString());
		}

		// 被弃用
		@SuppressWarnings("unused")
		BeanFactory factory = new XmlBeanFactory(new ClassPathResource("com/mystery/dao/StuMapper.xml"));

	}

}
