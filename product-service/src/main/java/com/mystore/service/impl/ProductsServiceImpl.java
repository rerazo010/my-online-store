package com.mystore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.mystore.client.FakeStoreClient;
import com.mystore.dto.ProductsDTO;
import com.mystore.dto.ResponseDTO;
import com.mystore.service.ProductsService;
import com.mystore.util.ResponseFactory;

@Service
public class ProductsServiceImpl implements ProductsService {

	@Autowired
	FakeStoreClient productsClient;

	@Override
	public ResponseEntity<ResponseDTO> getAllProducts() {
		return ResponseFactory.responseOk(productsClient.getAllProducts());
	}

	@Override
	public ResponseEntity<ResponseDTO> createProduct(ProductsDTO productDTO) {
		return ResponseFactory.responseOk(productsClient.createProduct(productDTO));
	}

	@Override
	public ResponseEntity<ResponseDTO> getProduct(Long id) {
		return ResponseFactory.responseOk(productsClient.getProduct(id));
	}

	@Override
	public ResponseEntity<ResponseDTO> updateProduct(Long id, ProductsDTO productDTO) {
		return ResponseFactory.responseOk(productsClient.updateProduct(id,productDTO));
	}

	@Override
	public ResponseEntity<ResponseDTO> deleteProduct(Long id) {
		return ResponseFactory.responseOk(productsClient.deleteProduct(id));
	}

}
