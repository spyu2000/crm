package com.huaxin.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.huaxin.bean.RoleInfo;
import com.huaxin.bean.Permission;
import com.huaxin.exception.ApplyException;

public interface RoleInfoService {
	Set<Permission> getPermissions(int roleId);
	Map<String, Object> getPermissonData(int roleId);
	List<RoleInfo> getAll();
	void deleteRole(Integer roleId) throws ApplyException;
	void addOrUpdateRole(RoleInfo role) throws ApplyException;
}
