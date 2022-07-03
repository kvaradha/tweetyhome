package com.learning.tweety.tweetyhome.securityconfig.activemq;

import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.learning.tweety.tweetyhome.credentials.Credentials;
import com.learning.tweety.tweetyhome.securityconfig.ApplicationProperties;
import com.learning.tweety.tweetyhome.services.common.RestClientService;

/**
 * Reference of the below implementation took from the
 * official spring documentation.
 * https://spring.io/guides/gs/messaging-jms/
 * [Virtual Topic]
 * https://tuhrig.de/virtual-topics-in-activemq/
 * https://activemq.apache.org/virtual-destinations.html
 */
@Configuration
public class ActiveMQConfig {
	
	@Autowired
	ApplicationProperties property;
	
	@Autowired
	RestClientService clientService;

	@Bean
	public ActiveMQConnectionFactory connectionFactory(){
		Credentials creds = clientService.getCredentials(property.getActiveMqServer());
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(creds.getServerUrl());
		connectionFactory.setPassword(creds.getPassWord());
		connectionFactory.setUserName(creds.getUserName());
		connectionFactory.setRedeliveryPolicy(redeliveryPolicy());
		return connectionFactory;
	}

	@Bean
	public JmsTemplate jmsTemplate(){
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory());
	    template.setMessageConverter(jacksonJmsMessageConverter());
	    //This represent JMS topic.
	    //By Default JMS will create queue.
	    template.setPubSubDomain(true);
		return template;
	}

	@Bean 
	// Serialize message content to JSON using TextMessage
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory());
		factory.setConcurrency("1-1");
		// [Subscription durable] 
		// In case of message to be preserved in activeMQ if subscriber is not listening or down. [Start]
		// commenting out subscription durable configurations. 
		// Since Virtual topic is more advanced then Subscription durable.
		// Particularly Virtual Topic is opt for Micro-services.
		// For virtual topic we need configuration in activemq level(activemq.xml).
		// Please check out below documentations.
		// https://tuhrig.de/virtual-topics-in-activemq/
		// https://activemq.apache.org/virtual-destinations.html
		//factory.setSubscriptionDurable(true);
		//factory.setClientId(ActiveMQConstansts.TWEETY_MESSAGES_CLIENT_ID);
		//[End]
		// In case of exception in listener, message will not be pop out from queue. [Start]
	    factory.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
	    factory.setSessionTransacted(true);
	    // [End]
		return factory;
	}
	
	private RedeliveryPolicy redeliveryPolicy() {
		RedeliveryPolicy policy = new RedeliveryPolicy();
		policy.setInitialRedeliveryDelay(1000);
		policy.setBackOffMultiplier(2);
		policy.setUseExponentialBackOff(true);
		policy.setMaximumRedeliveries(10);
		return policy;
	}
}
