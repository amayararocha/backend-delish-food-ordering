package com.delish.request;

import com.delish.model.Address;

import lombok.Data;

@Data
public class OrderRequest {

	private Long restaurantId;
	
	private Address deliveryAddress;
}
