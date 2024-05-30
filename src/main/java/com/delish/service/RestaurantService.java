package com.delish.service;

import java.util.List;

import com.delish.dto.RestaurantDto;
import com.delish.model.Restaurant;
import com.delish.model.User;
import com.delish.request.CreateRestaurantRequest;

public interface RestaurantService {

	public Restaurant createRestaurant(CreateRestaurantRequest raq, User user);
	
	public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception;
	
	public void deleteRestaurant(Long restaurantId) throws Exception;
	
	public List<Restaurant> getRestaurant();
	
	public List<Restaurant> searchRestaurant();
	
	public Restaurant findRestaurantByIdRestaurant(Long id) throws Exception;
	
	public Restaurant getRestaurantByUserId (Long userId) throws Exception;
	
	public RestaurantDto addToFavorites(Long restauratId, User user) throws Exception;
	
	public Restaurant updateRestaurantStatus(Long id) throws Exception;
	
}
 