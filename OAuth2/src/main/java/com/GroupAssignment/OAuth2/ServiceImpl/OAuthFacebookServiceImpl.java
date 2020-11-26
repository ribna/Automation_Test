package com.GroupAssignment.OAuth2.ServiceImpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;

import com.GroupAssignment.OAuth2.Service.OAuthFacebookService;

@Service
public class OAuthFacebookServiceImpl implements OAuthFacebookService{

	@Value("${spring.social.facebook.app-id}")
	private String facebookId;
	@Value("${spring.social.facebook.app-secret}")
	private String facebookSecret;
	
	private FacebookConnectionFactory CreateOAuthFacebookConnection() {
		return new FacebookConnectionFactory(facebookId, facebookSecret);
	}
	
	
	
	@Override
	public String OAuthFacebookLogin() {
		OAuth2Parameters parameters = new OAuth2Parameters();
		parameters.setRedirectUri("http://localhost:3000/facebookApp");
		parameters.setScope("public_profile,email,user_photos");
		return CreateOAuthFacebookConnection().getOAuthOperations().buildAuthenticateUrl(parameters);
	}

	@Override
	public String getFacebookOAuthAccessToken(String code) {
		return CreateOAuthFacebookConnection().getOAuthOperations().exchangeForAccess(code, "http://localhost:3000/facebookApp", null).getAccessToken();
	}

	@Override
	public User getFacebookUserProfile(String accessToken) {
		Facebook facebook = new FacebookTemplate(accessToken);
		String[] values = {"id","first_name","last_name","cover","email"}; 
				return facebook.fetchObject("me", User.class, values);
	}

	
}
