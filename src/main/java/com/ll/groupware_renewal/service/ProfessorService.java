package com.ll.groupware_renewal.service;

import com.ll.groupware_renewal.entity.Professor;
import com.ll.groupware_renewal.repository.ProfessorJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProfessorService {
	private final ProfessorJpaRepository professorDao;

	public void InsertInformation(Professor professor) {
		professorDao.InsertInformation(professor);
	}

	public void UpdateUserID(Professor professor) {
		professorDao.UpdateUserID(professor);
	}

	public void UpdateProfessorColleges(Professor professor) {
		professorDao.UpdateProfessorColleges(professor);
	}

	public void UpdateProfessorMajor(Professor professor) {
		professorDao.UpdateProfessorMajor(professor);
	}

	public void UpdateProfessorRoom(Professor professor) {
		professorDao.UpdateProfessorRoom(professor);
	}

	public void UpdateProfessorRoomNum(Professor professor) {
		professorDao.UpdateProfessorRoomNum(professor);
	}

	public ArrayList<String> SelectProfessorProfileInfo(String userID) {
		ArrayList<String> ProfessorInfo = new ArrayList<String>();
		ProfessorInfo = professorDao.SelectProfessorProfileInfo(userID);
		return ProfessorInfo;
	}

	public Professor SelectProfessorInfo(String userID) {
		return professorDao.SelectProfessorInfo(userID);
	}

	public void InsertWithdrawalProfessor(Professor professor) {
		professorDao.InsertWithdrawalprofessor(professor);
	}

	public void DeleteWithdrawalProfessor(Professor professor) {
		professorDao.DeleteWithdrawalprofessor(professor);
	}

	public void DeleteWithdrawalProfessorList(String string) {
		professorDao.DeleteWithdrawalprofessorList(string);
	}

	public void UpdateProfessorLoginDate(Professor professor) {
		professorDao.UpdateProfessorLoginDate(professor);
	}

	public Professor SelectModifyProfessorInfo(int userID) {
		return professorDao.SelectModifyProfessorInfo(userID);
	}
}
