package com.lc.df.file.batch.config;

import java.io.File;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.filters.AcceptOnceFileListFilter;
import org.springframework.integration.sftp.filters.SftpSimplePatternFileListFilter;
import org.springframework.integration.sftp.inbound.SftpInboundFileSynchronizer;
import org.springframework.integration.sftp.inbound.SftpInboundFileSynchronizingMessageSource;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.messaging.MessageHandler;

import com.lc.df.file.batch.tasklet.LatestMessageHandler;

@Configuration
public class FTPConfiguration {
	
	   @Value("${ftp.server.user.name}")
		private String user;
	    @Value("${ftp.server.user.pwd}")
		private String pwd;
	    @Value("${ftp.server.host.name}")
		private String host;
	    @Value("${ftp.server.host.port:22}")
		private String port;
	
	@Bean
	@Qualifier("sftpSessionFactory")
	public DefaultSftpSessionFactory sftpSessionFactory(){
		DefaultSftpSessionFactory defaultSftpSessionFactory= new DefaultSftpSessionFactory();
		defaultSftpSessionFactory.setUser(user);
		defaultSftpSessionFactory.setPassword(pwd);
		defaultSftpSessionFactory.setHost(host);
		defaultSftpSessionFactory.setPort(Integer.parseInt(port));
		Properties config= new Properties();
		 config.put("PreferredAuthentications", "password");
		 config.put("StrictHostKeyChecking", "no");
		 config.put("PubkeyAuthentication", "no");
		defaultSftpSessionFactory.setSessionConfig(config);
		return defaultSftpSessionFactory;
	}
	@Bean
	public SftpInboundFileSynchronizer ftpInboundFileSynchronizer() {
		SftpInboundFileSynchronizer fileSynchronizer = new SftpInboundFileSynchronizer(sftpSessionFactory());
		fileSynchronizer.setDeleteRemoteFiles(false);
		fileSynchronizer.setRemoteDirectory("/home/pbarega/ftp_file");
		fileSynchronizer.setFilter(new SftpSimplePatternFileListFilter("*.csv"));
		return fileSynchronizer;
	}

	@Bean
	@InboundChannelAdapter(channel = "ftpChannel", poller = @Poller(fixedDelay = "5000"))
	public MessageSource<File> ftpMessageSource() {
		
		
		
		SftpInboundFileSynchronizingMessageSource source = new SftpInboundFileSynchronizingMessageSource(ftpInboundFileSynchronizer());
		source.setLocalDirectory(new File("d:/ftp_file"));
		source.setAutoCreateLocalDirectory(true);
		source.setLocalFilter(new AcceptOnceFileListFilter<File>());
		return source;
	}

	@Bean
	@ServiceActivator(inputChannel = "ftpChannel")
	public MessageHandler handler() {
		return new LatestMessageHandler();
	}

}
