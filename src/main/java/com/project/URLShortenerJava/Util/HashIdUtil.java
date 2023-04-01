package com.project.URLShortenerJava.Util;

import java.math.BigInteger;
import java.util.UUID;

import org.hashids.Hashids;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HashIdUtil {

	private static final Hashids HASHIDS = new Hashids("salt",6);
	
	Logger logger = LoggerFactory.getLogger(HashIdUtil.class);
	
	public static String encode() {
		return HASHIDS.encode(Long.parseLong(String
				.format("%040d", new BigInteger(UUID.randomUUID()
						.toString()
						.replace("-", ""), 16))
				.substring(0,16)));
    }
	
}
