package com.delish.request;

import lombok.Data;

@Data
public class UpdateCartItemhRequest {
	
	private Long cartItemId;
	
	private int quantity;
}
