package com.ll.groupware_renewal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Inquiry {
	private int Ino;
	private String IBoardSubject;
	private String IBoardWriter;
	private String IBoardContent;
	private String IBoardDate;
	private String IBoardType;
	private String UserEmail;
	private String UserPhoneNum;
	private String State;
	private String IBoardAnswer;
	private int UserID;
	private int IBoardID;

    @Id
    private Long id;
}
