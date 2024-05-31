package com.delish.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ContactInformation {

	private String email;
	
	private String mobile;
	
	private String twitter;
	
	private String instagram;
	
}
