package com.mystore.util;

import java.io.Serializable;

public class SuccessResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;

	private Object response;

	public SuccessResponse(String message, Object response) {
		this.setMessage(message);
		this.setResponse(response);
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "SuccessResponse [message=" + message + ", response=" + response + "]";
	}

}
