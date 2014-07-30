package com.huaxin.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huaxin.bean.RoleInfo;
import com.huaxin.exception.ApplyException;
import com.huaxin.service.RoleInfoService;
import com.huaxin.service.PermissionService;
import com.huaxin.service.UserInfoService;
import com.huaxin.util.ApplyUtil;

@Controller
@RequestMapping(value="/role")
public class RoleController extends BaseController {
	private RoleInfoService roleInfoService;
	
	@Resource(name="roleInfoService")
	public void setRoleInfoService(RoleInfoService roleInfoService) {
		this.roleInfoService = roleInfoService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String list(ModelMap model) {
		model.put("roles", this.roleInfoService.getAll());
		return "role/list";
	}
	
	
	@RequestMapping(value="/permWin",method=RequestMethod.GET)
	public String getPermWin(@RequestParam("roleId") int roleId, ModelMap model) {
		model.put("roleId", roleId);
		return "role/permWin";
	}
	@RequestMapping(value="/userWin",method=RequestMethod.GET)
	public String getUserWin(@RequestParam("roleId") int roleId, ModelMap model) {
		model.put("roleId", roleId);
		return "role/userWin";
	}
	
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public @ResponseBody Map<String, String> addRole(@RequestBody RoleInfo role) {
		Map<String, String> map = new HashMap<String, String>(1);
		
		try {
			if(!ApplyUtil.nullOrBlank(role.getRoleName())) {
				this.roleInfoService.addOrUpdateRole(role);
			}
			map.put(AJAX_MESSAGE, "true");
		} catch (ApplyException e) {
			map.put(AJAX_MESSAGE, "false");
		}
		return map;
	}
	

	@RequestMapping(value="/del", method=RequestMethod.GET)
	public @ResponseBody Map<String, String> delete(@RequestParam("rid") String rid) {
		Map<String, String> map = new HashMap<String, String>(1);
		try {
			
			this.roleInfoService.deleteRole(Integer.parseInt(rid));
			map.put(AJAX_MESSAGE, "true");
			
		} catch(NumberFormatException e) {
			map.put(AJAX_MESSAGE, "false");
		} catch (ApplyException e) {
			map.put(AJAX_MESSAGE, "false");
		}
		return map;
	}
	
	@RequestMapping(value="/perm",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> getRolePermissions(@RequestParam("roleId") int roleId) {
		return this.roleInfoService.getPermissonData(roleId);
	}
}
