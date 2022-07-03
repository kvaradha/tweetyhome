package com.learning.tweety.tweetyhome.repository;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "followingack")
public class FollowingAck {
	@Column(name = "followingack_id")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long followingAckId;
	@Column(name = "created")
	Date creationDate;
	@Column(name = "payload", columnDefinition = "TEXT")
	String payload;
	@Column(name = "ack_status")
	String ackStatus;
	@Column(name = "message_type")
	String message_type;
	@Column(name = "following_id")
	Long following_id;
	public Long getFollowingAckId() {
		return followingAckId;
	}
	public void setFollowingAckId(Long followingAckId) {
		this.followingAckId = followingAckId;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	public String getAckStatus() {
		return ackStatus;
	}
	public void setAckStatus(String ackStatus) {
		this.ackStatus = ackStatus;
	}
	public String getMessage_type() {
		return message_type;
	}
	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}
	public Long getFollowing_id() {
		return following_id;
	}
	public void setFollowing_id(Long following_id) {
		this.following_id = following_id;
	}
}
