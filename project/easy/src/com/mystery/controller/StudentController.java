package com.mystery.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mystery.entity.Student;
import com.mystery.service.StudentService;
import com.utils.BaseController;

@Controller
@RequestMapping("/mystery/Student")
public class StudentController extends BaseController {

	@Autowired
	private StudentService studentService;

	@RequestMapping("/selectByDepartment")
	@ResponseBody
	// @Cacheable(cacheNames = "cache0")
	public Map<String, Object> getStudentByDepartment(String department, String sex, Integer row, Integer account) {

		System.out.println("进入");
		return studentService.getStudentByDepartment(department, sex, row, account);
	}

	@RequestMapping("/deleteById")
	@ResponseBody
	public String deleteById(Integer id) {

		studentService.deleteById(id);
		return "succeed";
	}

	@RequestMapping("/selectById")
	@ResponseBody
	public Student selectById(Integer id) {

		Student s = studentService.selectById(id);
		return s;
	}

	@RequestMapping("/selectAll")
	@ResponseBody
	public List<Student> selectAll() {

		List<Student> list = studentService.selectAll();
		for (Student student : list) {
			System.out.println(student.toString());
		}
		return list;

	}

}