package com.storage.control.dtos.label;

import com.storage.control.models.ProductLabel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductLabelDto {
	private String totalFat;
	private String saturedFat;
	private String protein;
	private String sugars;
	private String cholesterol;
	private String sodium;
	private String potassium;
	private String totalCarbohydrate;
	private String fiber;
	
	public static ProductLabelDto convertToDto(ProductLabel productLabel) {
		return new ProductLabelDto(productLabel.getTotalFat(), productLabel.getSaturedFat(), productLabel.getProtein(),
				productLabel.getSugars(), productLabel.getCholesterol(), productLabel.getSodium(), productLabel.getPotassium(), 
				productLabel.getTotalCarbohydrate(), productLabel.getFiber());
	}
}
