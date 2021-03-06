package com.learning.tweety.tweetyhome.securityconfig.activemq;

public class ActiveMQConstants {
	
	public static final String DELETE_FOLLOWING = "ackdeletefollowing";
	
	public static final String ADD_FOLLOWING = "ackaddfollowing";

	public static final String CONTAINER_FACTORY = "jmsListenerContainerFactory";
			
	public static final String RETWEET_TOPIC = "VirtualTopic.retweetmessages";
	
	public static final String FAVOURITE_TOPIC = "VirtualTopic.favouritemessages";
	
	public static final String TWEETY_MESSAGES_TOPIC = "VirtualTopic.tweetymessages";
	
	public static final String DELETE_TWEETY_MESSAGES_TOPIC = "VirtualTopic.deletetweetymessages";
	
	public static final String TWEETY_SEARCH_TOPIC = "VirtualTopic.tweetysearch";
	
	public static final String DELETE_RETWEET_TOPIC = "VirtualTopic.delretweetmessages";
	
	public static final String DELETE_FAVOURITE_TOPIC = "VirtualTopic.delfavouritemessages";
	
	public static final String RETWEET_COUNT_TOPIC = "VirtualTopic.retweetcount";
	
	public static final String FAVOURITE_COUNT_TOPIC = "VirtualTopic.favouritecount";
	
	public static final String DEL_RETWEET_COUNT_TOPIC = "VirtualTopic.delretweetcount";
	
	public static final String DEL_FAVOURITE_COUNT_TOPIC = "VirtualTopic.delfavouritecount";
	
	public static final String ADD_FOLLOWINGS_TOPIC = "VirtualTopic.addfollowings";
	
	public static final String DELETE_FOLLOWINGS_TOPIC = "VirtualTopic.deletefollowings";
	
	public static final String CONS_RETWEET_TOPIC = "Consumer.myConsumer1.VirtualTopic.retweetmessages";
	
	public static final String CONS_FAVOURITE_TOPIC = "Consumer.myConsumer1.VirtualTopic.favouritemessages";
	
	public static final String CONS_DELETE_RETWEET_TOPIC = "Consumer.myConsumer1.VirtualTopic.delretweetmessages";
	
	public static final String CONS_DELETE_FAVOURITE_TOPIC = "Consumer.myConsumer1.VirtualTopic.delfavouritemessages";
	
	public static final String CONS_ACK_DELETE_FOLLOWING = "Consumer.myConsumer1.VirtualTopic.ackdeletefollowing";
	
	public static final String CONS_ACK_ADD_FOLLOWING = "Consumer.myConsumer1.VirtualTopic.ackaddfollowing";
}
