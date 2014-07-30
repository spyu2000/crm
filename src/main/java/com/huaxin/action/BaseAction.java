package com.huaxin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.huaxin.pager.PageModel;

public abstract class BaseAction {
	//reload显示用户列表 例如user.action type = "redirect"
	public static final String RELOAD = "reload";
	public static final String UPDATE = "update";
	public static final String SAVE = "save";
	public static final String LIST = "list";
	
	protected Logger logger = Logger.getLogger(getClass());
	
	//分页
	private int pageNo;
	private int pageSize;
	
	private PageModel pageModel;
	
	
	public PageModel getPageModel() {
		return pageModel;
	}
	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}
	public int getPageNo() {
		if(this.pageNo == 0) {
			this.pageNo = 1;
		}
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		if(this.pageSize == 0) {
			this.pageSize = 1;
		}
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
		
	//ajax
	public void renderText(HttpServletResponse response, String result) throws IOException {
		PrintWriter out = response.getWriter();
		response.setHeader("Cache-Control", "no-store"); 
		response.setHeader("Pragma", "no-cache"); 
		response.setDateHeader("Expires", 0); 
		out.print(result);
		out.flush();
		out.close();
	}
	
	protected String executeMethod(String method) throws Exception {
		Class[] c = null;
		Method m = this.getClass().getMethod(method, c);
		Object[] o = null;
		String result = (String) m.invoke(this, o);
		return result;
	}
	
	
	public String list() throws Exception{return null;}
	public String save() throws Exception{return null;}
	public String saveTo() throws Exception{return null;}
	public String update() throws Exception{return null;}
	public String updateTo() throws Exception{return null;}
	public String delete() throws Exception{return null;}
	
}
