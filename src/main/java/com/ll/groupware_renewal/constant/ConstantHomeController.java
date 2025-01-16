package com.ll.groupware_renewal.constant;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class ConstantHomeController {
    public static final String Home = "/homeView/home";
    public static final String Select = "/signup/signupSelect";
    public static final String Consent = "/signup/infoConsent";
    public static final String Login = "/signin/login";
    public static final String AdminLogin = "/signin/mjuAdminLogin";
    public static final String Denied = "/homeView/accessDenied";
} 