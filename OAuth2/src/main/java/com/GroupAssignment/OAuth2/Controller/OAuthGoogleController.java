package com.GroupAssignment.OAuth2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.api.plus.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.GroupAssignment.OAuth2.Model.LoginUserInfo;
import com.GroupAssignment.OAuth2.Service.OAuthGoogleService;

@Controller
public class OAuthGoogleController {

	@Autowired OAuthGoogleService oAuthGoogleService;
	
	@GetMapping(value = "/googleloginOAuth")
	public RedirectView OAuthGoogleLogin() {
		
		RedirectView redirectView = new RedirectView();
		String url = oAuthGoogleService.OAuthGoogleLogin();
		System.out.println(url);
		redirectView.setUrl(url);
		return redirectView;
	}
	
	@GetMapping("/google")
	public String google(@RequestParam("code") String code) {
		String accessToken = oAuthGoogleService.getGoogleOAuthAccessToken(code);
		return "redirect:/googleProfileData/"+accessToken;
	}
		
	
	@GetMapping(value = "/googleProfileData/{accessToken:.+}")
	public String googleProfileData(@PathVariable String accessToken, Model model) {
		Person user = oAuthGoogleService.getGoogleUserProfile(accessToken);
	    System.out.println(user);
		LoginUserInfo loginUserInfo = new LoginUserInfo(user.getGivenName(), user.getFamilyName(), user.getImageUrl());
        
	 
	//    LoginUserInfo loginUserInfo = new LoginUserInfo("praneeth","nishada","weerawardana"); 
	    model.addAttribute("user", loginUserInfo);
		return "view/UserProfile";
		
	}
}
