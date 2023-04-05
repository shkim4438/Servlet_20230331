package com.servlet.study.repository;

import com.servlet.study.entity.Role;


public interface RoleRepository {
	public Role findRoleByRoleName(String roleName);
	
}
