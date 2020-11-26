package com.GroupAssignment.OAuth2.ServiceImpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;

import com.GroupAssignment.OAuth2.Service.OAuthGoogleService;

@Service
public class OAuthGoogleServiceImpl implements OAuthGoogleService {
	@Value("${spring.social.google.app-id}")
	private String googleId;
	@Value("${spring.social.google.app-secret}")
	private String googleSecret;
	
	private GoogleConnectionFactory CreateOAuthGoogleConnection() {
		return new GoogleConnectionFactory(googleId, googleSecret);
	}

	@Override
	public String OAuthGoogleLogin() {
		OAuth2Parameters parameters = new OAuth2Parameters();
		parameters.setRedirectUri("http://localhost:3000/google");
		parameters.setScope("profile");
		return CreateOAuthGoogleConnection().getOAuthOperations().buildAuthenticateUrl(parameters);
	
	}

	@Override
	public String getGoogleOAuthAccessToken(String code) {
		return CreateOAuthGoogleConnection().getOAuthOperations().exchangeForAccess(code, "http://localhost:3000/google", null).getAccessToken();
		
	}

	@Override
	public Person getGoogleUserProfile(String accessToken) {
		Google google = new GoogleTemplate(accessToken);
		Person person = google.plusOperations().getGoogleProfile();
		return person;
	}
	
	
	
}
