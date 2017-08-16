package com.lc.df.customermanagement.service.logging;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class CustomerLoggingHandler {

	 private final Logger slf4jLogger = LoggerFactory.getLogger(this.getClass());
	 
	 private ObjectMapper mapper= new ObjectMapper();
	 
	@AfterReturning(pointcut = "execution(* com.lc.df.customermanagement.web..*(..))", returning = "result")
	public void controllerLogging(JoinPoint joint,Object result) throws Exception{
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		try{
			String values = getLoggerMessage(joint);
			slf4jLogger.info("Info Method Name :::"+joint.toString());
			slf4jLogger.info("Info Input Parameters :::"+values);
			slf4jLogger.info("Info Output Parameters ::: "+mapper.writeValueAsString(null!=result?result:""));
		}catch(JsonProcessingException e){
			throw e;
		}
	}

	@AfterReturning(pointcut = "execution(* com.lc.df.customermanagement.service..*(..))", returning = "result")
	public void setterMethodLogging(JoinPoint joint,Object result)  throws Exception{
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		try{
		String values = getLoggerMessage(joint);
		slf4jLogger.debug("Debug Method Name :::"+joint.toString());
		slf4jLogger.debug("Debug input parameters :::"+values);
		slf4jLogger.debug("Debug Output Parameters :::"+mapper.writeValueAsString(null!=result?result:""));
		}catch(JsonProcessingException e){
			throw e;
		}
	}
	
	private String getLoggerMessage(JoinPoint joint) throws JsonProcessingException {
		String args=":";
		String values="";
			for(Object arg:joint.getArgs()){
				values=arg instanceof String?args+=arg+" ":mapper.writeValueAsString(arg);
			}
		return values;
	}
	
}
