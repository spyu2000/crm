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
import com.huaxin.service.PermissionService;

@Controller
@RequestMapping(value="/perm")
public class PermissionController extends BaseController {
	private PermissionService permissionService ;

	@Resource(name="permissionService")
	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}
	
	
	@RequestMapping(value="/role",method=RequestMethod.POST)
	public void getRolePerm(Integer roleId, HttpServletResponse response) {
		
		String result = this.permissionService.getRolePerm(roleId);
		try {
			this.renderText(response, result);
		} catch (IOException e) {
			
		}
	}
	
	@RequestMapping(value="/rolePerm",method=RequestMethod.GET)
	public @ResponseBody Map<String, String> addRolePerm(@RequestParam("roleId")String roleId, @RequestParam("perms")List<String> perms) {
		Map<String, String> map = new HashMap<String, String>(1);
		List<Integer> ids = new ArrayList<Integer>();
		
		for(String cid : perms) {
			ids.add(Integer.parseInt(cid));
		}
		try {
			this.permissionService.insertRolePerm(Integer.parseInt(roleId), ids);
		} catch (NumberFormatException e) {
			map.put(AJAX_MESSAGE, "false");
		} catch (ApplyException e) {
			map.put(AJAX_MESSAGE, "false");
		}
		map.put(AJAX_MESSAGE, "true");
		return map;
	}
	
}
