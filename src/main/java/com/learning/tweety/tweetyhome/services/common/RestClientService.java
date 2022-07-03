package com.learning.tweety.tweetyhome.services.common;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.learning.tweety.tweetyhome.credentials.Credentials;
import com.learning.tweety.tweetyhome.securityconfig.ApplicationProperties;

@Service
public class RestClientService {

	@Autowired
	ApplicationProperties property;
	String token;

	public Credentials getCredentials(String serverName) {
		try {
			return getCredentialsFromAuthServer(serverName, token);
		} catch(Exception ex) {
			token = getAccessToken();
		}
		//In case of token is empty or expired retry fetching creds.
		return getCredentialsFromAuthServer(serverName, token);
	}
	
	private Credentials getCredentialsFromAuthServer(String serverName, String accessToken) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = createBearerHeaders(accessToken);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<Credentials> resp = restTemplate.exchange(
				property.getResourceUrl() + serverName, 
				HttpMethod.GET, entity, Credentials.class);
		Credentials creds = resp.getBody();
		return creds;
	}

	private String getAccessToken() {
		String userName = property.getClientid();
		String password = property.getClientsecret();
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = createHeaders(userName, password);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<String> response = restTemplate.exchange(
				property.getAccessTokenUrl() + userName, HttpMethod.GET, entity, 
				String.class);
		return response.getBody();
	}

	@SuppressWarnings("serial")
	private HttpHeaders createHeaders(String username, String password) {
		return new HttpHeaders() {{
			String auth = username + ":" + password;
			byte[] encodedAuth = Base64.encodeBase64( 
					auth.getBytes(Charset.forName("US-ASCII")) );
			String authHeader = "Basic " + new String( encodedAuth );
			set( "Authorization", authHeader );
		}};
	}

	@SuppressWarnings("serial")
	private HttpHeaders createBearerHeaders(String token) {
		return new HttpHeaders() {{
			String authHeader = "Bearer " + token;
			set( "Authorization", authHeader );
		}};
	}
}
