package com.learning.tweety.tweetyhome.credentials;

public class Credentials {
	private Long credentialsId;
	private String serverName;
	private String userName;
	private String passWord;
	private String serverUrl;

	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public Long getCredentialsId() {
		return credentialsId;
	}
	public void setCredentialsId(Long credentialsId) {
		this.credentialsId = credentialsId;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
}
