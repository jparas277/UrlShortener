package com.project.URLShortenerJava.Bean;

import java.time.LocalDate;

public class RequestDate {
	private LocalDate date;

	public RequestDate(LocalDate date) {
		super();
		this.date = date;
	}

	public RequestDate() {
		super();
	}

	@Override
	public String toString() {
		return "RequestDate [date=" + date + "]";
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
}
