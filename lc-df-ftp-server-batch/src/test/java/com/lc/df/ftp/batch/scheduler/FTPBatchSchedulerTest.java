package com.lc.df.ftp.batch.scheduler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = FTPBatchScheduler.class)
public class FTPBatchSchedulerTest {


	private static final long WAIT_FOR_SCHEDULING = 10000;
	@Mock
    JobLauncher jobLauncher;
	@Mock
    Job processJob;
	@InjectMocks
	FTPBatchScheduler scheduler;
	@Test
	public void testRunJob() throws Exception {
		Thread.sleep(WAIT_FOR_SCHEDULING);
		JobExecution execution = Mockito.mock(JobExecution.class);
		Mockito.when(jobLauncher.run(Mockito.anyObject(), Mockito.anyObject())).thenReturn(execution );
		scheduler.runJob();
	
	}

}
