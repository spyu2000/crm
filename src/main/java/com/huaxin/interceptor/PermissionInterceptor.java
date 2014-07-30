package com.huaxin.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.huaxin.bean.RoleInfo;
import com.huaxin.bean.Permission;
import com.huaxin.bean.UserInfo;
import com.huaxin.util.ApplyUtil;
import com.huaxin.util.Constant;


public class PermissionInterceptor extends BaseInterceptor {
	
	private PermissionsMap permissionsMap;
	
	public PermissionsMap getPermissionsMap() {
		return permissionsMap;
	}
	public void setPermissionsMap(PermissionsMap permissionsMap) {
		this.permissionsMap = permissionsMap;
	}
	protected Logger logger = Logger.getLogger(getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		 
		if(decide(request, response)) {
			return true;
		} else {
			return returnException(request, response);
		}
	}
	
	//决策
	public boolean decide(HttpServletRequest request, HttpServletResponse response) {
		
		
		Integer require = this.getPermissionsMap().getAttributes(request);
		
		//不需要权限控制
		if(require == null) {
			return true;
		}
		HttpSession session = request.getSession();
		
		UserInfo user = (UserInfo) session.getAttribute(Constant.USER_SESSION_KEY);
		
		//未登录
		if(user == null) {
			return false;
		}
		
		Set<RoleInfo> roleInfos = user.getRoles();
		
		for(RoleInfo role : roleInfos) {
			for(Permission permission : role.getPermissions()) {
				if(permission.getPermId() == require) {
					return true;
				}
			}
		}
		logger.info("没有相应的权限");
		return false;
	}
	
	//是否ajax请求
	private boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		if (header != null && "XMLHttpRequest".equals(header))
			return true;
		else
			return false;
	}
	
	public boolean returnException(HttpServletRequest request, HttpServletResponse response)  {
		if(isAjaxRequest(request)) {
			
			PrintWriter out = null;
			try {
				out = response.getWriter();
				out.print(0);
				out.flush();
			} catch (IOException e) {
			} finally {
				out.close();
			}
		} else {
			if(ApplyUtil.nullOrBlank(failedUrl)) {
				failedUrl = DEFAULT_FAILED_URL;
			} 
			try {
				response.sendRedirect(request.getContextPath() + failedUrl);
			} catch (IOException e) {
			}
			
		}
		return false;
	}
	
}
