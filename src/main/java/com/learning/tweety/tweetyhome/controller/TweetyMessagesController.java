package com.learning.tweety.tweetyhome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.learning.tweety.tweetyhome.repository.TweetyMessages;
import com.learning.tweety.tweetyhome.services.common.TweetyMessageService;
import com.learning.tweety.tweetyhome.services.token.JWTTokenService;

import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/tweetyhome")
public class TweetyMessagesController {
	
	@Autowired
	TweetyMessageService tweetyMessageService;
	
	@Autowired
	JWTTokenService jwtTokenService;
	
	@PostMapping("/createMessage")
	public TweetyMessages postTwittyMessage(@RequestHeader("Authorization") String token,
			@RequestBody TweetyMessages message) {
		jwtTokenService.isValidUser(token, message.getUserName());
		return tweetyMessageService.createTweetyMessage(message);
	}
	
	@DeleteMapping("/deleteMessage/{tweetId}/{userName}")
	public void postTwittyMessage(@RequestHeader("Authorization") String token,
			@PathVariable Long tweetId,
			@PathVariable String userName) {
		jwtTokenService.isValidUser(token, userName);
		tweetyMessageService.deleteTweetyMessage(tweetId, userName);
	}
	
	@GetMapping("/getMessage/{userName}/{pageIndex}/{noOfRows}")
	public List<TweetyMessages> getTwittyMessage(@RequestHeader("Authorization") String token,
			@PathVariable String userName, 
			@PathVariable int pageIndex,
			@PathVariable int noOfRows) {
		jwtTokenService.isValidUser(token, userName);
		return tweetyMessageService.getProfileTweetyMessage(userName, pageIndex, noOfRows);
	}
}
