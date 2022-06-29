package com.storage.control.dtos.product;

import java.math.BigDecimal;
import java.util.UUID;

import com.storage.control.dtos.brand.ProductBrandDto;
import com.storage.control.dtos.label.ProductLabelDto;
import com.storage.control.enums.ProductCategory;
import com.storage.control.enums.StorageSituation;
import com.storage.control.models.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

	private UUID uuid;
	
	private String name;
	
	private ProductBrandDto productBrandDto;
	
	private BigDecimal unitaryValue;
	
	private Long quantity;
	
	private ProductLabelDto productLabelDto;
	
	private ProductCategory category;
	
	private StorageSituation storageSituation;
	
	public static ProductDto convertToDto(Product product) {
		return new ProductDto(product.getUuid() ,product.getName(), ProductBrandDto.convertToDto(product.getProductBrand()) ,product.getUnitaryValue(),product.getQuantity(), 
				ProductLabelDto.convertToDto(product.getProductLabel()), product.getCategory(), product.getStorageSituation());
	}
	
}
