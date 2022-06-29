package com.storage.control.models;

import javax.persistence.Embeddable;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_label")
@Embeddable
public class ProductLabel {
	
	private String totalFat;
	private String saturedFat;
	private String protein;
	private String sugars;
	private String cholesterol;
	private String sodium;
	private String potassium;
	private String totalCarbohydrate;
	private String fiber;
}
