package com.learning.tweety.tweetyhome.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface FavouriteRepository extends CrudRepository<Favourite, Long> {
	@Query(value = "delete from favourite where twittermessages_id = ?1" + " and username = ?2", nativeQuery = true)
	@Modifying
	@Transactional
	void deleteFavourite(Long tweetyId, String userName);

	@Query(value = "delete from favourite where twittermessages_id = ?1", nativeQuery = true)
	@Modifying
	@Transactional
	void deleteByTweetId(Long tweetyId);
	
	@Query(value = "select * from favourite where twittermessages_id = ?1" + " and username = ?2", nativeQuery = true)
	Favourite findFavourite(Long tweetyId, String userName);
	
	@Query(value = "select count(*) from favourite where twittermessages_id = ?1 "
			+ "and username = ?2", nativeQuery = true)
	Long findByTweetID(Long tweetyID, String userName);
}
