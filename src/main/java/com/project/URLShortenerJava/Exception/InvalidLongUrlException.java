package com.project.URLShortenerJava.Exception;

public class InvalidLongUrlException extends RuntimeException {
	private String message;

	public InvalidLongUrlException(String message) {
		super(message);
	}

	public InvalidLongUrlException() {
		super();
	}
	
}
