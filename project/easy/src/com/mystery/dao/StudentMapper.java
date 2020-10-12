package com.mystery.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mystery.entity.Student;

import tk.mybatis.mapper.common.Mapper;

public interface StudentMapper extends Mapper<Student> {

	public List<Student> getStudentNumber(@Param("department") String department, @Param("sex") String sex);
}