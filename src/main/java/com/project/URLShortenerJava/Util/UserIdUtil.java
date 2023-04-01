package com.project.URLShortenerJava.Util;

import java.util.UUID;

public class UserIdUtil {
	
	public static String gen() {
		return UUID.randomUUID().toString();
	}
}
