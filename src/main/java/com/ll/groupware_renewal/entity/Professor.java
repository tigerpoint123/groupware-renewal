package com.ll.groupware_renewal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Professor extends User {
@Id
	private int ProfessorID;
	private String ProfessorColleges; // 단과대학
	private String ProfessorMajor; // 전공
	private String ProfessorRoom; // 교수실
	private String ProfessorRoomNum; // 교수실 전화번호
	private int UserID; // foreign key
	private int WUserID;
	private String WithdrawalDate;


}
