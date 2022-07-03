package com.learning.tweety.tweetyhome.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FollowingAckRepository extends CrudRepository<FollowingAck, Long> {

	@Query(value = "select * from followingack where following_id = ?1" + " and message_type = ?2", nativeQuery = true)
	FollowingAck findPayloadAck(Long followingId, String messageType);
}
