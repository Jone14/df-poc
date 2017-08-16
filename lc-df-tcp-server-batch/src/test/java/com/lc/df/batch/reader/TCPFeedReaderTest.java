package com.lc.df.batch.reader;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = TCPFeedReader.class)
public class TCPFeedReaderTest {

	private String[] messages = { "James|Butt|6649 N Blue Gum St|New Orleans|500085,abc@abc.com|810-374-9840|active|GOLD",
			"Lenna|Paprocki|639 Main St|Anchorage|99501|lpaprocki@hotmail.com|907-921-2010|active|GOLD",
			"Donette|Foller|34 Center St|Hamilton|45011|donette.foller@cox.net|513-549-4561|active|GOLD",
			"Simona|Morasca|3 Mcauley Dr|Ashland|44805|simona@morasca.com|419-503-2484|active|GOLD",
			"Abel|Maclead|37275 St  Rt 17m M|Middle Island|99501|amaclead@gmail.com|631-677-3675|active|SILVER"};
	
	TCPFeedReader reader= new TCPFeedReader();
	
	@Test
	public void testRead() throws Exception {
		Assert.assertEquals(messages[0],reader.read());
		Assert.assertEquals(messages[1],reader.read());
		Assert.assertEquals(messages[2],reader.read());
		Assert.assertEquals(messages[3],reader.read());
	}

}
