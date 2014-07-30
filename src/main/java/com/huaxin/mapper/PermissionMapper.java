package com.huaxin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.huaxin.bean.Customer;
import com.huaxin.bean.Permission;
import com.huaxin.pager.BaseDao;

public interface PermissionMapper extends BaseDao<Permission> {
	
	List<Permission> getPermByRole(int roleId);
	
	void insertByRole(@Param("roleId") Integer roleId, @Param("permId") Integer permId);
	void deleteByRole(Integer roleId);
}
