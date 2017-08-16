package com.lc.df.jms.tibco;

import javax.jms.JMSException;
import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.tibco.tibjms.TibjmsConnectionFactory;
import com.tibco.tibjms.TibjmsQueue;

@Component
public class TibcoComponent {

	@Value("${customer.queue:sample.queue}")
	private String queueName;

	private Queue queue;

	@Bean
	TibjmsConnectionFactory tibcoJmsFactory() {
		TibjmsConnectionFactory factory = new TibjmsConnectionFactory();
		try {
			factory.setServerUrl("tcp://ldgrvtibemsu001.ladsys.net:7770");
			factory.setUserName("admin");
			factory.setUserPassword("admin");
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return factory;
	}

	@Bean
	public Queue queue() {
		return queue = new TibjmsQueue(queueName);
	}
}
