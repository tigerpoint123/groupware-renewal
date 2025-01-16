package com.ll.groupware_renewal.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@RequiredArgsConstructor
@Component
public class ConstantHomeController {

	private String Home;
	private String Select;
	private String Consent;
	private String Login;
	private String AdminLogin;
	private String Denied;
}
