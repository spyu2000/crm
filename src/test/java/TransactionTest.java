

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.huaxin.bean.Customer;
import com.huaxin.exception.ApplyException;
import com.huaxin.service.CustomerInfoService;

@ContextConfiguration(locations={"/applicationContext.xml"})
public class TransactionTest extends AbstractTransactionalJUnit4SpringContextTests  {
	
	private CustomerInfoService customerInfoService;
	
	private int customerId;
	
	@Resource(name="customerInfoService")
	public void setCustomerInfoService(CustomerInfoService customerInfoService) {
		this.customerInfoService = customerInfoService;
	}
	
	@Before
	public void prepareTestDatas() {
		try {
			Customer customer = new Customer(); 
			customer.setName("测试");
			this.customerId = this.customerInfoService.insertCustomer(customer);
		} catch(ApplyException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCustomer() {
		
		Customer customer = new Customer();
		customer.setName("大旧嘿！！");
		customer.setCustomerId(this.customerId);
		
		try {
			customerInfoService.addOrUpdateCustomer(customer);
		} catch (ApplyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
