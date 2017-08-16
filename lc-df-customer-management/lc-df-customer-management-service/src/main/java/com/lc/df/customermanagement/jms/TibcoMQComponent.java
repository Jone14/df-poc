package com.lc.df.customermanagement.jms;

import javax.jms.JMSException;
import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.tibco.tibjms.TibjmsConnectionFactory;
import com.tibco.tibjms.TibjmsQueue;

@Component
public class TibcoMQComponent {
	
	@Value("${customer.queue}")
	private String queueName;
	@Value("${tibco.serverURL}")
	private String serverURL;
	@Value("${tibco.username}")
	private String userName;
	@Value("${tibco.password}")
	private String password;
	private Queue queue;
	
	@Bean
	TibjmsConnectionFactory tibcoJmsFactory() {
		TibjmsConnectionFactory factory = new TibjmsConnectionFactory();
		try {
			factory.setServerUrl(serverURL);
			factory.setUserName(userName);
			factory.setUserPassword(password);
		} catch (JMSException e) {
			System.out.println(e.getMessage());
		}
		return factory;
	}

	@Bean
	public Queue queue() {
		return queue = new TibjmsQueue(queueName);
	}
}
