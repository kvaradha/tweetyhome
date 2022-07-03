package com.learning.tweety.tweetyhome.services.common;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.tweety.tweetyhome.repository.Favourite;
import com.learning.tweety.tweetyhome.repository.FavouriteRepository;
import com.learning.tweety.tweetyhome.repository.TweetyMessagesRepository;
import com.learning.tweety.tweetyhome.securityconfig.activemq.ActiveMQConstants;
import com.learning.tweety.tweetyhome.services.jms.MessagePublisher;

@Service
public class FavouriteService {

	@Autowired
	FavouriteRepository favouriteRepository;
	
	@Autowired
	MessagePublisher<Favourite> publisher;
	
	@Autowired
	TweetyMessagesRepository tweetyMessagesRepo;
	
	public Favourite updateFavourite(Favourite favourite) {
		Long tweetyID = favouriteRepository.findByTweetID(favourite.getTwitterMessageId(),
				favourite.getUserName());
		if (tweetyID > 0) {
			throw new IllegalArgumentException("Already favourite.");
		}
		Date currentDate = new Date();
		favourite.setCreationDate(new java.sql.Date(currentDate.getTime()));
		Favourite saveFavourite = favouriteRepository.save(favourite);
		publisher.sendMessage(ActiveMQConstants.FAVOURITE_TOPIC, favourite);
		return saveFavourite;
	}
	
	public void deleteFavourite(Long tweetyID, String userName) {
		Favourite favourite = favouriteRepository.findFavourite(tweetyID,userName);
		favouriteRepository.deleteFavourite(tweetyID, userName);
		publisher.sendMessage(ActiveMQConstants.DELETE_FAVOURITE_TOPIC, favourite);
	}
	
	public void deleteFavouriteById(Long tweetyID) {
		favouriteRepository.deleteByTweetId(tweetyID);
	}
	
	public void broadcastFavouriteCount(Favourite favourite) {
		publisher.sendMessage(ActiveMQConstants.FAVOURITE_COUNT_TOPIC, favourite);
	}
	
	public void broadcastDeleteFavouriteCount(Favourite favourite) {
		publisher.sendMessage(ActiveMQConstants.DEL_FAVOURITE_COUNT_TOPIC, favourite);
	}
}
