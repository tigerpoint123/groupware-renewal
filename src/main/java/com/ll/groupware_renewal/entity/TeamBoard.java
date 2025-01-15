package com.ll.groupware_renewal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TeamBoard {

	// 게시판정보
	@Id
	private int TBoardID;
	private String TBoardSubject;
	private String TBoardContent;
	private String TBoardWriter;
	private String TBoardDate;
	private String TUserLoginID;

	// 파일정보
	private int TBno;
	private String TFileID;
	private String TOriginalFileName;
	private String TStoredFileName;
	private String TFileSize;
	private String TeamID;
	
	private int TUserID;

}
