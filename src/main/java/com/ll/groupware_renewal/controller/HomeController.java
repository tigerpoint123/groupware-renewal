package com.ll.groupware_renewal.controller;

import com.ll.groupware_renewal.config.HomeConfig;
import com.ll.groupware_renewal.constant.ConstantHomeController;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final HomeConfig homeConfig;
    private final String Select;
    private final String Consent;
    private final String Login;
    private final String Denied;

    @RequestMapping(value = "/signupSelect", method = RequestMethod.GET)
    public String signupSelect() {
        return this.homeConfig.getUrls().getSelect();
    }

    // 정보동의화면
    @RequestMapping(value = "/infoConsent", method = RequestMethod.GET)
    public String infoConsent() {
        return this.homeConfig.getUrls().getConsent();
    }

    // 사용자 로그인 화면
    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String login() {
        return this.homeConfig.getUrls().getLogin();
    }

    // 403 에러
    @RequestMapping(value = "/access_denied")
    public String accessDeniedPage() throws Exception {
        return this.homeConfig.getUrls().getDenied();
    }

}