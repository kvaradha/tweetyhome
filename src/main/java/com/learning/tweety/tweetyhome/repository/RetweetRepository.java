package com.learning.tweety.tweetyhome.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RetweetRepository extends CrudRepository<Retweet, Long> {
	@Query(value = "delete from retweet where twittermessages_id = ?1 and username = ?2", nativeQuery = true)
	@Transactional
	@Modifying
	void deleteRetweet(Long tweetyId, String userName);

	@Query(value = "delete from retweet where twittermessages_id = ?1", nativeQuery = true)
	@Modifying
	@Transactional
	void deleteByTweetId(Long tweetyId);
	
	@Query(value = "select * from retweet where twittermessages_id = ?1 and username = ?2", nativeQuery = true)
	Retweet findRetweet(Long tweetyId, String userName);
	
	@Query(value = "select count(*) from retweet where twittermessages_id = ?1 "
			+ "and username = ?2", nativeQuery = true)
	Long findByTweetID(Long tweetyID, String userName);
}
