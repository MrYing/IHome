package com.ihome.dao;

import java.util.List;

import javax.swing.border.EmptyBorder;

import com.ihome.annotation.MyBatisRepository;
import com.ihome.entity.User;

@MyBatisRepository
public interface UserDao {
	boolean save(User user);
	List<User> findAll();
	User findByName(String name);
	boolean updateUser(User user);
	User findById(int id);
}
