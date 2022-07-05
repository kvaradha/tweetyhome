package com.learning.tweety.tweetyhome.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TweetyMessagesRepository extends CrudRepository<TweetyMessages, Long> {
	
	@Query(value = "select * from twittermessages where username = ?1", nativeQuery = true)
	Iterable<TweetyMessages> findOnlyUserCreatedMessages(String userName);
	
	@Query(value = "delete from twittermessages where twittermessages_id = ?1 and "
			+ "username = ?2", nativeQuery = true)
	@Modifying
	@Transactional
	void deleteTweet(Long tweetId, String userName);
	
	@Query(value = "select * from twittermessages where twittermessages_id = ?1 "
			+ "and username = ?2", nativeQuery = true)
	TweetyMessages findByTweetID(Long tweetyID, String userName);
	
	@Query(value = "select * from twittermessages where twittermessages_id in (\r\n"
			+ "(select twittermessages_id from retweet where username = ?1\r\n"
			+ "UNION\r\n"
			+ "select twittermessages_id from favourite where username = ?1))\r\n"
			+ "or username = ?1 order by twittermessages_id desc", nativeQuery = true)
	List<TweetyMessages> findAllUserRelatedMessages(String userName, Pageable pageable);
}
