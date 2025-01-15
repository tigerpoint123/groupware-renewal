package com.ll.groupware_renewal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TeamUser {
	@Id
	private int UserID;
	private int TeamID;
	private String UserLoginID;
	private String UserName;
}
