package com.huaxin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.huaxin.util.Constant;

public class ValidateCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String AUTH_CODE = "";
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		// 设置响应的类型格式为图片格式
		response.setContentType("image/jpeg");
		//禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		ServletOutputStream out = null;
		try {
			out = response.getOutputStream();
			
			HttpSession session = request.getSession();
			
			ValidateCode vCode = new ValidateCode(70,30,4,5);
			String randCode = vCode.getCode();
			
			session.setAttribute(Constant.AUTH_CODE, randCode);
			
			vCode.write(out);
		} catch(IOException e) {
			
		} finally {
			try {
				if(out != null) {
					out.close();
				}
			} catch (IOException e) {
			}
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}