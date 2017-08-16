/**
 * 
 */
package com.lc.df.customermanagement.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lc.df.customermanagement.domain.Customer;
import com.lc.df.customermanagement.jms.MQComponent;
import com.lc.df.customermanagement.repository.CustomerRepository;
import com.lc.df.customermanagement.service.api.CustomerManagementService;
import com.lc.df.customermanagement.service.kafka.KafkaComponent;

/**
 * @author mallrao
 *
 */
@Service
public class CustomerManagementServiceImpl implements CustomerManagementService{
	
	@Autowired
	private MQComponent activeMQComponent;
	
	@Autowired
	private KafkaComponent kafkaComponent;
	
	@Autowired
	CustomerRepository repo;
	
	public List<Customer> getAllCustomers() {
		Iterator<Customer> customerIterator = repo.findAll().iterator();
		List<Customer> customers = new ArrayList<>();
		//customerIterator.forEachRemaining(customers::add);
		while(customerIterator.hasNext()) {
			customers.add(customerIterator.next());
        }
		return customers;
	}

	public Customer getCustomer(String id) {
		return repo.findOne(id);
	}
	
	public List<Customer> getCustomerByFirstName(String firstName) {		
		return repo.findByFirstName(firstName);
	}	
	
	public List<Customer> getCustomerByLastName(String lastName) {		
		return repo.findByLastName(lastName);
	}
	
	public List<Customer> getCustomerByPostalCode(String postalCode) {		
		return repo.findByPostalCode(postalCode);
	}
	
	public List<Customer> getCustomerByEmailId(String emailId) {		
		return repo.findByEmailId(emailId);
	}
	
	public List<Customer> getCustomerByPhoneNumber(String phoneNumber) {		
		return repo.findByPhoneNumber(phoneNumber);
	}
	
	public List<Customer> getCustomerByStatus(String status) {		
		return repo.findByStatus(status);
	}
	
	public List<Customer> getCustomerByCategory(String category) {		
		return repo.findByCategory(category);
	}

	public Customer createCustomer(Customer customer) {
		return repo.save(customer);
	}
	
	public Customer updateCustomer(Customer customer) {
		return repo.save(customer);
	}

	public String deleteCustomer(String id) {
		repo.delete(id);
		return "Customer Id#"+id+" deleted successfully";
	}
	
	public void sendMessageToMQ(String message) {
		activeMQComponent.send(message);
	}
	
	public void sendMessageToKafka(String topic,String message) {
		kafkaComponent.sendMessages(topic, message);
	}
	public void sendMessageToDefaultTopic(String message) {
		sendMessageToKafka(null, message);
	}

}
