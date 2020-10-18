package com.cat.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cat.entity.Mesg;

public interface MesgDao extends JpaRepository<Mesg, Integer> {

	@Query(value = "select * from mesg where content=:content", nativeQuery = true)
	public Mesg getMesgByContent(@Param("content") String content);
}