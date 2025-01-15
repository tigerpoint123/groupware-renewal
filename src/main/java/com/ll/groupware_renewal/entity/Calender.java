package com.ll.groupware_renewal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Calender {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String title;
	private String description;
	private String start;
	private String end;
	private String backgroundColor;
	private int userId;
	private boolean allDay;
}
