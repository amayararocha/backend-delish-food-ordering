package com.delish.service;

import java.util.List;

import com.delish.model.Category;
import com.delish.model.Food;
import com.delish.model.Restaurant;

public interface FoodService {
	
	public Food createFood(CreateFoodRequest req, 
			Category category, 
			Restaurant restaurant);
	
	void deleteFood(Long foodId) throws Exception;
	
	public List<Food> getRestaurantsFood (Long restaurantId, 
			boolean isVegetarian, 
			boolean isNonVeg, 
			boolean isSeasonal, 
			String foodCategory);

	public List<Food> searchFood(String keyword);
	
	public Food findFoodById(Long foodId) throws Exception;
	
	public Food updateAvailibilityStatus(Long foodId) throws Exception;
}
