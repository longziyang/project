package com.mystery.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mystery.entity.Task;

import tk.mybatis.mapper.common.Mapper;

public interface TaskMapper extends Mapper<Task> {

	public List<Task> selectByState();

	public void updateByState(@Param("idd")Integer id);
}