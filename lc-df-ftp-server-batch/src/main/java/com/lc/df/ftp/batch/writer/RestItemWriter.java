package com.lc.df.ftp.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.lc.df.ftp.batch.domain.Customer;

@Component
public class RestItemWriter implements ItemWriter<Customer> {
	
	RestTemplate template= new RestTemplate();
	
	@Value("${rest.url}")
	private String url;	
	
	@Override
	public void write(List<? extends Customer> items) throws Exception {
	items.forEach(item->{
			System.out.println("Writing data after parsing..."+template.postForObject(url, item, String.class));
			});
	}

	

}
