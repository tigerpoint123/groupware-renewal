package com.ll.groupware_renewal.dao;

import com.ll.groupware_renewal.dto.Professor;
import com.ll.groupware_renewal.dto.Student;
import com.ll.groupware_renewal.dto.User;
import com.ll.groupware_renewal.dto.UserInfoOpen;
import com.ll.groupware_renewal.security.UsersDetails;

import java.util.ArrayList;
import java.util.List;

public interface UserDao {

	// 회원가입
	public void InsertForSignUp(User user);

	// 로그인
	public UsersDetails selectByLoginID(String userLoginID);

	// 중복 확인
	public boolean SelctForIDConfirm(User user);

	// 비번 찾기
	public boolean SelectPwdForConfirmToFindPwd(User user);

	// userID(다른 테이블들의 foreign key) 찾기
	public int SelectUserID(Student student);
	public int SelectUserID(Professor professor);

	// 비번 보여주기
	public boolean SelectForShowPassword(User user);

	public boolean SelectForEmailDuplicateCheck(User user);

	public void UpdateLoginDate(User user);

	public String SelectCurrentPwd(String id);

	public void UpdatePwd(User user);

	public boolean SelectForPwdCheckBeforeModify(String pw, String pw2);

	public ArrayList<String> SelectMyPageUserInfo(String userId);

	public ArrayList<String> SelectUserProfileInfo(String id);

	public void updateUserPhoneNumber(User user);

	public void updateUserMajor(User user);

	public void updateUserColleges(User user);

	public ArrayList<String> SelectUserInformation(String userId);

	public void UpdateTemporaryPwd(User user);

	public void UpdateDoWithdrawalRecoveryByAdmin(String ajaxMsg);

	public void UpdateDormantOneToZero(String id);

	public void UpdateUserRole(String id, String optionValue);

	public void UpdateAdminRole(String id, String optionValue);

	public ArrayList<String> SelectMyPageUserInfoByID(String mysqlID);

	public void UpdateUserName(User user);

	public void UpdatePhoneNum(User user);

	public void UpdateOpenGrade(User user);

	public User SelectUserInfo(String userLoginID);

	public List<UserInfoOpen> SelectOpenInfo(String userID);

	public int SelectUserIDFromBoardController(String userLoginID);

	public String SelectUserRole(String userLoginID);

	public String SelectUserName(String userLoginID);

	public void UpdateWithdrawalUser(User user);

	public void UpdateRecoveryWithdrawal(User user);

	public void UpdateWithdrawalByDormant(String ajxMsg);

	public boolean SelectDormant(String loginID);

	public void UpdateRecoveryDormant(String loginID);

	public String SelectWriter(String userLoginID);

	public String SelectUserIDForDocument(String userLoginID);

	public String SelectTWriterID(String tWriter);

	public String SelectUserIDForMyBoard(String loginID);

	public String SelectEmailForInquiry(String userLoginID);

	public String SelectPhoneNumForInquiry(String userLoginID);

	public String SelectUserIDForDate(String loginID);

	public String SelectIDForReview(String userLoginID);

	public User SelectModifyUserInfo(String loginID);

}