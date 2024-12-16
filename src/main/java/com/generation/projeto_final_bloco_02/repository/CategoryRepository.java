package com.generation.projeto_final_bloco_02.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.projeto_final_bloco_02.model.Category;


public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	public List<Category> findAllByCategoryNameContainingIgnoreCase(@Param("category") String categoryName);

}