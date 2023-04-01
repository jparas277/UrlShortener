package com.project.URLShortenerJava.Util;

import org.springframework.beans.BeanUtils;

import com.project.URLShortenerJava.Bean.UrlDto;
import com.project.URLShortenerJava.Bean.UrlEntity;
import com.project.URLShortenerJava.Bean.UrlReportDto;
import com.project.URLShortenerJava.Bean.UrlReportEntity;
import com.project.URLShortenerJava.Bean.UserDto;
import com.project.URLShortenerJava.Bean.UserEntity;

public class AppUtil {
	public static UrlDto urlEntityToDto(UrlEntity entity) {
		UrlDto dto = new UrlDto();
		BeanUtils.copyProperties(entity,dto);
		return dto;
	}
	
	public static UrlEntity urlDtoToEntity(UrlDto dto) {
		UrlEntity entity = new UrlEntity();
		BeanUtils.copyProperties(dto,entity);
		return entity;
	}
	public static UrlReportDto urlReportEntityToDto(UrlReportEntity entity) {
		UrlReportDto dto = new UrlReportDto();
		BeanUtils.copyProperties(entity,dto);
		return dto;
	}
	
	public static UrlReportEntity urlReportDtoToEntity(UrlReportDto dto) {
		UrlReportEntity entity = new UrlReportEntity();
		BeanUtils.copyProperties(dto,entity);
		return entity;
	}
	
	public static UserDto userEntityToDto(UserEntity entity) {
		UserDto dto = new UserDto();
		BeanUtils.copyProperties(entity,dto);
		return dto;
	}
	
	public static UserEntity userDtoToEntity(UserDto dto) {
		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(dto,entity);
		return entity;
	}
	
	
}
