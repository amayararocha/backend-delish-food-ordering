package com.delish.service;

import java.util.List;

import com.delish.model.Category;
import com.delish.model.IngredientsItem;

import lombok.Data;

@Data
public class CreateFoodRequest {
	
	private String name;
	
	private String description;
	
	private Long price;
	
	private Category category;
	
	private List<String> images;
	
	private Long restaurantId;
	
	private boolean vegetarian;
	
	private boolean seasonal;
	
	private List<IngredientsItem> ingredientes;
	
}
