package com.delish.request;

import java.util.List;

import com.delish.model.Address;
import com.delish.model.ContactInformation;

import lombok.Data;

@Data
public class CreateRestaurantRequest {
	
	private Long id;
	
	private String name;
	
	private String description;
	
	private String cuisineType;
	
	private Address address;
	
	private ContactInformation contactInformantion;
	
	private String openingHours;

	private List<String> images;
	
}
