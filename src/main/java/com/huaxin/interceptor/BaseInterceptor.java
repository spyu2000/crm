package com.huaxin.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class BaseInterceptor  extends HandlerInterceptorAdapter {
	protected final static String DEFAULT_FAILED_URL = "/login.jsp";
	
	protected String failedUrl;
	
	public String getFailedUrl() {
		return failedUrl;
	}

	public void setFailedUrl(String failedUrl) {
		this.failedUrl = failedUrl;
	}
	
}
