package com.huaxin.action;


import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.huaxin.bean.RoleInfo;
import com.huaxin.bean.UserInfo;
import com.huaxin.service.RoleInfoService;
import com.huaxin.service.UserInfoService;
import com.huaxin.util.ApplyUtil;
import com.huaxin.util.Constant;
import com.huaxin.vo.LoginModel;

@Controller
@RequestMapping(value="/login")
public class LoginController extends BaseController {
	
	private UserInfoService userInfoService;
	@Resource(name="userInfoService")
	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}
	
	private RoleInfoService roleInfoService;
	@Resource(name="roleInfoService")
	public void setUserInfoService(RoleInfoService roleInfoService) {
		this.roleInfoService = roleInfoService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.setAttribute(Constant.USER_SESSION_KEY, null);
		return "redirect:login.jsp";
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public String login(LoginModel loginModel, HttpSession session, ModelMap model) {
		
		boolean allowLogin = true;
		String message = this.getMessage("login.fail");
		UserInfo user = null;
		
		String authCode = (String) session.getAttribute(Constant.AUTH_CODE);
		session.setAttribute(Constant.AUTH_CODE, null);
		if(ApplyUtil.nullOrBlank(loginModel.getAuthCode()) || !loginModel.getAuthCode().equalsIgnoreCase(authCode)) {
			allowLogin = false;
			message = this.getMessage("login.authcoed");
			debug("验证码错误！");
		} else {
			user = this.userInfoService.findByName(loginModel.getUsername());
			if(user == null) {
				allowLogin = false;
				debug("用户不存在");
			} else if(ApplyUtil.nullOrBlank(user.getLoginPasswd()) || !ApplyUtil.hash(loginModel.getPasswd()).equals(user.getLoginPasswd()) ){
				allowLogin = false;
				debug("密码错误");
			} else {
				allowLogin = true;
			}
		}
			//数据的校验
		
		if(allowLogin) {
			
			Set<RoleInfo> roles = user.getRoles();
			for(RoleInfo role : roles) {
				//这个角色拥有的权限
				role.setPermissions(this.roleInfoService.getPermissions(role.getRoleId()));
			}
			session.setAttribute(Constant.USER_SESSION_KEY, user); //名为Constants.USER_INFO_SESSION的属性放到Session属性列表中
			//跳转到/index 链接 
			return "redirect:index";
		} else {
			model.addAttribute("message", message);
			
			//跳转到/login.jsp
			return "forward:login.jsp";
		}
		
	}
	
}
