package com.ll.groupware_renewal.dao;

import com.ll.groupware_renewal.dto.*;

import java.util.List;

public interface SearchDao {

	List<User> SelectKeyWord(SearchKeyWord searchKeyWord);

	Student SelectStudentInfo(int userID);

	Professor SelectProfessorInfo(int userID);

	List<UserReview> SelectUserReview(String userID);

}
