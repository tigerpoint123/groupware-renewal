package com.ll.groupware_renewal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Board {
	private String BFileID;
	private int Bno;
	private String BoardSubject;
	private String BoardWriter;
	private String BoardContent;
	private String BoardDate;
	private String BoardType;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String boardType;
	private int hitCount;
	
	@OneToMany(mappedBy = "board")
	private List<FileInfo> files = new ArrayList<>();
	
	public void increaseHitCount() {
		this.hitCount++;
	}
}
