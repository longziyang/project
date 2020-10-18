package com.dog.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.dog.entity.Mes;

public interface MesDao extends JpaRepository<Mes, Integer> {

	@Query(value = "select id,content,state from mes where state=0", nativeQuery = true)
	public List<Mes> getMesByState();

}
