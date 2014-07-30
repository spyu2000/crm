package com.huaxin.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huaxin.exception.ApplyException;
import com.huaxin.service.UserInfoService;

@Controller
@RequestMapping(value="/user")
public class UserController extends BaseController {
	
private UserInfoService userInfoService;
	
	@Resource(name="userInfoService")
	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}
	
	
	@RequestMapping(value="/role",method=RequestMethod.POST)
	public void getRolePerm(Integer roleId, HttpServletResponse response) {
		
		String result = this.userInfoService.getRoleUser(roleId);
		try {
			this.renderText(response, result);
		} catch (IOException e) {
			
		}
	}
	
	@RequestMapping(value="/roleUser",method=RequestMethod.GET)
	public @ResponseBody Map<String, String> addRolePerm(@RequestParam("roleId")String roleId, @RequestParam("users")List<String> users) {
		Map<String, String> map = new HashMap<String, String>(1);
		List<Integer> ids = new ArrayList<Integer>();
		
		for(String cid : users) {
			ids.add(Integer.parseInt(cid));
		}
		try {
			this.userInfoService.insertRoleUser(Integer.parseInt(roleId), ids);
		} catch (NumberFormatException e) {
			map.put(AJAX_MESSAGE, "false");
		} catch (ApplyException e) {
			map.put(AJAX_MESSAGE, "false");
		}
		map.put(AJAX_MESSAGE, "true");
		return map;
	}
	
	@RequestMapping(value="/crew",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> getRoleCrews(@RequestParam("roleId") int roleId) {
		//当请求路径没有roleId的时候，报400错误
		return this.userInfoService.findByRole(roleId);
	}
}
