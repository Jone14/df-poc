package com.lc.df.customermanagement.web;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.lc.df.customermanagement.domain.Customer;
import com.lc.df.customermanagement.service.api.CustomerManagementService;


@RunWith(SpringRunner.class)
@WebMvcTest(value = CustomerManagementServiceController.class, secure = false)
public class CustomerManagementServiceControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private CustomerManagementService customerManagementService;
	List<Customer> listOfCustomers;
	Customer customer;
	String expected;
	String expectedObject;
	String customerRequest;
	@Before
	public void setUp() throws Exception {
		listOfCustomers=new ArrayList<>();
		customer= new Customer("customerId", "firstName", "lastName", "addressLine1", "addressLine2","postalCode", "emailId", "phoneNumber", "status", "category");
		listOfCustomers.add(customer);
		expected = "[{customerId:customerId, firstName:firstName, lastName:lastName, addressLine1:addressLine1, addressLine2:addressLine2,postalCode:postalCode, emailId:emailId, phoneNumber:phoneNumber, status:status, category:category}]";
		expectedObject =expected.substring(1, expected.length()-1);
		customerRequest="{\"customerId\":\"customerId\", \"firstName\":\"firstName\", \"lastName\":\"lastName\", \"addressLine1\":\"addressLine1\", \"addressLine2\":\"addressLine2\",\"postalCode\":\"postalCode\", \"emailId\":\"emailId\", \"phoneNumber\":\"phoneNumber\", \"status\":\"status\", \"category\":\"category\"}";
	}

	@Test
	public void testGetListOfCustomers() throws Exception{
		
		Mockito.when(customerManagementService.getAllCustomers()).thenReturn(listOfCustomers);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customers").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testCreateCustomer() throws Exception{
		Mockito.when(customerManagementService.createCustomer(Mockito.any(Customer.class))).thenReturn(customer);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customers/create").accept(MediaType.APPLICATION_JSON).content(customerRequest).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals(expectedObject, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testUpdateCustomer() throws Exception{
		String id="1";
		Mockito.when(customerManagementService.getCustomer(Mockito.anyString())).thenReturn(customer);
		Mockito.when(customerManagementService.updateCustomer(Mockito.any(Customer.class))).thenReturn(customer);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customers/update/"+id).accept(MediaType.APPLICATION_JSON).content(customerRequest).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals(expectedObject, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testDeleteCustomer() throws Exception {
		String id="1";
		String deleteInfo="Customer Id#"+id+" deleted successfully";
		Mockito.when(customerManagementService.deleteCustomer(Mockito.anyString())).thenReturn(deleteInfo);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customers/delete/"+id).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Assert.assertEquals(deleteInfo, result.getResponse().getContentAsString());
	}

	@Test
	public void testGetCustomer() throws Exception {
		String id="1";
		Mockito.when(customerManagementService.getCustomer(Mockito.anyString())).thenReturn(customer);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customers/get/"+id).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals(expectedObject, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testGetCustomerByFirstName()throws Exception  {
		Mockito.when(customerManagementService.getCustomerByFirstName(Mockito.anyString())).thenReturn(listOfCustomers);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customers/get/firstname/firstName").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testGetCustomerByLastName()throws Exception  {
		Mockito.when(customerManagementService.getCustomerByLastName(Mockito.anyString())).thenReturn(listOfCustomers);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customers/get/lastname/lastName").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testGetCustomerByPostalCode()throws Exception  {
		Mockito.when(customerManagementService.getCustomerByPostalCode(Mockito.anyString())).thenReturn(listOfCustomers);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customers/get/postalcode/postalCode").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testGetCustomerByEmailId()throws Exception  {
		Mockito.when(customerManagementService.getCustomerByEmailId(Mockito.anyString())).thenReturn(listOfCustomers);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customers/get/email/emailId").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testGetCustomerByPhoneNumber() throws Exception {
		Mockito.when(customerManagementService.getCustomerByPhoneNumber(Mockito.anyString())).thenReturn(listOfCustomers);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customers/get/phone/phoneNumber").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testGetCustomerByStatus()throws Exception  {
		Mockito.when(customerManagementService.getCustomerByStatus(Mockito.anyString())).thenReturn(listOfCustomers);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customers/get/status/status").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	
	}

	@Test
	public void testGetCustomerByCategory()throws Exception  {
		Mockito.when(customerManagementService.getCustomerByCategory(Mockito.anyString())).thenReturn(listOfCustomers);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customers/get/category/category").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	
	}

}
