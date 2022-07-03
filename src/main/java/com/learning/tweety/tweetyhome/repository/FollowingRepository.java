package com.learning.tweety.tweetyhome.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FollowingRepository extends CrudRepository<Following, Long> {
	@Query(value = "delete from following where following = ?1" + " and username = ?2", nativeQuery = true)
	@Modifying
	@Transactional
	void deleteByFollowersName(String following, String userName);
	
	@Query(value = "select * from following where following = ?1" + " and username = ?2", nativeQuery = true)
	Following findFollowing(String following, String userName);
}
