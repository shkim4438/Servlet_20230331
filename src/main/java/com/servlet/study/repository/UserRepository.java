package com.servlet.study.repository;

import com.servlet.study.entity.User;

public interface UserRepository {
	public int save(User user);
	public User findUserByUsername(String username);
	
}
