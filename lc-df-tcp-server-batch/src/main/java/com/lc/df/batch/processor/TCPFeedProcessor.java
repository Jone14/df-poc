/**
 * 
 */
package com.lc.df.batch.processor;

import org.springframework.batch.item.ItemProcessor;

/**
 * @author mallrao
 *
 */
public class TCPFeedProcessor implements ItemProcessor<String, String> {
	
	@Override
	public String process(String data) throws Exception {
		return data.toUpperCase();
	}

}
