package com.cos.blog.model;

import lombok.Data;

@Data	// getter, setter 자동 생성
public class OAuthToken {
	private String access_token;
	private String token_type;
	private String refresh_token;
	private int expires_in;
	private String scope;
	private int refresh_token_expires_in;
}
