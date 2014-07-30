package com.huaxin.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.huaxin.bean.RoleInfo;
import com.huaxin.bean.Permission;
import com.huaxin.exception.ApplyException;
import com.huaxin.mapper.RoleInfoMapper;
import com.huaxin.service.RoleInfoService;

@Service("roleInfoService")
public class RoleInfoServiceImpl implements RoleInfoService {

	private RoleInfoMapper roleInfoMapper;
	@Resource(name="roleInfoMapper")
	public void setRoleInfoMapper(RoleInfoMapper roleInfoMapper) {
		this.roleInfoMapper = roleInfoMapper;
	}
	public Set<Permission> getPermissions(int roleId) {
		RoleInfo ri = this.roleInfoMapper.getCascade(roleId);
		return (ri != null ? ri.getPermissions() : new HashSet<Permission>());
	}
	
	public Map<String, Object> getPermissonData(int roleId) {
		Map<String, Object> result = new HashMap<String, Object>(2); 
		Set<Permission> permissions = getPermissions(roleId);
		result.put("total", permissions.size());
		result.put("rows", permissions);
		return result;
	}
	public List<RoleInfo> getAll() {
		return this.roleInfoMapper.getAll();
	}
	public void addOrUpdateRole(RoleInfo role)throws ApplyException {
		try {
			
			if(role.getRoleId() != null) {
				this.roleInfoMapper.update(role);
			} else {
				this.roleInfoMapper.save(role);
			}
		} catch(DataAccessException e) {
			throw new ApplyException(e.getMessage());
		}
	}
	
	public void deleteRole(Integer roleId) throws ApplyException {
		RoleInfo role = this.roleInfoMapper.get(roleId);
		try {
			//关联删除
			if(role != null ) {
				this.roleInfoMapper.deleteAndPerm(roleId);
				this.roleInfoMapper.deleteAndUser(roleId);
			}
			this.roleInfoMapper.delete(roleId);
		} catch(DataAccessException e) {
			throw new ApplyException(e.getMessage());
		}
		
	}

}
