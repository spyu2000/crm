package com.huaxin.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.huaxin.bean.Permission;
import com.huaxin.service.PermissionService;
import com.huaxin.util.UrlUtil;


public class PermissionsMap {
	private static Map<String, Integer> resourceMap = null;
	protected Logger logger = Logger.getLogger(getClass());
	private PermissionService permissionService ;

	@Resource(name="permissionService")
	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
		loadResourceDefine();
	}

	
	private void loadResourceDefine() {
		logger.info(".............加载权限");
		if(resourceMap == null) {
			resourceMap = new HashMap<String, Integer>();
			List<Permission> resources = this.permissionService.getAll();
			for (Permission resource : resources) {
				resourceMap.put(resource.getPermUrl(), resource.getPermId());
			}
		}
	}
	
	//返回所请求资源所需要的权限
	public Integer getAttributes(HttpServletRequest request) throws IllegalArgumentException {
		//分析请求
		String reqUrl = request.getServletPath();
		logger.info("............请求路径..." + reqUrl);
		if(resourceMap == null) {
			loadResourceDefine();
		}
		return resourceMap.get(reqUrl);
	}
}
