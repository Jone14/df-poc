package com.lc.df.ftp.batch.domain;

import java.util.UUID;


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
	
	private String customerId = UUID.randomUUID().toString();
	
	private String firstName;
	
	private String lastName;
	
	private String addressLine1;
	
	private String addressLine2;
	
	private String postalCode;
	
	
	private String emailId;
		
	
	private String phoneNumber;
	
	
	private String status;
	
	
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
	 
	 @Override
	public String toString() {
		 	return "customerId "+ customerId+
		 			"firstName "+ firstName+
		 			"lastName "+ lastName+
		 			"addressLine1 "+ addressLine1+
		 			"addressLine2 "+ addressLine2+
		 			"postalCode "+ postalCode+
		 			"emailId "+ emailId+
		 			"phoneNumber "+ phoneNumber+
		 			"status "+ status+
		 			"category "+ category;
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
