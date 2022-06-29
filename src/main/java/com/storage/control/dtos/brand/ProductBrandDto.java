package com.storage.control.dtos.brand;

import java.util.List;
import java.util.UUID;

import com.storage.control.models.ProductBrand;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductBrandDto {
	
	private UUID uuid;
		
	private String brandName;

	public ProductBrandDto(String brandName) {
		this.brandName = brandName;
	}
	
	public static ProductBrandDto convertToDto(List<ProductBrand> productBrand) {
		return new ProductBrandDto(productBrand.toString());
	}


}
