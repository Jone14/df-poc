/**
 * 
 */
package com.lc.df.ftp.batch.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;

import com.lc.df.ftp.batch.domain.Customer;

/**
 * @author mallrao
 *
 */
@Scope(scopeName="prototype")
public class FTPBatchReader extends MultiResourceItemReader<Customer> {

	private Resource[] resources;
	
	public FTPBatchReader(){		
	}
	
	public FTPBatchReader(Resource[] resources) {
		super();	
		setResources(resources);		
		setDelegate(reader());
		this.resources=resources;
	}

	@Bean
	public FlatFileItemReader<Customer> reader() {
		FlatFileItemReader<Customer> reader = new FlatFileItemReader<Customer>();
		reader.setLineMapper(new DefaultLineMapper<Customer>() {{
				setLineTokenizer(new DelimitedLineTokenizer() {{
						setDelimiter("|");
						setNames(new String[] { "firstName", "lastName", "addressLine1", "addressLine2", "postalCode","emaiId", "phoneNumber", "status", "category" });
					}});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<Customer>() {{
						setTargetType(Customer.class);
					}});
			}});

		return reader;
	}

}
