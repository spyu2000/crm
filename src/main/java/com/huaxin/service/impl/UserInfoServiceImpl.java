package com.huaxin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.huaxin.bean.UserInfo;
import com.huaxin.exception.ApplyException;
import com.huaxin.mapper.UserInfoMapper;
import com.huaxin.service.UserInfoService;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {
	private UserInfoMapper userInfoMapper;
	@Resource(name="userInfoMapper")
	public void setUserInfoMapper(UserInfoMapper userInfoMapper) {
		System.out.println("---------------userInfoMapper");
		this.userInfoMapper = userInfoMapper;
	}
	public UserInfo findByName(String username) {
		return this.userInfoMapper.findByName(username);
	}
	public Map<String, Object> findByRole(int roleId) {
		Map<String, Object> result = new HashMap<String, Object>(2); 
		List<UserInfo> users = this.userInfoMapper.findByRole(roleId);
		result.put("total", users.size());
		result.put("rows", users);
		return result;
	}
	public String getRoleUser(int roleId) {
		List<Map> rows = new ArrayList<Map>();
		List<UserInfo> allUsers = this.userInfoMapper.getAll();
		List<UserInfo> hasUsers = this.userInfoMapper.getRoleUser(roleId);
		
		for(UserInfo user : allUsers) {
			Map<String, Object> oneProperty = new HashMap<String, Object>();
			oneProperty.put("userId", user.getUserId());
			oneProperty.put("loginName", user.getLoginName());
			for(UserInfo hasUser : hasUsers) {
				if(hasUser.getUserId() == user.getUserId()) {
					oneProperty.put("ck", "true");
					break;
				}
			}
			rows.add(oneProperty);
		}
		String result = "";
		JSONObject json = new JSONObject();
		
		try {
			json.put("total", allUsers.size());
			json.put("rows", rows);
			result = json.toString();
		} catch (JSONException e) {
			
		}
		return result;
	}
	public void insertRoleUser(int roleId, List<Integer> users) throws ApplyException {
		try {
			this.userInfoMapper.deleteByRole(roleId);
			for(Integer userId : users) {
				this.userInfoMapper.insertByRole(roleId, userId);
			}
		} catch(DataAccessException e) {
			throw new ApplyException(e.getMessage());
		}
	}
}
