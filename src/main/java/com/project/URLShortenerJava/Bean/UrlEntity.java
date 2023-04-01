package com.project.URLShortenerJava.Bean;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "UrlTable")
public class UrlEntity {
	@MongoId
	private String id;
	private String shortUrl;
	private String longUrl;
	private Long clicks;
	private LocalDate createDate;
	private String userId;

	public UrlEntity() {
		super();
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public UrlEntity(String id, String shortUrl, String longUrl, Long clicks, LocalDate createDate, String userId) {
		super();
		this.id = id;
		this.shortUrl = shortUrl;
		this.longUrl = longUrl;
		this.clicks = clicks;
		this.createDate = createDate;
		this.userId = userId;
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
		return "UrlEntity [id=" + id + ", shortUrl=" + shortUrl + ", longUrl=" + longUrl + ", clicks=" + clicks
				+ ", createDate=" + createDate + ", userId=" + userId + "]";
	}

}
