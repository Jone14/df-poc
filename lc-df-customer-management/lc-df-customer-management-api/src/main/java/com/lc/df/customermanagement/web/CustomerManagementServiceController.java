/**
 * 
 */
package com.lc.df.customermanagement.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lc.df.customermanagement.domain.Customer;
import com.lc.df.customermanagement.service.api.CustomerManagementService;
import com.lc.df.customermanagement.service.exception.CustomerManagementServiceException;

/**
 * @author mallrao
 *
 */
@CrossOrigin
@RestController
public class CustomerManagementServiceController {
	
	@Autowired
	private CustomerManagementService customerManagementService;
	
	@RequestMapping("/customers")
	public List<Customer> getListOfCustomers(){
		 return customerManagementService.getAllCustomers();
	}
	
	@RequestMapping(method=RequestMethod.POST,value="customers/create", consumes={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}, produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public Customer createCustomer(@RequestBody Customer customer) throws Exception {
		
		Customer newCustomer = customerManagementService.createCustomer(customer);
		if(newCustomer==null){
			throw new CustomerManagementServiceException(2,"There was some problem in creating customer details, please contact system administrator");
		}
		else{
			customerManagementService.sendMessageToMQ("New customer has been created with Id, "+newCustomer.getCustomerId());
			customerManagementService.sendMessageToDefaultTopic("New customer has been created with Id, "+newCustomer.getCustomerId());
		}
		return newCustomer;
		
	}

	@RequestMapping(method=RequestMethod.POST,value="customers/update/{ID}", produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public Customer updateCustomer(@RequestBody Customer customer,@PathVariable String ID) throws Exception{
		
		Customer existingCustomer = customerManagementService.getCustomer(ID);
		
		if(existingCustomer==null){
			throw new CustomerManagementServiceException(3,"User not found!!");
		}
		customer.setCustomerId(ID);
		Customer newCustomer = customerManagementService.updateCustomer(customer);
		
		if(newCustomer==null){
			throw new CustomerManagementServiceException(2,"There was some problem in updating customer details, please contact system administrator");
		}
		else{
			customerManagementService.sendMessageToMQ("Customer has been updated with Id, "+newCustomer.getCustomerId());
			customerManagementService.sendMessageToDefaultTopic("Customer has been updated with Id, "+newCustomer.getCustomerId());
		}
		return newCustomer;
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="customers/delete/{ID}", produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public String deleteCustomer(@PathVariable String ID){
		String deletedInfo=customerManagementService.deleteCustomer(ID);
		customerManagementService.sendMessageToDefaultTopic(deletedInfo);
		return deletedInfo;
	}
	

	@RequestMapping(method=RequestMethod.GET, value="customers/get/{ID}", produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public Customer getCustomer(@PathVariable String ID) throws Exception{		
		
		Customer customer = customerManagementService.getCustomer(ID);
		
		if(customer == null){
			throw new CustomerManagementServiceException(1,"User not found");
		}
		return customer;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="customers/get/firstname/{firstName}", produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public List<Customer> getCustomerByFirstName(@PathVariable String firstName) throws Exception{
		
		List<Customer> customers = customerManagementService.getCustomerByFirstName(firstName);
		
		if(customers == null || customers.isEmpty()){
			throw new CustomerManagementServiceException(1,"User not found");
		}
		return customers;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="customers/get/lastname/{lastName}", produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public List<Customer> getCustomerByLastName(@PathVariable String lastName) throws Exception{
		
		List<Customer> customers = customerManagementService.getCustomerByLastName(lastName);
		
		if(customers == null || customers.isEmpty()){
			throw new CustomerManagementServiceException(1,"User not found");
		}
		return customers;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="customers/get/postalcode/{postalCode}", produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public List<Customer> getCustomerByPostalCode(@PathVariable String postalCode) throws Exception{
		
		List<Customer> customers = customerManagementService.getCustomerByPostalCode(postalCode);
		
		if(customers == null || customers.isEmpty()){
			throw new CustomerManagementServiceException(1,"User not found");
		}
		return customers;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="customers/get/email/{emailId}", produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public List<Customer> getCustomerByEmailId(@PathVariable String emailId) throws Exception{
		
		List<Customer> customers = customerManagementService.getCustomerByEmailId(emailId);
		
		if(customers == null || customers.isEmpty()){
			throw new CustomerManagementServiceException(1,"User not found");
		}
		return customers;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="customers/get/phone/{phoneNumber}", produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public List<Customer> getCustomerByPhoneNumber(@PathVariable String phoneNumber) throws Exception{
		
		List<Customer> customers = customerManagementService.getCustomerByPhoneNumber(phoneNumber);
		
		if(customers == null || customers.isEmpty()){
			throw new CustomerManagementServiceException(1,"User not found");
		}
		return customers;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="customers/get/status/{status}", produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public List<Customer> getCustomerByStatus(@PathVariable String status) throws Exception{
		
		List<Customer> customers = customerManagementService.getCustomerByStatus(status);
		
		if(customers == null || customers.isEmpty()){
			throw new CustomerManagementServiceException(1,"User not found");
		}
		return customers;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="customers/get/category/{category}", produces={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public List<Customer> getCustomerByCategory(@PathVariable String category) throws Exception{
		
		List<Customer> customers = customerManagementService.getCustomerByCategory(category);
		
		if(customers == null || customers.isEmpty()){
			throw new CustomerManagementServiceException(1,"User not found");
		}
		return customers;
	}

}
