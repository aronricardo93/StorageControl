package com.storage.control.controllers;


import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.storage.control.dtos.product.ProductDto;
import com.storage.control.dtos.product.ProductForm;
import com.storage.control.models.Product;
import com.storage.control.services.StorageControlService;

@RestController
@RequestMapping("api/v1/products")
public class StorageControlController {

	@Autowired
	private StorageControlService storageControlService;

	@GetMapping("/{uuid}")
	public ResponseEntity<ProductDto> getOneProduct(@PathVariable(required = true) UUID uuid){
		try {
			var product = storageControlService.getProductById(uuid);
			
			return ResponseEntity.ok(ProductDto.convertToDto(product));
		}catch(Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping
	@Cacheable(value = "productList")
	public ResponseEntity<Object> getAllProducts(@PageableDefault(sort = "uuid", size = 3, 
	direction = Direction.ASC, page = 0) Pageable pageable){
		
		Page<Product> products= storageControlService.getProducts(pageable);
		
		if(products.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sorry! Product list not found!");
		}

			
		//BeanUtils.copyProperties(products, dtos);
		return ResponseEntity.status(HttpStatus.OK).body(products);
		
	}
	
	@PostMapping
	@CacheEvict(value = "productList", allEntries = true)
	public ResponseEntity<Object> saveProduct(@RequestBody @Valid ProductForm form){
		
		return new ResponseEntity<>(storageControlService.saveProduct(form), HttpStatus.CREATED);
	}
	
	@PutMapping("/{uuid}")
	@CacheEvict(value = "productList", allEntries = true)
	public ResponseEntity<Object> updateProduct(@PathVariable(value = "uuid") UUID uuid, @RequestBody @Valid ProductForm updatedForm){
		
		Product newProduct = new Product();
		
		BeanUtils.copyProperties(updatedForm, newProduct);
		Product updatedProduct = storageControlService.updateProduct(uuid, newProduct);
		
		ProductDto dto = new ProductDto();
		BeanUtils.copyProperties(updatedProduct, dto);		
		
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
	
	@DeleteMapping("/{uuid}")
	@CacheEvict(value = "productList", allEntries = true)
	public ResponseEntity<ProductDto> deleteProduct(@PathVariable(value = "uuid") UUID uuid){
		
		Product productModel = storageControlService.deleteProduct(uuid);
		var dto = new ProductDto();
		
		BeanUtils.copyProperties(productModel, dto);
		
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
	
}	

