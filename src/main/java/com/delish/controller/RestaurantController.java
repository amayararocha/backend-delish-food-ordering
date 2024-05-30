package com.delish.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.delish.dto.RestaurantDto;
import com.delish.model.Restaurant;
import com.delish.model.User;
import com.delish.service.RestaurantService;
import com.delish.service.UserService;

@RestController
@RequestMapping("api/restaurants")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/search")
	public ResponseEntity<List<Restaurant>> searchRestaurant( 
			@RequestHeader("Authorization") String jwt, @RequestParam String keyword) 
			throws Exception {
		
		User user = userService.findUserByJwtToken(jwt);
		
		List<Restaurant> restaurant = restaurantService.searchRestaurant(keyword);
		
		return new ResponseEntity<>(restaurant, HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<List<Restaurant>> getAllRestaurant( 
			@RequestHeader("Authorization") String jwt) 
			throws Exception {
		
		User user = userService.findUserByJwtToken(jwt);
		
		List<Restaurant> restaurant = restaurantService.getAllRestaurant();
		
		return new ResponseEntity<>(restaurant, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Restaurant> findRestaurantById( 
			@RequestHeader("Authorization") String jwt,
			@PathVariable Long id) 
			throws Exception {
		
		User user = userService.findUserByJwtToken(jwt);
		
		Restaurant restaurant = restaurantService.findRestaurantById(id);
		
		return new ResponseEntity<>(restaurant, HttpStatus.OK);
	}
	
	@PutMapping("/{id}/add-favorities")
	public ResponseEntity<RestaurantDto> addToFavorities( 
			@RequestHeader("Authorization") String jwt,
			@PathVariable Long id) 
			throws Exception {
		
		User user = userService.findUserByJwtToken(jwt);
		
		RestaurantDto restaurant = restaurantService.addToFavorites(id, user);
		
		return new ResponseEntity<>(restaurant, HttpStatus.OK);
	}
}
