package com.lc.df.tcp.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.net.Socket;
import javax.net.SocketFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.ip.tcp.TcpReceivingChannelAdapter;
import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNetServerConnectionFactory;
import org.springframework.integration.transformer.ObjectToStringTransformer;
import org.springframework.messaging.MessageChannel;

@SpringBootApplication
public class TcpServerJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TcpServerJavaApplication.class, args);
//		ConfigurableApplicationContext context = SpringApplication.run(TcpServerJavaApplication.class, args);
	}
	
	@Bean
    public TcpNetServerConnectionFactory cf() {
        return new TcpNetServerConnectionFactory(5565);
    }

    @Bean
    public TcpReceivingChannelAdapter inbound(AbstractServerConnectionFactory cf) {
        TcpReceivingChannelAdapter adapter = new TcpReceivingChannelAdapter();
        adapter.setConnectionFactory(cf);
        adapter.setOutputChannel(tcpIn());
        return adapter;
    }

    @Bean
    public MessageChannel tcpIn() {
        return new DirectChannel();
    }

    @Transformer(inputChannel = "tcpIn", outputChannel = "serviceChannel")
    @Bean
    public ObjectToStringTransformer transformer() {
        return new ObjectToStringTransformer();
    }

    @ServiceActivator(inputChannel = "serviceChannel")
    public void service(String in) {    	
        System.out.println("Message recieved..."+in);    	 
    }
}
