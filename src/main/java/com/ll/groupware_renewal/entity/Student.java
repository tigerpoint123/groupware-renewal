package com.ll.groupware_renewal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Student extends User {
@Id
	private int StudentID;
	private String StudentGrade; // 학년
	private String StudentGender; // 성별
	private String StudentColleges; // 단과대학
	private String StudentMajor; // 전공
	private String StudentDoubleMajor; // 복수전공
	private int UserID; // foreign key
	private int WUserID;
	private String WithdrawalDate;

}