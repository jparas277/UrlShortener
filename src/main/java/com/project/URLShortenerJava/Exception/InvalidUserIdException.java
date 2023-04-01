package com.project.URLShortenerJava.Exception;

public class InvalidUserIdException extends RuntimeException {
	private String message;

	public InvalidUserIdException(String message) {
		super();
		this.message = message;
	}

	public InvalidUserIdException() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
