package com.lc.df.ftp.batch.reader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.lc.df.ftp.batch.domain.Customer;
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = FTPBatchReader.class)
public class FTPBatchReaderTest {
	
	FTPBatchReader ftpBatchReader;
	Resource [] resources=new Resource[1];
	@Before
	public void setUp(){
		Resource resource=new ClassPathResource("customer.txt");
		resources[0]=resource;
		ftpBatchReader =new FTPBatchReader(resources);
	}
	
	@Test
	public void testReader() throws UnexpectedInputException, ParseException, Exception {
		Customer customer = ftpBatchReader.read();
		Assert.assertEquals("Raj",customer.getFirstName());
		Assert.assertEquals("Kumar",customer.getLastName());
		Assert.assertEquals("Madhapur",customer.getAddressLine1());
		Assert.assertEquals("Hitec City",customer.getAddressLine2());
		Assert.assertEquals("500089",customer.getPostalCode());
		Assert.assertEquals("raj.kumar@gmail.com",customer.getEmailId());
		Assert.assertEquals("7778889990",customer.getPhoneNumber());
		Assert.assertEquals("Active",customer.getStatus());
		Assert.assertEquals("SILVER",customer.getCategory());
	}

}
