package com.project.URLShortenerJava.Exception;

public class NoDataExistsException extends RuntimeException {
	private String message;

	public NoDataExistsException(String message) {
		super();
		this.message = message;
	}

	public NoDataExistsException() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
