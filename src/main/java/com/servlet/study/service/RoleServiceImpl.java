package com.servlet.study.service;

import com.servlet.study.entity.Role;
import com.servlet.study.entity.User;
import com.servlet.study.repository.UserRepository;
import com.servlet.study.repository.RoleRepository;
import com.servlet.study.repository.RoleRepositoryImpl;

public class RoleServiceImpl implements RoleService {
	
	// Service 객체 싱글톤 정의
	private static RoleServiceImpl instance;
	public static RoleServiceImpl getInstance() {
		if(instance == null) {
			instance = new RoleServiceImpl();
		}
		return instance;
	}
	
	// repository 객체 DI(Dependency Injection): 의존관계 주입
	private RoleRepository roleRepository;
	
	private RoleServiceImpl() {
		roleRepository = RoleRepositoryImpl.getInstance();
	}

	@Override
	public Role getRole(String roleName) {
		return roleRepository.findRoleByRoleName(roleName);
	}
	
		
}
