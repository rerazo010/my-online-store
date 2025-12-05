package com.mystore.service;

import org.springframework.http.ResponseEntity;
import com.mystore.dto.ProductsDTO;
import com.mystore.dto.ResponseDTO;

public interface ProductsService {

	public ResponseEntity<ResponseDTO> getAllProducts();

	public ResponseEntity<ResponseDTO> createProduct(ProductsDTO productDTO);

	public ResponseEntity<ResponseDTO> getProduct(Long id);

	public ResponseEntity<ResponseDTO> updateProduct(Long id, ProductsDTO productDTO);

	public ResponseEntity<ResponseDTO> deleteProduct(Long id);

}
