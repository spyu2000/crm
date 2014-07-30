package com.huaxin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.huaxin.bean.UserInfo;
import com.huaxin.pager.BaseDao;

@Component("userInfoMapper")
public interface UserInfoMapper extends BaseDao<UserInfo> {
	public UserInfo findByName(String username);
	List<UserInfo> findByRole(int roleId);
	List<UserInfo> getRoleUser(int roleId);
	
	void insertByRole(@Param("roleId") Integer roleId, @Param("userId") Integer userId);
	void deleteByRole(Integer roleId);
}
