package com.project.URLShortenerJava.Bean;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("UserTable")
public class UserEntity {
	@MongoId
	private String id;
	private String userId;

	
	public UserEntity(String id, String userId) {
		super();
		this.id = id;
		this.userId = userId;
	}

	public UserEntity() {
		super();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserEntity [userId=" + userId + "]";
	}

}
