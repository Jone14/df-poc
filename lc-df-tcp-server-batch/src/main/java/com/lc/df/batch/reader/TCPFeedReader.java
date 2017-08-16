/**
 * 
 */
package com.lc.df.batch.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;


/**
 * @author mallrao
 *
 */
public class TCPFeedReader implements ItemReader<String> {
	
	private String[] messages = { "James|Butt|6649 N Blue Gum St|New Orleans|500085,abc@abc.com|810-374-9840|active|GOLD",
			"Lenna|Paprocki|639 Main St|Anchorage|99501|lpaprocki@hotmail.com|907-921-2010|active|GOLD",
			"Donette|Foller|34 Center St|Hamilton|45011|donette.foller@cox.net|513-549-4561|active|GOLD",
			"Simona|Morasca|3 Mcauley Dr|Ashland|44805|simona@morasca.com|419-503-2484|active|GOLD",
			"Abel|Maclead|37275 St  Rt 17m M|Middle Island|99501|amaclead@gmail.com|631-677-3675|active|SILVER"};

	private int count = 0;

	@Override
	public String read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {

		if (count < messages.length) {
			return messages[count++];
		} else {
			count = 0;
		}
		return null;
	}		
}
