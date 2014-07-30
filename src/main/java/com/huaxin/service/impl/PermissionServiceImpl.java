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

import com.huaxin.bean.Permission;
import com.huaxin.exception.ApplyException;
import com.huaxin.mapper.PermissionMapper;
import com.huaxin.service.PermissionService;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
	private PermissionMapper permissionMapper;
	@Resource(name="permissionMapper")
	public void setPermissionMapper(PermissionMapper permissionMapper) {
		System.out.println("---------------permissionMapper");
		this.permissionMapper = permissionMapper;
	}
	
	public List<Permission> getAll() {
		return this.permissionMapper.getAll();
	}

	public String getRolePerm(int roleId) {
		
		List<Map> rows = new ArrayList<Map>();
		//所有权限
		List<Permission> perms = this.permissionMapper.getAll();
		//该角色拥有的权限
		List<Permission> hasPerms = this.permissionMapper.getPermByRole(roleId);
		
		for(Permission perm : perms) {
			if(perm.getParentId() != null && perm.getParentId() != 0) {
				Map<String, Object> oneProperty = new HashMap<String, Object>();
				oneProperty.put("permId", perm.getPermId());
				oneProperty.put("permName", perm.getPermName());
				for(Permission hasPerm : hasPerms) {
					if(perm.getPermId() == hasPerm.getPermId()) {
						oneProperty.put("ck", "true");
						break;
					}
				}
				rows.add(oneProperty);
			}
		}
		String result = "";
		JSONObject json = new JSONObject();
		
		try {
			json.put("total", perms.size());
			json.put("rows", rows);
			result = json.toString();
		} catch (JSONException e) {
			
		}
		return result;
	}

	public void insertRolePerm(int roleId, List<Integer> perms) throws ApplyException {
		//使用这用模式
		try {
			this.permissionMapper.deleteByRole(roleId);
			for(Integer perm : perms) {
				this.permissionMapper.insertByRole(roleId, perm);
			}
		} catch(DataAccessException e) {
			throw new ApplyException(e.getMessage());
		}
	}
}
