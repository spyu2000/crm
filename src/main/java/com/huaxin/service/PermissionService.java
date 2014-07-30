package com.huaxin.service;

import java.util.List;
import java.util.Map;

import com.huaxin.bean.Permission;
import com.huaxin.exception.ApplyException;

public interface PermissionService {
	List<Permission> getAll();
	String getRolePerm(int roleId);
	void insertRolePerm(int roleId, List<Integer> perms) throws ApplyException;
}
