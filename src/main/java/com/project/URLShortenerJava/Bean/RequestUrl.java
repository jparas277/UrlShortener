package com.project.URLShortenerJava.Bean;

import java.util.Optional;

public class RequestUrl {
	private String url;
	private Optional<String> userId;
	
	public RequestUrl(String url, Optional<String> userId) {
		super();
		this.url = url;
		this.userId = userId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Optional<String> getUserId() {
		return userId;
	}

	public void setUserId(Optional<String> userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "RequestUrl [url=" + url + ", userId=" + userId + "]";
	}
	
}
