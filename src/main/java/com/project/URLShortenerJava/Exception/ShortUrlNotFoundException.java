package com.project.URLShortenerJava.Exception;

public class ShortUrlNotFoundException extends RuntimeException {
	private String message;

	public ShortUrlNotFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ShortUrlNotFoundException() {
		super();
	}
	

	
}
