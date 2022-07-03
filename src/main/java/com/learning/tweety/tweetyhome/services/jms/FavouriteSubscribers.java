package com.learning.tweety.tweetyhome.services.jms;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import com.learning.tweety.tweetyhome.repository.Favourite;
import com.learning.tweety.tweetyhome.securityconfig.activemq.ActiveMQConstants;
import com.learning.tweety.tweetyhome.services.common.GsonGenerator;
import com.learning.tweety.tweetyhome.services.common.TweetyMessageService;

@Component
public class FavouriteSubscribers {

	@Autowired
	TweetyMessageService tweetyMessageService;
	
	@Autowired
	GsonGenerator gsonGenerator;

	/**
	 * Container factory name defined in @see ActiveMQConfig JMSException - In case
	 * of any exception message will not pop. [Configuration for this in @see
	 * ActiveMQConfig]
	 * 
	 * @param messages
	 * @throws JMSException
	 */
	@JmsListener(destination = ActiveMQConstants.CONS_FAVOURITE_TOPIC, containerFactory = ActiveMQConstants.CONTAINER_FACTORY)
	public void favouriteMessage(String messages) throws JMSException {
		Favourite favourite = gsonGenerator.buildGson().fromJson(messages, Favourite.class);
		tweetyMessageService.updateFavouriteCount(favourite);
	}

	@JmsListener(destination = ActiveMQConstants.CONS_DELETE_FAVOURITE_TOPIC, containerFactory = ActiveMQConstants.CONTAINER_FACTORY)
	public void deleteFavouriteMessage(String messages) throws JMSException {
		Favourite favourite = gsonGenerator.buildGson().fromJson(messages, Favourite.class);
		tweetyMessageService.removeFavouriteCount(favourite);
	}
}
