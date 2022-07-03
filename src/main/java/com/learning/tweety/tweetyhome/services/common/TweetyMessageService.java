package com.learning.tweety.tweetyhome.services.common;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.learning.tweety.tweetyhome.repository.Favourite;
import com.learning.tweety.tweetyhome.repository.Retweet;
import com.learning.tweety.tweetyhome.repository.TweetyMessages;
import com.learning.tweety.tweetyhome.repository.TweetyMessagesRepository;
import com.learning.tweety.tweetyhome.securityconfig.activemq.ActiveMQConstants;
import com.learning.tweety.tweetyhome.services.jms.MessagePublisher;

@Service
public class TweetyMessageService {

	@Autowired
	MessagePublisher<TweetyMessages> publisher;
	
	@Autowired
	TweetyMessagesRepository tweetyMessagesRepo;
	
	@Autowired
	FavouriteService favouriteService;
	
	@Autowired
	RetweetService retweetService;
	
	/**
	 * create message, and store in the DB.
	 * if DB save is successful. Publish it to the activeMQ topic.
	 * @param message
	 */
	public TweetyMessages createTweetyMessage(TweetyMessages message) {
		Date currentDate = new Date();
		message.setTweetyCreationDate(new java.sql.Date(currentDate.getTime()));
		TweetyMessages savedMessage = tweetyMessagesRepo.save(message);
		String []tweetMessageArray =  message.getTweetyMessage().split(" ");
		identifyKeyWordAndBroadCast(message, tweetMessageArray);
		publisher.sendMessage(ActiveMQConstants.TWEETY_MESSAGES_TOPIC, message);
		return savedMessage;
	}
	
	/**
	 * Delete message based on id.
	 * @param tweetyID
	 */
	public void deleteTweetyMessage(Long tweetyID, String userName) {
		TweetyMessages message = tweetyMessagesRepo.findById(tweetyID).get();
		tweetyMessagesRepo.deleteTweet(tweetyID, userName);
		favouriteService.deleteFavouriteById(tweetyID);
		retweetService.deleteRetweetById(tweetyID);
		publisher.sendMessage(ActiveMQConstants.DELETE_TWEETY_MESSAGES_TOPIC, message);
	}
	
	public Iterable<TweetyMessages> getTweetyMessage(String userName) {
		return tweetyMessagesRepo.findOnlyUserCreatedMessages(userName);
	}
	
	public List<TweetyMessages> getProfileTweetyMessage(String userName, int pageIndex, int noOfRows) {
		Pageable pagination = PageRequest.of(pageIndex, noOfRows);
		return tweetyMessagesRepo.findAllUserRelatedMessages(userName, pagination);
	}
	
	/*
	 * Broadcast each mentions as a separate messages.
	 */
	private void identifyKeyWordAndBroadCast(TweetyMessages message, 
			String []tweetMessageArray) {
		for(String tweetMessage : tweetMessageArray) {
			if(tweetMessage.contains("#")) {
				message.setTweetyKeyword(tweetMessage);
				publisher.sendMessage(ActiveMQConstants.TWEETY_SEARCH_TOPIC, message);
			}
		}
	}
	
	public void updateRetweetCount(Retweet retweet) {
		Long tweetyID = retweet.getTwitterMessageId();
		TweetyMessages message = tweetyMessagesRepo.findById(tweetyID).get();
		message.setRetweetCount(message.getRetweetCount() + 1);
		tweetyMessagesRepo.save(message);
		retweetService.broadcastRetweetCount(retweet);
	}
	
	public void removeRetweetCount(Retweet retweet) {
		Long tweetyID = retweet.getTwitterMessageId();
		TweetyMessages message = tweetyMessagesRepo.findById(tweetyID).get();
		message.setRetweetCount(message.getRetweetCount() - 1);
		tweetyMessagesRepo.save(message);
		retweetService.broadcastDeleteRetweetCount(retweet);
	}
	
	public void updateFavouriteCount(Favourite favourite) {
		Long tweetyID = favourite.getTwitterMessageId();
		TweetyMessages message = tweetyMessagesRepo.findById(tweetyID).get();
		message.setFavouriteCount(message.getFavouriteCount() + 1);
		tweetyMessagesRepo.save(message);
		favouriteService.broadcastFavouriteCount(favourite);
	}
	
	public void removeFavouriteCount(Favourite favourite) {
		Long tweetyID = favourite.getTwitterMessageId();
		TweetyMessages message = tweetyMessagesRepo.findById(tweetyID).get();
		message.setFavouriteCount(message.getFavouriteCount() - 1);
		tweetyMessagesRepo.save(message);
		favouriteService.broadcastDeleteFavouriteCount(favourite);
	}
}


