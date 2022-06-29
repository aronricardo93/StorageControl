package com.storage.control.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.storage.control.enums.ProductCategory;
import com.storage.control.enums.StorageSituation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID uuid;
	
	@Column(nullable = false, length = 20)
	private String name;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<ProductBrand> productBrand;
	
	@NotNull
	private BigDecimal unitaryValue;
	
	@NotNull
	private Long quantity;
	
	@Embedded
	private ProductLabel productLabel;
	
	@Enumerated(EnumType.STRING)
	private ProductCategory category;
	
	@Enumerated(EnumType.STRING)
	private StorageSituation storageSituation;
	
	private LocalDateTime entryTime = LocalDateTime.now();

	public Product(String name, BigDecimal unitaryValue,
			 Long quantity, ProductLabel productLabel, ProductCategory category) {
		this.name = name;
		this.unitaryValue = unitaryValue;
		this.quantity = quantity;
		this.productLabel = productLabel;
		this.category = category;
	}
	
	public StorageSituation checkStorage(Long quantity) {
		if(quantity == 0 || quantity.equals(null)) {
			setStorageSituation(StorageSituation.EMPTY_STOCK);
		}else if(quantity > 1 && quantity <= 10){
			setStorageSituation(StorageSituation.WARNING);
		}else {
			setStorageSituation(StorageSituation.IN_STOCK);
		}
		
		return getStorageSituation();
	}

}
