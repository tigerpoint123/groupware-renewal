package com.ll.groupware_renewal.service;

import com.ll.groupware_renewal.entity.*;
import com.ll.groupware_renewal.repository.SearchJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
	private final SearchJpaRepository searchJpaRepository;

	public List<User> SelectKeyWord(SearchKeyWord searchKeyWord) {
		return searchJpaRepository.SelectKeyWord(searchKeyWord);
	}

	public Student SelectStudentInfo(int userID) {
		return searchJpaRepository.SelectStudentInfo(userID);
	}

	public Professor SelectProfessorInfo(int userID) {
		return searchJpaRepository.SelectProfessorInfo(userID);
	}

	public List<UserReview> SelectUserReview(String userID) {
		return searchJpaRepository.SelectUserReview(userID);
	}

}
