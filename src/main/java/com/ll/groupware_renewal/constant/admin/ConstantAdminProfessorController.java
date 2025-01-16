package com.ll.groupware_renewal.constant.admin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@RequiredArgsConstructor
@Component
public class ConstantAdminProfessorController {
	private String UserName;
	private String UserLoginID;
	private String UserPhoneNum;
	private String UserPhone;
	private String UserEmail;
	private String UserMajor;
	private String UserNameForOpen;
	private String Email;
	
	private String RSignupProfessor;
	private String RMyPageProfessor;
	private String RModifyProfessor;

}
