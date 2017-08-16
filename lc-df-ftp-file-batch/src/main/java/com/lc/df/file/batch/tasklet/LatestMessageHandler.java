package com.lc.df.file.batch.tasklet;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.lc.df.file.batch.domain.Customer;

@Component
@EnableBatchProcessing
public class LatestMessageHandler implements MessageHandler {
	
	  Logger logger=LoggerFactory.getLogger(LatestMessageHandler.class);

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	RestTemplate template = new RestTemplate();

	private String url="http://localhost:8080/customers/create";


	private String path;
	@Override
	public void handleMessage(Message<?> source) throws MessagingException {
		try {
			path=source.getPayload().toString();
			JobParameters jobParameter=new JobParameters();
			jobLauncher.run(importUserJob(), jobParameter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	public Job importUserJob() {
		return jobBuilderFactory.get("importUserJob").incrementer(new RunIdIncrementer()).flow(step1()).end().build();
	}
	
	public Step step1() {
		return stepBuilderFactory.get("step1").allowStartIfComplete(true).<Customer, Customer>chunk(10).reader(reader()).writer(writer()).build();
	}
	
	private ItemWriter<? super Customer> writer() {
		ItemWriter <Customer> writer;
		 writer = new ItemWriter<Customer>() {
			@Override
			public void write(List<? extends Customer> items) throws Exception {
				for(Customer customer:items){
					String result =template.postForObject(url, customer, String.class);
					logger.info(result);
				}
			}
		};
		return writer;
	}
	
	public LineMapper<Customer> lineMapper() {
		DefaultLineMapper<Customer> lineMapper = new DefaultLineMapper<Customer>();

		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter("|");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames(new String[]{"firstName", "lastName", "addressLine1", "addressLine2","postalCode", "emailId", "phoneNumber", "status", "category"});
		BeanWrapperFieldSetMapper<Customer> fieldSetMapper = new BeanWrapperFieldSetMapper<Customer>();
		fieldSetMapper.setTargetType(Customer.class);

		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);

		return lineMapper;
	}
	

	private ItemReader<? extends Customer> reader() {
		FlatFileItemReader<Customer> itemReader= new FlatFileItemReader<>();
		itemReader.setResource(new FileSystemResource(path));
		itemReader.setLineMapper(lineMapper());
		return itemReader;
	}
}
