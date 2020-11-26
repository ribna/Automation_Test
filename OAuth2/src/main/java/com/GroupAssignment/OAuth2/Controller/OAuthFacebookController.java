package com.GroupAssignment.OAuth2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.GroupAssignment.OAuth2.Model.LoginUserInfo;
import com.GroupAssignment.OAuth2.Service.OAuthFacebookService;

@Controller
public class OAuthFacebookController {
	
	@Autowired private OAuthFacebookService oAuthFacebookService;
	
	@GetMapping(value = "/facebookloginOAuth")
	public RedirectView OAuthFacebookLogin() {
		
		RedirectView redirectView = new RedirectView();
		String url = oAuthFacebookService.OAuthFacebookLogin();
		System.out.println(url);
		redirectView.setUrl(url);
		return redirectView;
	}
	
	@GetMapping("/facebookApp")
	public String facebookApp(@RequestParam("code") String code) {
		String accessToken = oAuthFacebookService.getFacebookOAuthAccessToken(code);
		return "redirect:/facebookProfileData/"+accessToken;
	}
		
	
	@GetMapping(value = "/facebookProfileData/{accessToken:.+}")
	public String facebookProfileData(@PathVariable String accessToken, Model model) {
		User user = oAuthFacebookService.getFacebookUserProfile(accessToken);
	    System.out.println(user);
	//	LoginUserInfo loginUserInfo = new LoginUserInfo(user.getFirstName(), user.getLastName(), user.getCover().getSource());
       // this line getting data from facebook but it error because facebook policy 
	  //  LoginUserInfo loginUserInfo = new LoginUserInfo(user.getLastName(), ((Object) user).getPhotoUrl() != null ? user.getPhotoUrl().toString() : null);
	    
	   LoginUserInfo loginUserInfo = new LoginUserInfo("praneeth","nishada","weerawardana"); 
	    model.addAttribute("user", loginUserInfo);
		return "view/UserProfile";
		
	}

}
