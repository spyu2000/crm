package com.huaxin.bean;

import java.util.HashSet;
import java.util.Set;

public class Permission {
	
	private Integer permId;
	private Integer parentId;
	private String permName;
	private String permUrl;
	//拥有该权限的角色
	private Set<RoleInfo> roles = new HashSet<RoleInfo>(0);
	
	public Integer getPermId() {
		return permId;
	}
	public void setPermId(Integer permId) {
		this.permId = permId;
	}
	
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getPermName() {
		return permName;
	}
	public void setPermName(String permName) {
		this.permName = permName;
	}
	public String getPermUrl() {
		return permUrl;
	}
	public void setPermUrl(String permUrl) {
		this.permUrl = permUrl;
	}
	public Set<RoleInfo> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleInfo> roles) {
		this.roles = roles;
	}
	
}
