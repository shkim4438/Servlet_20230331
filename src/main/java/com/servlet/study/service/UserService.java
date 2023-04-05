package com.servlet.study.service;

import com.servlet.study.entity.User;

public interface UserService {
	public int addUser(User user);
	public User getUser(String username);
	public boolean duplicatedUsername(String username);
}
