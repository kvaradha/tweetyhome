package com.learning.tweety.tweetyhome.services.common;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.tweety.tweetyhome.repository.Following;
import com.learning.tweety.tweetyhome.repository.FollowingAck;
import com.learning.tweety.tweetyhome.repository.FollowingAckRepository;

@Service
public class FollowingAckService {
	
	@Autowired
	GsonGenerator gsonGenerator;
	
	@Autowired
	FollowingAckRepository followingAckRepo;
	
	public FollowingAck insertPalyload(Following following, String messageType) {
		FollowingAck followingAck = new FollowingAck();
		Date currentDate = new Date();
		followingAck.setCreationDate(new java.sql.Date(currentDate.getTime()));
		followingAck.setAckStatus("NotReceived");
		followingAck.setPayload(gsonGenerator.buildGson().toJson(following));
		followingAck.setMessage_type(messageType);
		followingAck.setFollowing_id(following.getFollowingId());
		followingAckRepo.save(followingAck);
		return null;
	}
	
	public void updateAcknowledgement(Long followingID, String messageType) {
		FollowingAck saveFollowingAck = followingAckRepo.findPayloadAck(followingID, messageType);
		saveFollowingAck.setAckStatus("Received");
		followingAckRepo.save(saveFollowingAck);
	}

}
