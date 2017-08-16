/**
 * 
 */
package com.lc.df.customermanagement.service.kafka;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @author 
 *
 */
@Component
public class KafkaComponent {
	
	@Autowired
	KafkaTemplate<String,String> kafkaTemplate;
	
	@Value("${kafka.topic.sender}")
	private String kafkaTopic;

	public void sendMessages(String topic,String message){
		this.kafkaTopic=null!=topic?topic:this.kafkaTopic;
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(this.kafkaTopic, message);

	    // register a callback with the listener to receive the result of the send asynchronously
	    future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

	      @Override
	      public void onSuccess(SendResult<String, String> result) {
	            result.getRecordMetadata().offset();
	      }

	      @Override
	      public void onFailure(Throwable ex) {
	      }
	    });
		
	}
}
