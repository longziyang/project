package com.mystery.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.mystery.dao.StudentMapper;
import com.mystery.entity.Student;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Transactional
@Service
public class StudentService {
	@Autowired
	private StudentMapper studentMapper;

	public List<Student> selectAll() {

		return studentMapper.selectAll();
	}

	public void deleteById(Integer id) {
		studentMapper.deleteByPrimaryKey(id);
	}

	public Student selectById(Integer id) {

		Student s = new Student();
		s.setId(id);
		return studentMapper.selectByPrimaryKey(s);

	}

	// 接受前端传来的值 院系 ，性别，展示到第几行了 ，每个页数固定条数条数
	public Map<String, Object> getStudentByDepartment(String department, String sex, Integer row, Integer account) {

		// 创建查询
		Example example = new Example(Student.class);
		Criteria criteria = example.createCriteria();
		if (department != null && !department.equals(""))
			criteria.andCondition("department=", department);
		if (sex != null && !sex.equals(""))
			criteria.andCondition("sex=", sex);

		// 获取总条数
		int number = studentMapper.selectCountByExample(example);
		int pageNum = number % account == 0 ? number % account : number % account + 1;
		System.out.println("pageNum=" + pageNum);

		// 分页每次截取的条数
		PageHelper.offsetPage((row - 1) * account, account);
		List<Student> list = studentMapper.selectByExample(example);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNum", pageNum);
		map.put("list", list);

		return map;
	}

}