package com.ll.groupware_renewal.service;

import com.ll.groupware_renewal.dao.SearchDao;
import com.ll.groupware_renewal.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearchDao searchDao;

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
