package com.storage.control.repositories.brand;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.storage.control.models.ProductBrand;

@Repository
public interface BrandRepository extends JpaRepository<ProductBrand, Long>{

}
