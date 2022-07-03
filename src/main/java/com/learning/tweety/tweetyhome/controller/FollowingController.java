package com.learning.tweety.tweetyhome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.learning.tweety.tweetyhome.repository.Following;
import com.learning.tweety.tweetyhome.services.common.FollowingService;
import com.learning.tweety.tweetyhome.services.token.JWTTokenService;

@RestController
@RequestMapping("/tweetyhome/following")
public class FollowingController {
	
	@Autowired
	FollowingService followingService;
	
	@Autowired
	JWTTokenService jwtTokenService;
	
	@PostMapping("/newfollowing")
	public Following follow(@RequestHeader("Authorization") String token,
			@RequestBody Following following) {
		jwtTokenService.isValidUser(token, following.getUserName());
		return followingService.addFollowers(following);
	}
	
	@DeleteMapping("/deletefollowing/{followingName}/{userName}")
	public void deleteFollow(@RequestHeader("Authorization") String token,
			@PathVariable String followingName, @PathVariable String userName) {
		jwtTokenService.isValidUser(token, userName);
		followingService.deleteFollowers(followingName, userName);
	}

}
