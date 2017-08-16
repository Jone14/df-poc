/**
 * 
 */
package com.lc.df.customermanagement.domain;


import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.couchbase.core.mapping.Document;
import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import javax.validation.constraints.Pattern;


/**
 * @author mallrao
 *
 */
@Document
public class Customer {
	
	/**
	 * @param customerId
	 * @param firstName
	 * @param lastName
	 * @param addressLine1
	 * @param addressLine2
	 * @param postalCode
	 * @param emaiId
	 * @param phoneNumber
	 * @param status
	 * @param category
	 */
	
	@Id
	private String customerId = UUID.randomUUID().toString();
	
	@Field	
	@NotNull(message="First Name cannot be Null")
	@NotEmpty(message="First Name cannot be Empty")
	private String firstName;
	
	@Field
	@NotNull(message="Last Name cannot be Null")
	@NotEmpty(message="Last Name cannot be Empty")
	private String lastName;
	
	@Field
	@NotNull(message="AddressLine1 cannot be Null")
	@NotEmpty(message="AddressLine1 Name cannot be Empty")
	private String addressLine1;
	
	@Field
	private String addressLine2;
	
	@Field
	@NotNull(message="PostalCode cannot be Null")
	@NotEmpty(message="postalCode cannot be Empty")
	private String postalCode;
	
	@Field
	@NotNull(message="EmailId cannot be Null")
	@NotEmpty(message="Email cannot be Empty")
	@Email(message="Email should be well formed")
	private String emailId;
		
	@Field
	@NotNull(message="phoneNumber cannot be Null")
	@Pattern(regexp= "^(?:(?:\\+?1\\s*(?:[.-]\\s*)?)?(?:\\(\\s*([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\\s*\\)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\\s*(?:[.-]\\s*)?)?([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\\s*(?:[.-]\\s*)?([0-9]{4})(?:\\s*(?:#|x\\.?|ext\\.?|extension)\\s*(\\d+))?$")
	private String phoneNumber;
	
	@Field
	private String status;
	
	@Field
	@NotNull(message="category Name cannot be Null")
	@NotEmpty(message="category cannot be Empty")
	@Pattern(regexp="(PLATINUM|GOLD|SILVER|BRONZE)")
	private String category;
	
	public Customer(){
		
	}
	
	/**
	 * @param customerId
	 * @param firstName
	 * @param lastName
	 * @param addressLine1
	 * @param addressLine2
	 * @param postalCode
	 * @param emailId
	 * @param phoneNumber
	 * @param status
	 * @param category
	 */
	public Customer(String customerId, String firstName, String lastName, String addressLine1, String addressLine2,
			String postalCode, String emailId, String phoneNumber, String status, String category) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.postalCode = postalCode;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
		this.status = status;
		this.category = category;
	}
	
	
	 @Override
     public int hashCode() {
		 
		 int hash = 1;
         if (customerId != null) {
            hash = hash * 31 + customerId.hashCode();
         }
         if (firstName != null) {
            hash = hash * 31 + firstName.hashCode();
         }
         if (lastName != null) {
            hash = hash * 31 + lastName.hashCode();
         }
	     
         return hash;
		 
	 }
	 
	 @Override
     public boolean equals(Object obj) {
		 
		 if ((obj == null) || (obj.getClass() != this.getClass())){
			 return false;
		 }
	            
		 Customer customer = (Customer) obj;
		 return this.hashCode() == customer.hashCode();
		 
	 }
	
	
	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}
	/**
	 * @param addressLine1 the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	/**
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return addressLine2;
	}
	/**
	 * @param addressLine2 the addressLine2 to set
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}
	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {		
		this.category = category;
	}
	
	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}


	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

}
