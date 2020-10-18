package com.dog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dog.entity.User;


public interface UserDao extends JpaRepository<User, Integer>{

}
