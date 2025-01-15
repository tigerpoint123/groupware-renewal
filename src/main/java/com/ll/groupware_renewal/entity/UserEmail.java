package com.ll.groupware_renewal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserEmail {
@Id
	private int UserEmailID;
	private String UserEmail;
	private int UserCertificationNum;
	private String UserCertificationTime;

	private int Counter;
	private String From;
	private String Content;
	private String Title;
	private String Date;

}
