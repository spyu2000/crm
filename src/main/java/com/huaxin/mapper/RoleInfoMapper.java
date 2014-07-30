package com.huaxin.mapper;

import com.huaxin.bean.RoleInfo;
import com.huaxin.pager.BaseDao;

public interface RoleInfoMapper extends BaseDao<RoleInfo> {
	RoleInfo getCascade(int roleId);
	void deleteAndPerm(int roleId);
	void deleteAndUser(int roleId);
}
