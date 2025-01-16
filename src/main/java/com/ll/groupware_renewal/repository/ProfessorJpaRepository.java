package com.ll.groupware_renewal.repository;

import com.ll.groupware_renewal.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ProfessorJpaRepository extends JpaRepository<Professor, Integer> {

	// 정보 저장
	public void InsertInformation(Professor professor);

	// 회원가입 후 userID(foreign key) 업데이트
	public void UpdateUserID(Professor professor);
	
	public ArrayList<String> SelectMyPageUserInfo(String string);

	public void UpdateProfessorColleges(Professor professor);

	public void UpdateProfessorMajor(Professor professor);
	
	public void UpdateProfessorRoom(Professor professor);

	public void UpdateProfessorRoomNum(Professor professor);

	public ArrayList<String> SelectProfessorProfileInfo(String userID);
	
	public ArrayList<String> SelectMyPageUserInfoByID(String mysqlID);

	public Professor SelectProfessorInfo(String userID);

	public void InsertWithdrawalprofessor(Professor professor);

	public void DeleteWithdrawalprofessor(Professor professor);

	public void DeleteWithdrawalprofessorList(String string);

	public void UpdateProfessorLoginDate(Professor professor);

	public Professor SelectModifyProfessorInfo(int userID);

}
