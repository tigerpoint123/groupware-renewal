package com.ll.groupware_renewal.repository;

import com.ll.groupware_renewal.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface StudentJpaRepository extends JpaRepository<Student, Integer> {

	// 정보 저장
	void InsertInformation(Student student);

	// 회원가입 후 userID(foreign key) 업데이트
	void UpdateUserID(Student student);

	String getGrade(String userId);

	ArrayList<String> SelectMyPageUserInfo(String mysqlID);

	void UpdateStudentGender(Student student);

	void UpdateStudentDobuleMajor(Student student);

	ArrayList<String> SelectStudentProfileInfo(String userID);

	ArrayList<String> SelectMyPageUserInfoByID(String mysqlID);

	void UpdateStudentGrade(Student student);

	void UpdateStudentColleges(Student student);

	void UpdateStudentMajor(Student student);

	Student SelectStudentInfo(String userID);

	void InsertWithdrawalStudent(Student student);

	void DeleteWithdrawalStudent(Student student);

	void DeleteWithdrawalStudentList(String string);

	void UpdateStudentLoginDate(Student student);

	Student SelectModifyStudentInfo(int userID);

}
