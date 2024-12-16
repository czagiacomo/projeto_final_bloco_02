package com.generation.projeto_final_bloco_02.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.projeto_final_bloco_02.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	public List<Product> findAllByProductNameContainingIgnoreCase(@Param("productName")String productName);
	
}
