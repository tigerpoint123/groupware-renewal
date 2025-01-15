package com.ll.groupware_renewal.service;

import com.ll.groupware_renewal.dto.User;
import com.ll.groupware_renewal.dto.UserEmail;

import java.util.List;

public interface EmailService {
	// 이메일 보내기
	public int sendEmail(User user);

	// 인증번호확인
	public boolean AuthNum(String authNum);

	// 이메일중복확인
	public boolean SelectForEmailDuplicateCheck(User user);

	public List<UserEmail> PrintEmailList();

	public boolean CheckEmailLogin(String id, String pw);

	public List<UserEmail> GetEmailList();
}
