package com.delish.dto;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;


@Data
@Embeddable
public class RestaurantDto {
	
	private String title;
	
	@Column(length = 1000)
	private List<String> imagens;
	
	private String description;
	
	private Long id;
}
