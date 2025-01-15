package com.ll.groupware_renewal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MergeTeam {
@Id
	private String TeamID;
	private String ClassName;
	private String ClassProfessorName;
	private String TeamName;

}
