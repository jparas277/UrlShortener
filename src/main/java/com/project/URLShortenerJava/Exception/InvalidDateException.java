package com.project.URLShortenerJava.Exception;

public class InvalidDateException extends RuntimeException {
	private String message;

	public InvalidDateException(String message) {
		super();
		this.message = message;
	}

	public InvalidDateException() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
