package com.learning.tweety.tweetyhome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.tweety.tweetyhome.repository.Retweet;
import com.learning.tweety.tweetyhome.services.common.RetweetService;
import com.learning.tweety.tweetyhome.services.token.JWTTokenService;

@RestController
@RequestMapping("/tweetyhome/retweet")
public class RetweetController {
	@Autowired
	RetweetService retweetService;
	
	@Autowired
	JWTTokenService jwtTokenService;
	
	@PostMapping("/retweetmessage")
	public Retweet retweetMessage(@RequestHeader("Authorization") String token, 
			@RequestBody Retweet retweetInfo) {
		jwtTokenService.isValidUser(token, retweetInfo.getUserName());
		return retweetService.updateRetweet(retweetInfo);
	}
	
	@DeleteMapping("/deleteretweetmessage/{tweetId}/{userName}")
	public void deteleRetweetMessage(@RequestHeader("Authorization") String token, 
			@PathVariable Long tweetId, @PathVariable String userName) {
		jwtTokenService.isValidUser(token, userName);
		retweetService.deleteRetweet(tweetId, userName);
	}
}
