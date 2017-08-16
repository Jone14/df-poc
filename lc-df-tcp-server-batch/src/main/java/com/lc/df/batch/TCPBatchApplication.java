package com.lc.df.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.ip.tcp.TcpReceivingChannelAdapter;
import org.springframework.integration.ip.tcp.TcpSendingMessageHandler;
import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNetServerConnectionFactory;
import org.springframework.integration.transformer.ObjectToStringTransformer;
import org.springframework.messaging.MessageChannel;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import java.net.Socket;
import javax.net.SocketFactory;

@SpringBootApplication
@EnableBatchProcessing
public class TCPBatchApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(TCPBatchApplication.class, args);	
	}	
}
