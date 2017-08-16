package com.lc.df.ftp.batch.config;


import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.lc.df.ftp.batch.domain.Customer;
import com.lc.df.ftp.batch.listener.JobCompletionNotificationListener;
import com.lc.df.ftp.batch.reader.FTPBatchReader;
import com.lc.df.ftp.batch.writer.RestItemWriter;


@EnableBatchProcessing
@Component
public class FTPBatchConfiguration {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;	 	

    @Autowired
    public DataSource dataSource;
    
    @Autowired
    public RestItemWriter restItemWriter;
    
	@Value("${ftp.resource.path}")
	private Resource[] resources;
 
    @Bean       
    public Job processCustomerJob() {
        return jobBuilderFactory.get("processCustomerJob")
                .incrementer(new RunIdIncrementer())
                .listener(new JobCompletionNotificationListener())
                .flow(step1())
                .end()
                .build();
    }
    
    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Customer, Customer> chunk(10)
                .reader(new FTPBatchReader(resources))
                .writer(restItemWriter)
                .build();
    }
    
    public void setResources(Resource []resources){
    	this.resources=resources;
    }
}