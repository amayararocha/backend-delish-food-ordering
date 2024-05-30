package com.delish.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.delish.dto.RestaurantDto;
import com.delish.model.Restaurant;
import com.delish.model.User;
import com.delish.request.CreateRestaurantRequest;

@Service
public interface RestaurantService {

	public Restaurant createRestaurant(CreateRestaurantRequest req, User user);
	
	public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception;
	
	public void deleteRestaurant(Long restaurantId) throws Exception;
	
	public List<Restaurant> getAllRestaurant();
	
	public List<Restaurant> searchRestaurant(String keyword);
	
	public Restaurant findRestaurantById(Long id) throws Exception;
	
	public Restaurant getRestaurantByUserId (Long userId) throws Exception;
	
	public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception;
	
	public Restaurant updateRestaurantStatus(Long id) throws Exception;
	
}
 