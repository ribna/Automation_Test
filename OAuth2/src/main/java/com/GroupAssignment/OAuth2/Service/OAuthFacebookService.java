package com.GroupAssignment.OAuth2.Service;

import org.springframework.social.facebook.api.User;

public interface OAuthFacebookService {

	String OAuthFacebookLogin();

	String getFacebookOAuthAccessToken(String code);

	User getFacebookUserProfile(String accessToken);

}
