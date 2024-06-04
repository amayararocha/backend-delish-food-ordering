package com.delish.service;

import java.util.List;

import com.delish.model.Category;

public interface CategoryService {

	public Category createCategory(String name, Long userId);
	
	public List<Category> findCategoryByRestaurantId (Long id) throws Exception;
	
	public Category findCategoryById(Long id) throws Exception;
}
