package com.ll.groupware_renewal.service;

import com.ll.groupware_renewal.entity.Student;
import com.ll.groupware_renewal.repository.StudentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class StudentService {
	private final StudentJpaRepository studentDao;

	public void InsertInformation(Student student) {
		studentDao.InsertInformation(student);
	}
	public void UpdateUserID(Student student) {
		studentDao.UpdateUserID(student);
	}

	public void updateStudentGender(Student student) {
		studentDao.UpdateStudentGender(student);
	}

	public void updateStudentGrade(Student student) {
		studentDao.UpdateStudentGrade(student);
	}

	public void UpdateStudentDobuleMajor(Student student) {
		studentDao.UpdateStudentDobuleMajor(student);
	}

	public ArrayList<String> SelectStudentProfileInfo(String userID) {
		ArrayList<String> StudentInfo = new ArrayList<String>();
		StudentInfo = studentDao.SelectStudentProfileInfo(userID);
		return StudentInfo;
	}

	public void UpdateStudentGender(Student student) {
		studentDao.UpdateStudentGender(student);
	}

	public void UpdateStudentColleges(Student student) {
		studentDao.UpdateStudentColleges(student);
	}

	public void UpdateStudentMajor(Student student) {
		studentDao.UpdateStudentMajor(student);
	}

	public Student SelectStudentInfo(String userID) {
		return studentDao.SelectStudentInfo(userID);
	}

	public void InsertWithdrawalStudent(Student student) {
		studentDao.InsertWithdrawalStudent(student);
	}

	public void DeleteWithdrawalStudent(Student student) {
		studentDao.DeleteWithdrawalStudent(student);
	}

	public void DeleteWithdrawalStudentList(String string) {
		studentDao.DeleteWithdrawalStudentList(string);
	}

	public void UpdateStudentLoginDate(Student student) {
		studentDao.UpdateStudentLoginDate(student);
	}

	public Student SelectModifyStudentInfo(int userID) {
		return studentDao.SelectModifyStudentInfo(userID);
	}
}
