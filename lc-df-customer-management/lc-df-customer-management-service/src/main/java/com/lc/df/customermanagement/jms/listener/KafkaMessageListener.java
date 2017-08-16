/**
 * 
 */
package com.lc.df.customermanagement.jms.listener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author mallrao
 *
 */

@Component
public class KafkaMessageListener {
	
	@Value("${kafka.log.file}")
	private String kafkaLogFile;
	
	@KafkaListener(topics = "${kafka.topic.listener}")
	public String reciever(String message) throws Exception{
		try{
			createOrAppendFile(new File(kafkaLogFile),message);
		}
		catch(IOException e){
			throw e;
		}
		
		return message;
	}
	
	public void  createOrAppendFile(File f, String text) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
		bw.write(text);
		bw.append(',');
		bw.flush();
		bw.close();
	}

}
