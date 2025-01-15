package com.ll.groupware_renewal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserReview {
	@Id
	private String ReviewID;
	private String Positive;
	private String Contribute;
	private String Respect;
	private String Flexible;
	private String ClassName;
	private String ClassProfessorName;
	private String ReviewDate;
	private String UserID;
	private String WriterUserID;
	private String TeamName;
}
