package com.lc.df.customermanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.lc.df.customermanagement.domain.Customer;
import com.lc.df.customermanagement.jms.MQComponent;
import com.lc.df.customermanagement.repository.CustomerRepository;
import com.lc.df.customermanagement.service.kafka.KafkaComponent;
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes=CustomerManagementServiceImpl.class)
public class CustomerManagementServiceImplTest {
	
	@Mock
	private MQComponent activeMQComponent;
	
	@Mock
	private KafkaComponent kafkaComponent;
	
	@Mock
	CustomerRepository repo;
	
	@InjectMocks
	CustomerManagementServiceImpl customerService;
	
	Customer customer;
	List<Customer> customers;
	@Before
	public void setUp() throws Exception {
		customer= new Customer("customerId", "firstName", "lastName", "addressLine1", "addressLine2","postalCode", "emailId", "phoneNumber", "status", "category");
		customers= new ArrayList<>();
		customers.add(customer);
	}

	@Test
	public void testGetAllCustomers() {
		Mockito.when(repo.findAll()).thenReturn(customers);
		List<Customer> customersOutput = customerService.getAllCustomers();
		Assert.assertEquals(customersOutput.get(0).getCustomerId(), customer.getCustomerId());
	}

	@Test
	public void testGetCustomer() {
		String id="1";
		Mockito.when(repo.findOne(id)).thenReturn(customer);
		Customer newcustomer=customerService.getCustomer(id);
		Assert.assertEquals(customer, newcustomer);
	}

	@Test
	public void testGetCustomerByFirstName() {
		String id="firstName";
		Mockito.when(repo.findByFirstName(id)).thenReturn(customers);
		List<Customer> newcustomers=customerService.getCustomerByFirstName(id);
		Assert.assertEquals(customers, newcustomers);
	}

	//@Test
	public void testGetCustomerByLastName() {
		String id="lastName";
		Mockito.when(repo.findByLastName(id)).thenReturn(customers);
		List<Customer> newcustomers=customerService.getCustomerByLastName(id);
		Assert.assertEquals(customers, newcustomers);
	}

	@Test
	public void testGetCustomerByPostalCode() {
		String id="postalCode";
		Mockito.when(repo.findByPostalCode(id)).thenReturn(customers);
		List<Customer> newcustomers=customerService.getCustomerByPostalCode(id);
		Assert.assertEquals(customers, newcustomers);
	}

	@Test
	public void testGetCustomerByEmailId() {
		String id="person@ladbrokes.co.uk";
		Mockito.when(repo.findByEmailId(id)).thenReturn(customers);
		List<Customer> newcustomers=customerService.getCustomerByEmailId(id);
		Assert.assertEquals(customers, newcustomers);
	}

	@Test
	public void testGetCustomerByPhoneNumber() {
		String id="firstName";
		Mockito.when(repo.findByFirstName(id)).thenReturn(customers);
		List<Customer> newcustomers=customerService.getCustomerByFirstName(id);
		Assert.assertEquals(customers, newcustomers);
	}

	@Test
	public void testGetCustomerByStatus() {
		String id="status";
		Mockito.when(repo.findByStatus(id)).thenReturn(customers);
		List<Customer> newcustomers=customerService.getCustomerByStatus(id);
		Assert.assertEquals(customers, newcustomers);
	}

	@Test
	public void testGetCustomerByCategory() {
		String id="Category";
		Mockito.when(repo.findByCategory(id)).thenReturn(customers);
		List<Customer> newcustomers=customerService.getCustomerByCategory(id);
		Assert.assertEquals(customers, newcustomers);
	}

	@Test
	public void testCreateCustomer() {
		Mockito.when(repo.save(customer)).thenReturn(customer);
		Customer newcustomer=customerService.createCustomer(customer);
		Assert.assertEquals(customer, newcustomer);
	}

	@Test
	public void testUpdateCustomer() {
		Mockito.when(repo.save(customer)).thenReturn(customer);
		Customer newcustomer=customerService.createCustomer(customer);
		Assert.assertEquals(customer, newcustomer);
	}

	@Test
	public void testDeleteCustomer() {
		String id="1";
		Assert.assertEquals("Customer Id#"+id+" deleted successfully", customerService.deleteCustomer(id));
	}
}
