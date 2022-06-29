package com.storage.control.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.storage.control.models.Product;

@Repository
public interface StorageControlRepository extends JpaRepository<Product, UUID>{

	Page<Product> findAll(Pageable pageable);

}
