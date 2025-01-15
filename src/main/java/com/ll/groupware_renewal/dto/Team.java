package com.ll.groupware_renewal.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@ManyToOne
	private Class classInfo;
	
	private String TeamName;
    private String TeamLeaderName;
    private String TeamCreationDate;
    private String TeamLeaderID;
    private int ClassID;
    private int TeamID;
}
