/**
 * 
 */
package com.lc.df.batch.writer;

import java.util.List;
import org.springframework.batch.item.ItemWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

/**
 * @author mallrao
 *
 */
public class TCPFeedWriter implements ItemWriter<String> {
	
	@Override
	public void write(List<? extends String> messages) throws Exception {
		
		Socket socket = null;
        Writer out = null;
        BufferedReader in = null;
        String host = "localhost";
        int port = 5565;
        socket = new Socket(host, port);
        System.out.println("connected.");
        
		for (String msg : messages) {
			try{
				 System.out.println("sending messages ..."+msg);

	             out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));	             	             
	             out.write(msg+"\r\n");
	             out.flush();
	             Thread.sleep(1000);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
				try{
					 socket.close();
		             out.close();
				}
				catch(Exception e){
					
				}
			}		
		}
	}

}
