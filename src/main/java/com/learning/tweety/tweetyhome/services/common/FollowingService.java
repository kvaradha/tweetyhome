package com.learning.tweety.tweetyhome.services.common;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.tweety.tweetyhome.repository.Following;
import com.learning.tweety.tweetyhome.repository.FollowingRepository;
import com.learning.tweety.tweetyhome.securityconfig.activemq.ActiveMQConstants;
import com.learning.tweety.tweetyhome.services.jms.MessagePublisher;

@Service
public class FollowingService {
	@Autowired
	FollowingRepository followingRepository;
	
	@Autowired
	MessagePublisher<Following> publisher;
	
	@Autowired
	FollowingAckService followingAckService;
	
	@Transactional
	public Following addFollowers(Following following) {
		Following fetchFollowing = followingRepository.findFollowing(following.getFollowingName(), 
				following.getUserName());
		if(fetchFollowing != null) {
			throw new RuntimeException("Already following.");
		}
		Date currentDate = new Date();
		following.setCreationDate(new java.sql.Date(currentDate.getTime()));
		Following saveFollowing = followingRepository.save(following);
		followingAckService.insertPalyload(saveFollowing, ActiveMQConstants.ADD_FOLLOWING);
		publisher.sendMessage(ActiveMQConstants.ADD_FOLLOWINGS_TOPIC, saveFollowing);
		return saveFollowing;
	}
	
	@Transactional
	public void deleteFollowers(String followingName, String userName) {
		Following following = followingRepository.findFollowing(followingName, userName);
		if(following == null) {
			throw new RuntimeException("Deletion failed. Invalid follower name.");
		}
		followingRepository.deleteByFollowersName(followingName, userName);
		followingAckService.insertPalyload(following, ActiveMQConstants.DELETE_FOLLOWING);
		publisher.sendMessage(ActiveMQConstants.DELETE_FOLLOWINGS_TOPIC, following);
	}
}
