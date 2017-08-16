package com.lc.df.batch;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowJobBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.JobFlowBuilder;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.boot.test.context.SpringBootTest;

import com.lc.df.batch.controller.TCPServiceInvokeController;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = TCPServiceInvokeController.class)
public class BatchConfigTest {
	
	@Mock
	public JobBuilderFactory jobBuilderFactory;

	@Mock
	public StepBuilderFactory stepBuilderFactory;
	
	@InjectMocks
	BatchConfig config;
	@Mock
	private JobBuilder jobBuilder;
	@Mock
	private JobFlowBuilder jobFlowBuilder;
	@Mock
	private StepBuilder stepBuilder;
	@Mock
	private SimpleStepBuilder<Object, Object> simpleStepBuilder;
	@Mock
	private FlowJobBuilder flowBuilder;
	@Mock
	private Job job;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testProcessJob() {
		Mockito.when(jobBuilderFactory.get(Mockito.anyString())).thenReturn(jobBuilder);
		Mockito.when(jobBuilder.incrementer(Mockito.anyObject())).thenReturn(jobBuilder);
		Mockito.when(jobBuilder.listener(Mockito.anyObject())).thenReturn(jobBuilder);
		Mockito.when(jobBuilder.flow(Mockito.anyObject())).thenReturn(jobFlowBuilder);
		Mockito.when(stepBuilderFactory.get(Mockito.anyString())).thenReturn(stepBuilder);
		Mockito.when(stepBuilder.chunk(Mockito.anyInt())).thenReturn(simpleStepBuilder);
		Mockito.when(simpleStepBuilder.reader(Mockito.anyObject())).thenReturn(simpleStepBuilder);
		Mockito.when(simpleStepBuilder.processor(Mockito.anyObject())).thenReturn(simpleStepBuilder);
		Mockito.when(simpleStepBuilder.writer(Mockito.anyObject())).thenReturn(simpleStepBuilder);
		Mockito.when(jobFlowBuilder.end()).thenReturn(flowBuilder);
		Mockito.when(flowBuilder.build()).thenReturn(job);
		Assert.assertEquals(job,config.processJob());
	}
}
