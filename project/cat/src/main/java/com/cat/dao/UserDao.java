package com.cat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cat.entity.User;

public interface UserDao extends JpaRepository<User, Integer>{

}
