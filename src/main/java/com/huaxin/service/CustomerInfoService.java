package com.huaxin.service;

import java.util.List;
import java.util.Map;

import com.huaxin.bean.Customer;
import com.huaxin.exception.ApplyException;
import com.huaxin.pager.PageModel;


public interface CustomerInfoService {
	
	public String getCustomers(PageModel pageModel);
	public void addOrUpdateCustomer(Customer customer) throws ApplyException;
	public void deleteCustomers(List<Integer> cids) throws ApplyException;
	
	public int insertCustomer(Customer customer) throws ApplyException;
}
