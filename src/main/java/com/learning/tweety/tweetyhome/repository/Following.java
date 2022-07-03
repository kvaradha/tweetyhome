package com.learning.tweety.tweetyhome.repository;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "following")
public class Following {
	@Column(name = "following_id")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long followingId;
	@Column(name = "created")
	Date creationDate;
	@Column(name = "username")
	String userName;
	@Column(name = "following")
	String followingName;
	public Long getFollowingId() {
		return followingId;
	}
	public void setFollowingId(Long followingId) {
		this.followingId = followingId;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFollowingName() {
		return followingName;
	}
	public void setFollowingName(String followingName) {
		this.followingName = followingName;
	}
	
}
