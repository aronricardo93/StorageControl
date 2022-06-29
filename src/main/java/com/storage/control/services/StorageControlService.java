package com.storage.control.services;


import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.storage.control.dtos.product.ProductForm;
import com.storage.control.models.Product;
import com.storage.control.models.ProductBrand;
import com.storage.control.models.ProductLabel;
import com.storage.control.repositories.StorageControlRepository;
import com.storage.control.repositories.brand.BrandRepository;

@Service
public class StorageControlService {

	@Autowired
	private StorageControlRepository storageControlRepository;
	
	@Autowired
	private BrandRepository brandRepository;
	
	public Page<Product> getProducts(Pageable pageable){
		return storageControlRepository.findAll(pageable);
	}

	public Product getProductById(UUID uuid) {
		Optional<Product> optional = storageControlRepository.findById(uuid);
		
		return optional.orElseThrow();
	}
	

	@Transactional
	public Product saveProduct(ProductForm form) {
		
		var product = new Product();
		var productBrand = new ProductBrand(form.getProductBrand().getBrandName());
		
		var productLabel = new ProductLabel(form.getProductLabel().getTotalFat(), 
				form.getProductLabel().getSaturedFat(), form.getProductLabel().getProtein(), 
				form.getProductLabel().getSugars(), form.getProductLabel().getCholesterol(), 
				form.getProductLabel().getSodium(), form.getProductLabel().getPotassium(), 
				form.getProductLabel().getTotalCarbohydrate(), form.getProductLabel().getFiber());
		
		product.setName(form.getName());
		product.setUnitaryValue(form.getUnitaryValue());
		product.setQuantity(form.getQuantity());
		product.setProductLabel(productLabel);
		product.setCategory(form.getCategory());
		product.setEntryTime(LocalDateTime.now());
		product.setStorageSituation(product.checkStorage(form.getQuantity()));
		
		brandRepository.save(productBrand);
		storageControlRepository.save(product);
		
		return product;
	}

	public Product updateProduct(UUID uuid, Product product) {
		Product originalProduct = this.getProductById(uuid);
		product.setUuid(originalProduct.getUuid());
				
		return storageControlRepository.save(product);
	}

	@Transactional
	public Product deleteProduct(UUID uuid) {
		Product originalProduct = this.getProductById(uuid);
		
		storageControlRepository.delete(originalProduct);
		
		return originalProduct;
	}

}