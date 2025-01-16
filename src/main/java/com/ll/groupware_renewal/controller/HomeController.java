package com.ll.groupware_renewal.controller;

import com.ll.groupware_renewal.constant.ConstantHomeController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
public class HomeController {
	private String Select;
	private String Consent;
	private String Login;
	private String Denied;
	
	@RequestMapping(value = "/signupSelect", method = RequestMethod.GET)
	public String signupSelect() {
		this.Select = ConstantHomeController.Select;
		return Select;
	}

	// 정보동의화면
	@RequestMapping(value = "/infoConsent", method = RequestMethod.GET)
	public String infoConsent() {
		this.Consent = ConstantHomeController.Consent;
		return Consent;
	}

	// 사용자 로그인 화면
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login() {
		this.Login = ConstantHomeController.Login;
		return Login;
	}

	// 403 에러
	@RequestMapping(value = "/access_denied")
	public String accessDeniedPage() throws Exception {
		this.Denied = ConstantHomeController.Denied;
		return Denied;
	}

}