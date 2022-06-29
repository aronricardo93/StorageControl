package com.storage.control.dtos.product;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.storage.control.dtos.brand.ProductBrandForm;
import com.storage.control.dtos.label.ProductLabelForm;
import com.storage.control.enums.ProductCategory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductForm {

	@NotBlank(message = "Name cannot be blank!")
	private String name;
	
	private ProductBrandForm productBrand;
	
	@NotNull(message = "Unitary value cannot be null!")
	private BigDecimal unitaryValue;
	
	@NotNull(message = "Quantity cannot be null!")
	private Long quantity;
	
	private ProductLabelForm productLabel;
	
	private ProductCategory category;
	
}
