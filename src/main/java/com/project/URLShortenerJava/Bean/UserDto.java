package com.project.URLShortenerJava.Bean;

public class UserDto {
	private String url;
	private String userId;
	public UserDto(String url, String userId) {
		super();
		this.url = url;
		this.userId = userId;
	}
	public UserDto() {
		super();
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "UserDto [url=" + url + ", userId=" + userId + "]";
	}
	
}
