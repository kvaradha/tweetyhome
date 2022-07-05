package com.learning.tweety.tweetyhome.services.common;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.learning.tweety.tweetyhome.repository.Retweet;
import com.learning.tweety.tweetyhome.repository.RetweetRepository;
import com.learning.tweety.tweetyhome.repository.TweetyMessagesRepository;
import com.learning.tweety.tweetyhome.securityconfig.activemq.ActiveMQConstants;
import com.learning.tweety.tweetyhome.services.jms.MessagePublisher;

@Service
public class RetweetService {
	@Autowired
	RetweetRepository retweetRepository;
	
	@Autowired
	MessagePublisher<Retweet> publisher;
	
	@Autowired
	TweetyMessagesRepository tweetyMessagesRepo;
	
	public Retweet updateRetweet(Retweet retweet) {
		Long tweetyID = retweetRepository.findByTweetID(retweet.getTwitterMessageId(),
				retweet.getUserName());
		if (tweetyID > 0) {
			throw new IllegalArgumentException("Already retweeted.");
		}
		Date currentDate = new Date();
		retweet.setCreationDate(new java.sql.Date(currentDate.getTime()));
		Retweet saveRetweet = retweetRepository.save(retweet);
		publisher.sendMessage(ActiveMQConstants.RETWEET_TOPIC, retweet);
		return saveRetweet;
	}
	
	public void deleteRetweetById(Long tweetyID) {
		retweetRepository.deleteByTweetId(tweetyID);
	}
	
	public void deleteRetweet(Long tweetyID, String userName) {
		Retweet retweet = retweetRepository.findRetweet(tweetyID, userName);
		if(retweet == null) {
			throw new RuntimeException("Deletion failed. Invalid tweet id.");
		}
		retweetRepository.deleteRetweet(tweetyID, userName);
		publisher.sendMessage(ActiveMQConstants.DELETE_RETWEET_TOPIC, retweet);
	}
	
	public void broadcastRetweetCount(Retweet retweet) {
		publisher.sendMessage(ActiveMQConstants.RETWEET_COUNT_TOPIC, retweet);
	}
	
	public void broadcastDeleteRetweetCount(Retweet retweet) {
		publisher.sendMessage(ActiveMQConstants.DEL_RETWEET_COUNT_TOPIC, retweet);
	}
}
