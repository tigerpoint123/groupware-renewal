package com.ll.groupware_renewal.service;

import com.ll.groupware_renewal.entity.User;
import com.ll.groupware_renewal.entity.UserEmail;
import com.ll.groupware_renewal.repository.UserJpaRepository;
import com.ll.groupware_renewal.util.email.Email;
import com.ll.groupware_renewal.util.email.EmailImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {
	private final UserJpaRepository emailDao;
	private final EmailImpl emailImpl;
	private final Email email;

	private String Email;
	private int Num;

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

	public boolean AuthNum(String authNum) {// 입력한 인증번호 가져오기
		boolean Checker;
		Checker = emailImpl.AuthNum(Integer.parseInt(authNum), this.Num);
		return Checker;

	}

	// 이메일 중복확인
	public boolean SelectForEmailDuplicateCheck(User user) {
		// 이메일 중복
		boolean EmailChecker;
		EmailChecker = emailDao.SelectForEmailDuplicateCheck(user);
		return EmailChecker;
	}

	public List<UserEmail> PrintEmailList() {
		return email.printEmailList();
	}

	public boolean CheckEmailLogin(String id, String pw) {
		boolean CheckEmailLogin = email.CheckEmailLogin(id, pw);
		return CheckEmailLogin;
	}

	public List<UserEmail> GetEmailList() {
		return email.GetEmailList();
	}
}
