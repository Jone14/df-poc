/**
 * 
 */
package com.lc.df.batch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mallrao
 *
 */
@RestController
@Component
@EnableScheduling
public class TCPServiceInvokeController {
	
	@Autowired
    JobLauncher jobLauncher;
 
    @Autowired
    Job processJob;
 
    /*@RequestMapping("/invoke")  
    public String handle() throws Exception {    		
            JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(processJob, jobParameters);
 
        return "Batch job has been invoked";
    }*/
    
    @Scheduled(cron = "*/10 * * * * *")
    public void runJob() throws Exception {
    	JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(processJob, jobParameters);
    }

}
