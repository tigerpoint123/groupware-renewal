package com.ll.groupware_renewal.repository;

import com.ll.groupware_renewal.entity.Professor;
import com.ll.groupware_renewal.entity.Student;
import com.ll.groupware_renewal.entity.User;
import com.ll.groupware_renewal.entity.UserInfoOpen;
import com.ll.groupware_renewal.security.UsersDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface UserJpaRepository extends JpaRepository<User, Long> {

	// 회원가입
	void InsertForSignUp(User user);

	// 로그인
	UsersDetails selectByLoginID(String userLoginID);

	// 중복 확인
	boolean SelctForIDConfirm(User user);

	// 비번 찾기
	boolean SelectPwdForConfirmToFindPwd(User user);

	// userID(다른 테이블들의 foreign key) 찾기
	int SelectUserID(Student student);
	int SelectUserID(Professor professor);

	// 비번 보여주기
	boolean SelectForShowPassword(User user);

	boolean SelectForEmailDuplicateCheck(User user);

	void UpdateLoginDate(User user);

	String SelectCurrentPwd(String id);

	void UpdatePwd(User user);

	boolean SelectForPwdCheckBeforeModify(String pw, String pw2);

	ArrayList<String> SelectMyPageUserInfo(String userId);

	ArrayList<String> SelectUserProfileInfo(String id);

	void updateUserPhoneNumber(User user);

	void updateUserMajor(User user);

	void updateUserColleges(User user);

	ArrayList<String> SelectUserInformation(String userId);

	void UpdateTemporaryPwd(User user);

	void UpdateDoWithdrawalRecoveryByAdmin(String ajaxMsg);

	void UpdateDormantOneToZero(String id);

	void UpdateUserRole(String id, String optionValue);

	void UpdateAdminRole(String id, String optionValue);

	ArrayList<String> SelectMyPageUserInfoByID(String mysqlID);

	void UpdateUserName(User user);

	void UpdatePhoneNum(User user);

	void UpdateOpenGrade(User user);

	User SelectUserInfo(String userLoginID);

	List<UserInfoOpen> SelectOpenInfo(String userID);

	int SelectUserIDFromBoardController(String userLoginID);

	String SelectUserRole(String userLoginID);

	String SelectUserName(String userLoginID);

	void UpdateWithdrawalUser(User user);

	void UpdateRecoveryWithdrawal(User user);

	void UpdateWithdrawalByDormant(String ajxMsg);

	boolean SelectDormant(String loginID);

	void UpdateRecoveryDormant(String loginID);

	String SelectWriter(String userLoginID);

	String SelectUserIDForDocument(String userLoginID);

	String SelectTWriterID(String tWriter);

	String SelectUserIDForMyBoard(String loginID);

	String SelectEmailForInquiry(String userLoginID);

	String SelectPhoneNumForInquiry(String userLoginID);

	String SelectUserIDForDate(String loginID);

	String SelectIDForReview(String userLoginID);

	User SelectModifyUserInfo(String loginID);
}