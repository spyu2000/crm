package com.huaxin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.huaxin.bean.Customer;
import com.huaxin.exception.ApplyException;
import com.huaxin.mapper.CustomerMapper;
import com.huaxin.pager.PageModel;
import com.huaxin.service.CustomerInfoService;

@Service("customerInfoService")
public class CustomerInfoServiceImpl implements CustomerInfoService {
	
	private CustomerMapper customerMapper;
	@Resource(name="customerMapper")
	public void setCustomerMapper(CustomerMapper customerMapper) {
		System.out.println("---------------customerMapper");
		this.customerMapper = customerMapper;
	}
	
	public String getCustomers(PageModel pageModel) {
		/*
		 * 
	{                                                      
	"total":239,                                                      
	"rows":[                                                         
		{"code":"001","name":"Name 1","addr":"Address 11","col4":"col4 data"}
			]                                                          
	}   
		 */
		String result = "";
		JSONObject json = new JSONObject();
		List<Map> rows = new ArrayList<Map>();
		List<Customer> customers = this.customerMapper.getAll(pageModel);
		
		for(Customer customer : customers) {
			Map<String, Object> row = new HashMap<String, Object>();
			row.put("name", customer.getName());
			row.put("customerId", customer.getCustomerId());
			row.put("email", customer.getEmail());
			row.put("address", customer.getAddress());
			row.put("mobile", customer.getMobile());
			rows.add(row);
		}
		
		try {
			json.put("total", this.customerMapper.count(pageModel));
			json.put("rows", rows);
			
			result = json.toString();
		} catch (JSONException e) {
		}
		
		return result;
	}

	public void addOrUpdateCustomer(Customer customer) throws ApplyException {
		if(customer.getCustomerId() != null) {
			this.customerMapper.update(customer);
		} else {
			this.customerMapper.save(customer);
		}
	}

	public void deleteCustomers(List<Integer> cids) throws ApplyException {
		for(Integer cid : cids) {
			this.customerMapper.delete(cid);
		}
	}

	public int insertCustomer(Customer customer) throws ApplyException {
		return this.customerMapper.insert(customer);
	}
}
