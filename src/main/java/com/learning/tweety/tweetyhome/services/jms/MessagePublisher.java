package com.learning.tweety.tweetyhome.services.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessagePublisher<T> {
	@Autowired
	JmsTemplate jmsTemplate;

	/**
	 * topicName represents topic in activemq. <BR>
	 * message represents message to be sent to activemq.
	 * @param topicName
	 * @param message
	 */
	public void sendMessage(String topicName, T message) {
		jmsTemplate.convertAndSend(topicName, message);
	}
}
