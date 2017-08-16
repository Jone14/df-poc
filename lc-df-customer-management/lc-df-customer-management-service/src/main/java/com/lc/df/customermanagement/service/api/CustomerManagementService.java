/**
 * 
 */
package com.lc.df.customermanagement.service.api;


import java.util.List;
import com.lc.df.customermanagement.domain.Customer;

/**
 * @author mallrao
 *
 */
public interface CustomerManagementService {
	
	public List<Customer> getAllCustomers();

	public Customer getCustomer(String id);
	
	public List<Customer> getCustomerByFirstName(String firstName);
	
	public List<Customer> getCustomerByLastName(String lastName);
	
	public List<Customer> getCustomerByPostalCode(String postalCode);
	
	public List<Customer> getCustomerByEmailId(String emailId);
	
	public List<Customer> getCustomerByPhoneNumber(String phoneNumber);
	
	public List<Customer> getCustomerByStatus(String status);
	
	public List<Customer> getCustomerByCategory(String category);

	public Customer createCustomer(Customer customer);
	
	public Customer updateCustomer(Customer customer);

	public String deleteCustomer(String id);
	
	public void sendMessageToMQ(String message);
	
	public void sendMessageToKafka(String topic,String message);
	
	public void sendMessageToDefaultTopic(String message);

}
