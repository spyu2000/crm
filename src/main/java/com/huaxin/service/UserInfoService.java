package com.huaxin.service;

import java.util.List;
import java.util.Map;

import com.huaxin.bean.UserInfo;
import com.huaxin.exception.ApplyException;

public interface UserInfoService {
	UserInfo findByName(String username);
	Map<String, Object> findByRole(int roleId);
	
	String getRoleUser(int roleId);
	void insertRoleUser(int roleId, List<Integer> users) throws ApplyException;
}
