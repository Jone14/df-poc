package com.lc.df.customermanagement.service.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwagerConfig {
	
	@Value("${customer.title}")
	private String title;
	@Value("${customer.desc}")
	private String description;
	@Value("${customer.version}")
	private String version;

	@Bean
	public Docket docker() {
		Docket docker = new Docket(DocumentationType.SWAGGER_2).apiInfo(getApiInfo()).select().apis(RequestHandlerSelectors.any())
				.paths(Predicates.not(PathSelectors.regex("/error*"))).build();
		return docker;
	}
	
	@Bean
	public ApiInfo getApiInfo() {
		ApiInfoBuilder builder= new ApiInfoBuilder();
		builder.title(title);
		builder.description(description);
		builder.version(version);
		return builder.build();
	}


}
