package test;

import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.util.Assert;

import com.huaxin.bean.Customer;
import com.huaxin.bean.Permission;
import com.huaxin.bean.RoleInfo;
import com.huaxin.bean.UserInfo;
import com.huaxin.exception.ApplyException;
import com.huaxin.mapper.CustomerMapper;
import com.huaxin.mapper.RoleInfoMapper;
import com.huaxin.mapper.PermissionMapper;
import com.huaxin.mapper.UserInfoMapper;
import com.huaxin.pager.PageModel;
import com.huaxin.service.PermissionService;
import com.huaxin.util.ApplyUtil;
import com.huaxin.util.Constant;
import com.huaxin.util.SpringUtil;

public class MainTest {
	public static void main(String[] args) {
		JSONObject json = new JSONObject();
		/*
true
true
false
true
		 */
		//IllegalArgumentException
		System.out.println(StringUtils.isBlank(" "));
		System.out.println(StringUtils.isBlank(null));
		System.out.println(StringUtils.isEmpty(" "));
		System.out.println(StringUtils.isEmpty(null));
		//物尽其用
		//断言， 输入参数不能为空      
		//Assert.notNull(null, "输入参数不能为空");
		System.out.println(ApplyUtil.hash("admin"));
	}
	
	 
	@Test
	public void testPermission() {
		
		//SystemMenuMapper systemMenuDao = (SystemMenuMapper) SpringUtil.getBean("systemMenuMapper");
		PermissionService menuService = (PermissionService) SpringUtil.getBean("permissionService");
		List<Integer> perms = new ArrayList<Integer>();
		perms.add(3);
		menuService.insertRolePerm(new Integer(1), perms);
	}
	
	@Test
	public void testCustomer() {
		CustomerMapper customerInfoMapper = (CustomerMapper) SpringUtil.getBean("customerMapper");
		
		Customer customer = new Customer();
		customer.setName("笑笑");
		customer.setAddress("沙井");
		customer.setEmail("lihuaxin168@gmail.com");
		customer.setMobile("13430731465");
		try {
			System.out.println(customerInfoMapper.insert(customer));
			System.out.println(customer.getCustomerId());
		} catch (ApplyException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUser() {
		UserInfoMapper userInfoMapper = (UserInfoMapper) SpringUtil.getBean("userInfoMapper");
		List<UserInfo> userinfo = userInfoMapper.findByRole(2);
		
		for(UserInfo user : userinfo) {
			System.out.println(user.getLoginName());
		}
	}
	
	@Test
	public void testRole() {
		RoleInfoMapper roleInfoMapper = (RoleInfoMapper) SpringUtil.getBean("roleInfoMapper");
		RoleInfo roleinfo = roleInfoMapper.getCascade(1);
		Set<Permission> permissions = roleinfo.getPermissions();
		
		for(Permission permission : permissions) {
			System.out.println(permission.getPermName());
		}
		
	}
	
}
