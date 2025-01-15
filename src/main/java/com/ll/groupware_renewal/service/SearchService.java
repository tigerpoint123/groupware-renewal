package com.ll.groupware_renewal.service;

import com.ll.groupware_renewal.entity.*;

import java.util.List;

public interface SearchService {

	public List<User> SelectKeyWord(SearchKeyWord searchKeyWord);

	public Student SelectStudentInfo(int i);

	public Professor SelectProfessorInfo(int userID);

	public List<UserReview> SelectUserReview(String userID);

}
