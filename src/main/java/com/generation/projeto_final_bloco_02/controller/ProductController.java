package com.generation.projeto_final_bloco_02.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.projeto_final_bloco_02.model.Product;
import com.generation.projeto_final_bloco_02.repository.ProductRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@GetMapping
	public ResponseEntity<List<Product>> getAll() {
		return ResponseEntity.ok(productRepository.findAll());

	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getById(@PathVariable Long id) {
		return productRepository.findById(id).map(response -> ResponseEntity.ok(response))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@GetMapping("/product/{productName}")
	public ResponseEntity<List<Product>> getByProductName(@PathVariable String productName) {
		return ResponseEntity.ok(productRepository.findAllByProductNameContainingIgnoreCase(productName));
	}

	@PostMapping
	public ResponseEntity<Product> post(@Valid @RequestBody Product product) {
		return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(product));
	}

	@PutMapping
	public ResponseEntity<Product> put(@Valid @RequestBody Product product) {
		return productRepository.findById(product.getId())
				.map(response -> ResponseEntity.status(HttpStatus.OK).body(productRepository.save(product)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Product> product = productRepository.findById(id);

		if (product.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		productRepository.deleteById(id);
	}

}
