package com.mystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mystore.dto.ProductsDTO;
import com.mystore.dto.ResponseDTO;
import com.mystore.service.ProductsService;

@RestController
@RequestMapping("/products")
public class ProductsController {

	@Autowired
	ProductsService productsService;

	@GetMapping(value = "/all")
	public ResponseEntity<ResponseDTO> getAllProducts() {
		return productsService.getAllProducts();
	}

	@PostMapping(value = "/create")
	public ResponseEntity<ResponseDTO> creteProduct(@RequestBody @Validated ProductsDTO productDTO) {
		return productsService.createProduct(productDTO);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseDTO> getProduct(@PathVariable("id") String id) throws InterruptedException {
		try {
			Long longId = Long.parseLong(id);
			Thread.sleep(2000); // Simula una demora de 2 segundos
			
			return productsService.getProduct(longId);
		} catch (NumberFormatException e) {
			return ResponseEntity.badRequest()
					.body(new ResponseDTO("El id debe ser un numero", "valor no valido", null));
		}

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ResponseDTO> updateProduct(@PathVariable("id") Long id, @RequestBody ProductsDTO productDTO) {
		return productsService.updateProduct(id, productDTO);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ResponseDTO> deleteProduct(@PathVariable("id") Long id) {
		return productsService.deleteProduct(id);
	}

}
