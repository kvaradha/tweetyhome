package com.learning.tweety.tweetyhome.repository;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "retweet")
public class Retweet {
	@Column(name = "retweet_id")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long retweetId;
	@Column(name = "created")
	Date creationDate;
	@Column(name = "twittermessages_id")
	Long twitterMessageId;
	@Column(name = "username")
	String userName;
	public Long getRetweetId() {
		return retweetId;
	}
	public void setRetweetId(Long retweetId) {
		this.retweetId = retweetId;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Long getTwitterMessageId() {
		return twitterMessageId;
	}
	public void setTwitterMessageId(Long twitterMessageId) {
		this.twitterMessageId = twitterMessageId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
