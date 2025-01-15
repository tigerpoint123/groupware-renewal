package com.ll.groupware_renewal.util.email;

import com.ll.groupware_renewal.entity.UserEmail;

import java.util.List;

public interface Email {

	public void sendEmail(String email, int Num);

	public boolean AuthNum(int authnum, int num);

	public List<UserEmail> printEmailList();

	public boolean CheckEmailLogin(String id, String pw);

	public List<UserEmail> GetEmailList();
}
