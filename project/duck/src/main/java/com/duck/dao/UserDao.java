package com.duck.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.duck.entity.User;

public interface UserDao extends JpaRepository<User, Integer> {

}
