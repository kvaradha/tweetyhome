package com.learning.tweety.tweetyhome.initializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.learning.tweety.tweetyhome.securityconfig.ApplicationProperties;

@SpringBootApplication
@EnableJpaRepositories("com.learning.tweety")
@ComponentScan("com.learning.tweety")
@EntityScan("com.learning.tweety")
@EnableConfigurationProperties(ApplicationProperties.class)
@EnableJms
@EnableTransactionManagement
public class TweetyhomeApplication {
	public static void main(String[] args) {
		SpringApplication.run(TweetyhomeApplication.class, args);
	}
}
