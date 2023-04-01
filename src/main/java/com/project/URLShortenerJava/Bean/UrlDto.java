package com.project.URLShortenerJava.Bean;

import java.time.LocalDate;

public class UrlDto {
	private String id;
	private String shortUrl;
	private String longUrl;
	private Long clicks;
	private LocalDate createDate;
	private String userId;

	public UrlDto() {
		super();
	}
	
	
	public UrlDto(String shortUrl, String longUrl, Long clicks, String userId) {
		super();
		this.shortUrl = shortUrl;
		this.longUrl = longUrl;
		this.clicks = clicks;
		this.createDate = LocalDate.now();
		this.userId = userId;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	public Long getClicks() {
		return clicks;
	}

	public void setClicks(Long clicks) {
		this.clicks = clicks;
	}

	@Override
	public String toString() {
		return "UrlDto [id=" + id + ", shortUrl=" + shortUrl + ", longUrl=" + longUrl + ", clicks=" + clicks
				+ ", createDate=" + createDate + ", userId=" + userId + "]";
	}

}
