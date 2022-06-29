package com.storage.control.dtos.label;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductLabelForm {

	@NotBlank
	private String totalFat;
	
	@NotBlank
	private String saturedFat;
	
	@NotBlank
	private String protein;
	
	@NotBlank
	private String sugars;
	
	@NotBlank
	private String cholesterol;
	
	@NotBlank
	private String sodium;
	
	@NotBlank
	private String potassium;
	
	@NotBlank
	private String totalCarbohydrate;
	
	@NotBlank
	private String fiber;
}
