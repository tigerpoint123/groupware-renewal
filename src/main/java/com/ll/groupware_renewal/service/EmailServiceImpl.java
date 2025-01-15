package com.ll.groupware_renewal.service;

import com.ll.groupware_renewal.dao.UserDao;
import com.ll.groupware_renewal.dto.User;
import com.ll.groupware_renewal.dto.UserEmail;
import com.ll.groupware_renewal.email.Email;
import com.ll.groupware_renewal.email.EmailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private UserDao emailDao;
	@Autowired
	private EmailImpl emailImpl;
	@Autowired
	private Email email;

	private String Email;
	private int Num;

	@Override
	public int sendEmail(User user) {
		Random RandomNum = new Random();
		this.Num = RandomNum.nextInt(888888) + 111111; // 인증번호 생성
		this.Email = user.getUserEmail();
		System.out.println(Num);
		if (this.Email.equals("@mju.ac.kr")) {
		} else {
			emailImpl.sendEmail(Email, Num);
		}
		return Num;
	}

	@Override
	public boolean AuthNum(String authNum) {// 입력한 인증번호 가져오기
		boolean Checker;
		Checker = emailImpl.AuthNum(Integer.parseInt(authNum), this.Num);
		return Checker;

	}

	// 이메일 중복확인
	@Override
	public boolean SelectForEmailDuplicateCheck(User user) {
		// 이메일 중복
		boolean EmailChecker;
		EmailChecker = emailDao.SelectForEmailDuplicateCheck(user);
		return EmailChecker;
	}

	@Override
	public List<UserEmail> PrintEmailList() {
		return email.printEmailList();
	}

	@Override
	public boolean CheckEmailLogin(String id, String pw) {
		boolean CheckEmailLogin = email.CheckEmailLogin(id, pw);
		return CheckEmailLogin;
	}

	@Override
	public List<UserEmail> GetEmailList() {
		return email.GetEmailList();
	}
}