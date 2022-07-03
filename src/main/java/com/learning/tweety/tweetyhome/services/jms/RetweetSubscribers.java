package com.learning.tweety.tweetyhome.services.jms;

import javax.jms.JMSException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import com.learning.tweety.tweetyhome.repository.Retweet;
import com.learning.tweety.tweetyhome.securityconfig.activemq.ActiveMQConstants;
import com.learning.tweety.tweetyhome.services.common.GsonGenerator;
import com.learning.tweety.tweetyhome.services.common.TweetyMessageService;

@Component
public class RetweetSubscribers {

	@Autowired
	TweetyMessageService tweetyMessageService;
	
	@Autowired
	GsonGenerator gsonGenerator;

	/**
	 * Container factory name defined in @see ActiveMQConfig
	 * 
	 * @param messages
	 * @throws JMSException
	 */
	@JmsListener(destination = ActiveMQConstants.CONS_RETWEET_TOPIC, containerFactory = ActiveMQConstants.CONTAINER_FACTORY)
	public void retweetMessage(String messages) throws JMSException {
		Retweet retweet = gsonGenerator.buildGson().fromJson(messages, Retweet.class);
		tweetyMessageService.updateRetweetCount(retweet);
	}

	@JmsListener(destination = ActiveMQConstants.CONS_DELETE_RETWEET_TOPIC, containerFactory = ActiveMQConstants.CONTAINER_FACTORY)
	public void deleteRetweetMessage(String messages) throws JMSException {
		Retweet retweet = gsonGenerator.buildGson().fromJson(messages, Retweet.class);
		tweetyMessageService.removeRetweetCount(retweet);
	}
}
