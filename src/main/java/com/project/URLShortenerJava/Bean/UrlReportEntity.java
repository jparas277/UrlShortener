package com.project.URLShortenerJava.Bean;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("UrlReportTable")
public class UrlReportEntity {
	private String id;
	private LocalDate fetchDate;
	private LocalDate createDate;
	private Long clicks;
	private String shortUrl;
	private String userId;

	public UrlReportEntity() {
		super();
	}

	public UrlReportEntity(String id, LocalDate fetchDate, LocalDate createDate, Long clicks, String shortUrl,
			String userId) {
		super();
		this.id = id;
		this.fetchDate = fetchDate;
		this.createDate = createDate;
		this.clicks = clicks;
		this.shortUrl = shortUrl;
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getFetchDate() {
		return fetchDate;
	}

	public void setFetchDate(LocalDate fetchDate) {
		this.fetchDate = fetchDate;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public Long getClicks() {
		return clicks;
	}

	public void setClicks(Long clicks) {
		this.clicks = clicks;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	@Override
	public String toString() {
		return "UrlReportEntity [id=" + id + ", fetchDate=" + fetchDate + ", createDate=" + createDate + ", clicks="
				+ clicks + ", shortUrl=" + shortUrl + "]";
	}
}
