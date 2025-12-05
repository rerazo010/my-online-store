package com.mystore.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mystore.dto.ResponseDTO;

/**
 * A factory for creating Response objects.
 */
public class ResponseFactory {

	private ResponseFactory() {
		throw new IllegalStateException("ResponseFactory class");
	}

	/**
	 * Response ok.
	 *
	 * @param response the response
	 * @return the response entity
	 */
	public static ResponseEntity<ResponseDTO> responseOk(Object response) {
		return ResponseEntity.ok(new ResponseDTO("Successfully Responded.", null, response));
	}

	public static ResponseEntity<ResponseDTO> responseOk() {
		return ResponseEntity.ok(new ResponseDTO("Successfully Responded.", null, null));
	}

	public static ResponseEntity<ResponseDTO> responseOk(String message, Object response) {
		return ResponseEntity.ok(new ResponseDTO(message, null, response));
	}

	/**
	 * Internal error.
	 *
	 * @param e the e
	 * @return the response entity
	 */
	public static ResponseEntity<ResponseDTO> internalError(Throwable e) {
		if (e instanceof IllegalStateException) {
			return new ResponseEntity<>(new ResponseDTO("An internal error occurred. IllegalState", e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} else if (e instanceof NullPointerException) {
			return new ResponseEntity<>(
					new ResponseDTO("An internal error occurred. - NullPointer", e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<>(new ResponseDTO("An internal error occurred. - Default", e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
