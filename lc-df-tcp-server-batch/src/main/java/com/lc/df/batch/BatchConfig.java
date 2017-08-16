package com.lc.df.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lc.df.batch.listener.TCPJobCompletionListener;
import com.lc.df.batch.processor.TCPFeedProcessor;
import com.lc.df.batch.reader.TCPFeedReader;
import com.lc.df.batch.writer.TCPFeedWriter;

@Configuration
public class BatchConfig {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job processJob() {
		return jobBuilderFactory.get("processJob")
				.incrementer(new RunIdIncrementer()).listener(listener())
				.flow(orderStep1()).end().build();
	}

	@Bean
	public Step orderStep1() {
		return stepBuilderFactory.get("orderStep1").<String, String> chunk(1)
				.reader(new TCPFeedReader()).processor(new TCPFeedProcessor())
				.writer(new TCPFeedWriter()).build();
	}

	@Bean
	public JobExecutionListener listener() {
		return new TCPJobCompletionListener();
	}


}
