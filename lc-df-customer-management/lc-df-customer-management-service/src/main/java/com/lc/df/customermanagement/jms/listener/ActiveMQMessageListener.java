/**
 * 
 */
package com.lc.df.customermanagement.jms.listener;

import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;
import org.springframework.jms.annotation.JmsListener;

/**
 * @author mallrao
 *
 */
@EnableJms
@Component
public class ActiveMQMessageListener {
	
	@JmsListener(destination = "${customer.queue}")
	public void receiveQueue(String text) {
		System.out.println("MessageRecieved from Active MQ  :: "+text);
	}

}
