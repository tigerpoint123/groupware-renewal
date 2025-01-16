package com.ll.groupware_renewal.util.email;

import com.ll.groupware_renewal.entity.UserEmail;

import java.util.List;

public interface Email {

    void sendEmail(String email, int Num);

    boolean AuthNum(int authnum, int num);

    List<UserEmail> printEmailList();

    boolean CheckEmailLogin(String id, String pw);

    List<UserEmail> GetEmailList();
}
