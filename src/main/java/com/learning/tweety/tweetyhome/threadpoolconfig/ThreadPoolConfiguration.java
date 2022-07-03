package com.learning.tweety.tweetyhome.threadpoolconfig;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.learning.tweety.tweetyhome.securityconfig.ApplicationProperties;

@Configuration
public class ThreadPoolConfiguration {
	
	@Autowired
	ApplicationProperties property;
	
	@Bean(name = "threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor task = new ThreadPoolTaskExecutor();
		task.setCorePoolSize(Integer.parseInt(property.getCorePoolSize()));
		task.setMaxPoolSize(Integer.parseInt(property.getMaxPoolSize()));
        return task;
    }
}
