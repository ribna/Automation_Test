package com.GroupAssignment.OAuth2.Service;

import org.springframework.social.google.api.plus.Person;


public interface OAuthGoogleService{

	String OAuthGoogleLogin();

	String getGoogleOAuthAccessToken(String code);

	Person getGoogleUserProfile(String accessToken);

}
