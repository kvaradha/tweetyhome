package com.learning.tweety.tweetyhome.repository;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "favourite")
public class Favourite {
	@Column(name = "favourite_id")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long favouriteId;
	@Column(name = "created")
	Date creationDate;
	@Column(name = "twittermessages_id")
	Long twitterMessageId;
	@Column(name = "username")
	String userName;
	public Long getFavouriteId() {
		return favouriteId;
	}
	public void setFavouriteId(Long favouriteId) {
		this.favouriteId = favouriteId;
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
