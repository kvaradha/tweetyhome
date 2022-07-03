package com.learning.tweety.tweetyhome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.tweety.tweetyhome.repository.Favourite;
import com.learning.tweety.tweetyhome.services.common.FavouriteService;
import com.learning.tweety.tweetyhome.services.token.JWTTokenService;

@RestController
@RequestMapping("/tweetyhome/favourite")
public class FavouriteController {
	@Autowired
	FavouriteService favouriteService;
	
	@Autowired
	JWTTokenService jwtTokenService;
	
	@PostMapping("/favouritemessage")
	public Favourite retweetMessage(@RequestHeader("Authorization") String token,
			@RequestBody Favourite favouriteInfo) {
		jwtTokenService.isValidUser(token, favouriteInfo.getUserName());
		return favouriteService.updateFavourite(favouriteInfo);
	}
	
	@DeleteMapping("/deletefavouritemessage/{tweetId}/{userName}")
	public void deteleFavouriteMessage(@RequestHeader("Authorization") String token,
			@PathVariable Long tweetId, @PathVariable String userName) {
		jwtTokenService.isValidUser(token, userName);
		favouriteService.deleteFavourite(tweetId, userName);
	}
}
