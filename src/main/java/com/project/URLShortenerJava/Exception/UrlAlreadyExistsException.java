package com.project.URLShortenerJava.Exception;

public class UrlAlreadyExistsException extends RuntimeException {
	private String message;
	private String url;
	public UrlAlreadyExistsException(String message, String url) {
		super();
		this.message = message;
		this.url = url;
	}
	public UrlAlreadyExistsException() {
		super();
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
