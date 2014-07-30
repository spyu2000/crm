package com.huaxin.mapper;

import org.springframework.stereotype.Component;

import com.huaxin.bean.Customer;
import com.huaxin.exception.ApplyException;
import com.huaxin.pager.BaseDao;

public interface CustomerMapper extends BaseDao<Customer> {
	int insert(Customer customer) throws ApplyException;
}
