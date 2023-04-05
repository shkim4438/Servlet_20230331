package com.servlet.study.service;

import com.servlet.study.entity.User;
import com.servlet.study.repository.UserRepository;
import com.servlet.study.repository.UserRepositoryImpl;
import com.servlet.study.repository.RoleRepositoryImpl;

public class UserServiceImpl implements UserService {
	
	// Service 객체 싱글톤 정의
	private static UserServiceImpl instance;
	public static UserServiceImpl getInstance() {
		if(instance == null) {
			instance = new UserServiceImpl();
		}
		return instance;
	}
	
	// repository 객체 DI(Dependency Injection): 의존관계 주입
	private UserRepository userRepository;
	
	private UserServiceImpl() {
		userRepository = UserRepositoryImpl.getInstance();
	}
	
	@Override
	public int addUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUser(String username) {
		return userRepository.findUserByUsername(username);
	}

	@Override
	public boolean duplicatedUsername(String username) {
		User user = userRepository.findUserByUsername(username);
		
		return user != null;
	}
	
}
