package com.generation.projeto_final_bloco_02.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.generation.projeto_final_bloco_02.model.Product;
import com.generation.projeto_final_bloco_02.repository.ProductRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductControllerTest {
	

		@Autowired
		private TestRestTemplate testRestTemplate;

		@Autowired
		private ProductRepository productRepository;

		@BeforeAll
		void start(){

			productRepository.deleteAll();

		}

		@Test
		@DisplayName("Cadastro de Produto")
		public void createProduct() {
			
			BigDecimal bd1 = new BigDecimal("10");

			HttpEntity<Product> reqBody = new HttpEntity<Product>(new Product(0L, 
				"Dipirona", bd1));

			ResponseEntity<Product> resBody = testRestTemplate
				.exchange("/products", HttpMethod.POST, reqBody, Product.class);

			assertEquals(HttpStatus.CREATED, resBody.getStatusCode());
		
		}

}
