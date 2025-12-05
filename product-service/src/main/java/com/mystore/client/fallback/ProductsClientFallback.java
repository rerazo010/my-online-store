package com.mystore.client.fallback;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.mystore.client.FakeStoreClient;
import com.mystore.dto.ProductsDTO;

import feign.FeignException;
import feign.hystrix.FallbackFactory;

@Component
public class ProductsClientFallback implements FallbackFactory<FakeStoreClient> {

	private static final Logger log = LoggerFactory.getLogger(ProductsClientFallback.class);

	@Override
	public FakeStoreClient create(Throwable cause) {

		return new FakeStoreClient() {
			@Override
			public List<ProductsDTO> getAllProducts() {
				validateException();
				return Collections.emptyList();
			}

			@Override
			public ProductsDTO createProduct(ProductsDTO productDTO) {
				validateException();
				return null;
			}

			@Override
			public ProductsDTO getProduct(Long id) {
				validateException();
				return null;
			}

			@Override
			public ProductsDTO updateProduct(Long id, ProductsDTO productDTO) {
				validateException();
				return null;
			}

			@Override
			public ProductsDTO deleteProduct(Long id) {
				validateException();
				return null;
			}

			
			private void validateException() {
				if (cause instanceof FeignException) {
					int status = ((FeignException) cause).status();
					log.error("Error getting data: status code {}", status);
				} else {
					log.error("Unexpected error when retrieving data", cause);
				}
			};
			

		};
	}

}
