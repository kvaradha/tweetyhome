package com.learning.tweety.tweetyhome.securityconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@ConfigurationPropertiesScan
@EnableConfigurationProperties
@ConfigurationProperties("spring")
public class ApplicationProperties {
	@Value("${spring.env.name}")
	private String envName;
	@Value("${spring.authserver.accessTokenUrl}")
	private String accessTokenUrl;
	@Value("${spring.authserver.resourceUrl}")
	private String resourceUrl;
	@Value("${spring.authserver.clientid}")
	private String clientid;
	@Value("${spring.authserver.clientsecret}")
	private String clientsecret;
	@Value("${spring.jwt.id}")
	private String jwtID;
	private String jwSecret;
	@Value("${spring.threadPool.corePoolSize}")
	private String corePoolSize;
	@Value("${spring.threadPool.maxPoolSize}")
	private String maxPoolSize;
	@Value("${spring.activeMQ.serverName}")
	private String activeMqServer;
	
	public String getActiveMqServer() {
		return activeMqServer;
	}
	public void setActiveMqServer(String activeMqServer) {
		this.activeMqServer = activeMqServer;
	}
	public String getEnvName() {
		return envName;
	}
	public void setEnvName(String envname) {
		this.envName = envname;
	}
	public String getAccessTokenUrl() {
		return accessTokenUrl;
	}
	public void setAccessTokenUrl(String accessTokenUrl) {
		this.accessTokenUrl = accessTokenUrl;
	}
	public String getResourceUrl() {
		return resourceUrl;
	}
	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}
	public String getClientid() {
		return clientid;
	}
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	public String getClientsecret() {
		return clientsecret;
	}
	public void setClientsecret(String clientsecret) {
		this.clientsecret = clientsecret;
	}
	public String getJwtID() {
		return jwtID;
	}
	public void setJwtID(String jwtID) {
		this.jwtID = jwtID;
	}
	public String getJwSecret() {
		return jwSecret;
	}
	public void setJwSecret(String jwSecret) {
		this.jwSecret = jwSecret;
	}
	public String getCorePoolSize() {
		return corePoolSize;
	}
	public void setCorePoolSize(String corePoolSize) {
		this.corePoolSize = corePoolSize;
	}
	public String getMaxPoolSize() {
		return maxPoolSize;
	}
	public void setMaxPoolSize(String maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}
}
