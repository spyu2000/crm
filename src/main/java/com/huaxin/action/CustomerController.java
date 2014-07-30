package com.huaxin.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huaxin.bean.Customer;
import com.huaxin.exception.ApplyException;
import com.huaxin.pager.PageModel;
import com.huaxin.service.CustomerInfoService;
import com.huaxin.util.ApplyUtil;
import com.huaxin.vo.DataGridModel;

@Controller
@RequestMapping(value="/customer")
public class CustomerController extends BaseController {
	private CustomerInfoService customerInfoService;
	@Resource(name="customerInfoService")
	public void setCustomerInfoService(CustomerInfoService customerInfoService) {
		this.customerInfoService = customerInfoService;
	}
	
	@RequestMapping(value="/list", method=RequestMethod.POST)
	public void getDateGrid(DataGridModel dgm,  Customer customer, HttpServletResponse response) {
		PageModel pageModel = new PageModel(dgm.getPage(), dgm.getRows());
		pageModel.setCondition(customer);
		String result = this.customerInfoService.getCustomers(pageModel);
		try {
			this.renderText(response, result);
		} catch (IOException e) {
		}
	}
	
	@RequestMapping(value="/addWin", method=RequestMethod.GET)
	public String addWin() {
		return "customer/customerAdd";
	}
	
	@RequestMapping(value="/del", method=RequestMethod.GET)
	public @ResponseBody Map<String, String> delete(@RequestParam("cid") List<String> cids) {
		Map<String, String> map = new HashMap<String, String>(1);
		try {
			List<Integer> ids = new ArrayList<Integer>();
			
			for(String cid : cids) {
				ids.add(Integer.parseInt(cid));
			}
			this.customerInfoService.deleteCustomers(ids);
			map.put(AJAX_MESSAGE, "true");
		} catch(NumberFormatException e) {
			map.put(AJAX_MESSAGE, "false");
		} catch (ApplyException e) {
			map.put(AJAX_MESSAGE, "false");
		}
		return map;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public @ResponseBody Map<String, String> addCustomer(@RequestBody Customer customer) {
		Map<String, String> map = new HashMap<String, String>(1);
		
		try {
			if(!ApplyUtil.nullOrBlank(customer.getName())) {
				this.customerInfoService.addOrUpdateCustomer(customer);
			}
			map.put(AJAX_MESSAGE, "true");
		} catch (ApplyException e) {
			map.put(AJAX_MESSAGE, "false");
		}
		return map;
	}
	
	
}
