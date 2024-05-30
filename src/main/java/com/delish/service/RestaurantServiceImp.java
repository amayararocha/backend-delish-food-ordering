package com.delish.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delish.dto.RestaurantDto;
import com.delish.model.Restaurant;
import com.delish.model.User;
import com.delish.repository.AddressRepository;
import com.delish.repository.RestaurantRepository;
import com.delish.request.CreateRestaurantRequest;

@Service
public class RestaurantServiceImp implements RestaurantService{
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserService userService;

	@Override
	public Restaurant createRestaurant(CreateRestaurantRequest raq, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRestaurant(Long restaurantId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Restaurant> getRestaurant() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Restaurant> searchRestaurant() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Restaurant findRestaurantByIdRestaurant(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Restaurant getRestaurantByUserId(Long userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestaurantDto addToFavorites(Long restauratId, User user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Restaurant updateRestaurantStatus(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
