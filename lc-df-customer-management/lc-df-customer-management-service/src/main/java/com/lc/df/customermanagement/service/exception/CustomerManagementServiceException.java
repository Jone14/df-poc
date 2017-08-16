/**
 * 
 */
package com.lc.df.customermanagement.service.exception;

/**
 * @author mallrao
 *
 */
public class CustomerManagementServiceException extends Exception {
	
	private static final long serialVersionUID = -1315519600481257190L;
	
		private int code;
		private String message;
		
		/**
		 * @return the code
		 */
		public int getCode() {
			return code;
		}

		/**
		 * @param code the code to set
		 */
		public void setCode(int code) {
			this.code = code;
		}

		/**
		 * @return the message
		 */
		public String getMessage() {
			return message;
		}

		/**
		 * @param message the message to set
		 */
		public void setMessage(String message) {
			this.message = message;
		}

		
		
		public CustomerManagementServiceException() {
	       super();
	    }
		
		public CustomerManagementServiceException(int code, String message) {
			super();
			setCode(code);
			setMessage(message);		       
		}


	    public CustomerManagementServiceException(int code, String details, Throwable cause) {
	    	super();
	        setCode(code);
			setMessage(message);
	    }

}
