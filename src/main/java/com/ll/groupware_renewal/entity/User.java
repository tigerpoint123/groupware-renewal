package com.ll.groupware_renewal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
@Id
	private int UserID;
	private String UserName; // 이름
	private String UserPhoneNum; // 핸드폰 번호
	private String UserEmail; // 이메일
	private String UserLoginID; // 로그인 ID
	private String UserLoginPwd; // 로그인 Pwd
	private String UserModifiedPW; // ? // PW?Pwd?
	private String UserRole; // 'STUDENT', 'PROFESSOR', 'ADMINISTRATOR'
	private String Authority; // ROLE_USER, ROLE_ADMIN
	private boolean Enabled; // true : 계쩡 활성화, false : 비활성화
	private boolean Withdrawal; 
	private boolean Dormant;
	private String Date;
	private String OpenName;
	private String OpenEmail;
	private String OpenPhoneNum;
	private String OpenMajor;
	private String OpenGrade;

}