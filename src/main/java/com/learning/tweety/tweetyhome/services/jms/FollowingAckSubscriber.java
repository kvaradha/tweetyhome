package com.learning.tweety.tweetyhome.services.jms;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.learning.tweety.tweetyhome.repository.Following;
import com.learning.tweety.tweetyhome.securityconfig.activemq.ActiveMQConstants;
import com.learning.tweety.tweetyhome.services.common.FollowingAckService;
import com.learning.tweety.tweetyhome.services.common.GsonGenerator;

@Component
public class FollowingAckSubscriber {
	
	@Autowired
	GsonGenerator gsonGenerator;
	
	@Autowired
	FollowingAckService followingAckService;
	
	@JmsListener(destination = ActiveMQConstants.CONS_ACK_ADD_FOLLOWING, containerFactory = ActiveMQConstants.CONTAINER_FACTORY)
	public void ackaddfollowing(String messages) throws JMSException {
		Following followingAck = gsonGenerator.buildGson().fromJson(messages, Following.class);
		followingAckService.updateAcknowledgement(followingAck.getFollowingId(), ActiveMQConstants.ADD_FOLLOWING);
	}
	
	@JmsListener(destination = ActiveMQConstants.CONS_ACK_DELETE_FOLLOWING, containerFactory = ActiveMQConstants.CONTAINER_FACTORY)
	public void ackdeletefollowing(String messages) throws JMSException {
		Following followingAck = gsonGenerator.buildGson().fromJson(messages, Following.class);
		followingAckService.updateAcknowledgement(followingAck.getFollowingId(), ActiveMQConstants.DELETE_FOLLOWING);
	}
}
