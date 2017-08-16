/**
 * 
 */
package com.lc.df.customermanagement.repository;

import java.util.List;

import org.springframework.data.couchbase.core.query.View;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.repository.CrudRepository;

import com.lc.df.customermanagement.domain.Customer;


/**
 * @author mallrao
 *
 */
@ViewIndexed(designDoc = "customer")
//@N1qlPrimaryIndexed
public interface CustomerRepository extends CrudRepository<Customer, String> {
	@View(viewName="findByFirstName")
	public List<Customer> findByFirstName(String firstName);
	@View(viewName="findByLastName")
	public List<Customer> findByLastName(String lastName);
	@View(viewName="findByPostal")
	public List<Customer> findByPostalCode(String postalCode);
	@View(viewName="findByEmailId")
	public List<Customer> findByEmailId(String emailId);
	@View(viewName="findByPhoneNumber")
	public List<Customer> findByPhoneNumber(String phoneNumber);
	@View(viewName="findByStatus")
	public List<Customer> findByStatus(String status);
	@View(viewName="findByCategory")
	public List<Customer> findByCategory(String category);

}
