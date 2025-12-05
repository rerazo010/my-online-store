package com.mystore.dto;


public class ResponseDTO  {


	private String message;

	private Object errors;

	private Object response;


	public ResponseDTO() {
		super();
	}

	public ResponseDTO(String message, Object errors, Object response) {
		super();
		this.message = message;
		this.errors = errors;
		this.response = response;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}



	public Object getErrors() {
		return errors;
	}

	public void setErrors(Object errors) {
		this.errors = errors;
	}


}
