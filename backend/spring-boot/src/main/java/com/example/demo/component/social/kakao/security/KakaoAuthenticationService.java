package com.example.demo.component.social.kakao.security;

import com.example.demo.component.social.kakao.api.Kakao;
import com.example.demo.component.social.kakao.connect.KakaoConnectionFactory;
import org.springframework.social.security.provider.OAuth2AuthenticationService;

public class KakaoAuthenticationService extends OAuth2AuthenticationService<Kakao> {
	public KakaoAuthenticationService(String clientId) {
		super(new KakaoConnectionFactory(clientId));
	}
}
