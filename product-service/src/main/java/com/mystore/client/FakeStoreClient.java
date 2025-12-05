package com.mystore.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.mystore.dto.ProductsDTO;

import com.mystore.client.fallback.ProductsClientFallback;

@FeignClient(name = "${component.fakestore.name}",url = "${component.fakestore.url}", fallbackFactory = ProductsClientFallback.class)
public interface FakeStoreClient {

	//Products endpoints
	
	@GetMapping(value = "/products")
	List<ProductsDTO> getAllProducts();
	
	@PostMapping(value = "/products")
	ProductsDTO createProduct(@RequestBody ProductsDTO productDTO);
	
	@GetMapping(value = "/products/{id}")
	ProductsDTO getProduct(@PathVariable("id") Long id);
	
	@PutMapping(value = "/products/{id}")
	ProductsDTO updateProduct(@PathVariable("id") Long id,@RequestBody ProductsDTO productDTO);
	
	@DeleteMapping(value = "/products/{id}")
	ProductsDTO deleteProduct(@PathVariable("id") Long id);
	


}
