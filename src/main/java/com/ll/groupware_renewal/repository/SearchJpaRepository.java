package com.ll.groupware_renewal.repository;

import com.ll.groupware_renewal.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchJpaRepository extends JpaRepository<User, Integer> {

	List<User> SelectKeyWord(SearchKeyWord searchKeyWord);

	Student SelectStudentInfo(int userID);

	Professor SelectProfessorInfo(int userID);

	List<UserReview> SelectUserReview(String userID);

}
