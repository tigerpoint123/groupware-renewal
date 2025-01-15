package com.ll.groupware_renewal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Class {
	@Id
	private int ClassID;
	private String ClassName;
	private String ClassType;
	private String ClassProfessorName;
}
