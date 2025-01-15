package com.ll.groupware_renewal.service;

import com.ll.groupware_renewal.entity.*;
import com.ll.groupware_renewal.repository.SearchJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SearchService {

	@Autowired
	private SearchJpaRepository searchDao;

	@Override
	public List<User> SelectKeyWord(SearchKeyWord searchKeyWord) {
		return searchDao.SelectKeyWord(searchKeyWord);
	}

	@Override
	public Student SelectStudentInfo(int userID) {
		return searchDao.SelectStudentInfo(userID);
	}

	@Override
	public Professor SelectProfessorInfo(int userID) {
		return searchDao.SelectProfessorInfo(userID);
	}

	@Override
	public List<UserReview> SelectUserReview(String userID) {
		return searchDao.SelectUserReview(userID);
	}

}
