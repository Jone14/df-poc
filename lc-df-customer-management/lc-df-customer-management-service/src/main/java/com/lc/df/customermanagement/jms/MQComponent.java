package com.lc.df.customermanagement.jms;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@EnableJms
@Component
public class MQComponent {
	@Autowired
	private JmsTemplate jmsMessagingTemplate;
	
	@Autowired
	private Queue queue;
	
	public void send(String msg) {
		System.out.println("Message sent to MQ  :: "+msg);
		this.jmsMessagingTemplate.convertAndSend(this.queue, msg);
	}	
	
}
