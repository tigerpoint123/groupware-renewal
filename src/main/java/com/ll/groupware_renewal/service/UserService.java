package com.ll.groupware_renewal.service;

import com.ll.groupware_renewal.entity.Professor;
import com.ll.groupware_renewal.entity.Student;
import com.ll.groupware_renewal.entity.User;
import com.ll.groupware_renewal.entity.UserInfoOpen;
import com.ll.groupware_renewal.repository.ProfessorJpaRepository;
import com.ll.groupware_renewal.repository.StudentJpaRepository;
import com.ll.groupware_renewal.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {
    private UserJpaRepository userDao;
    private StudentJpaRepository studentDao;
    private ProfessorJpaRepository professorDao;

    public void InsertForSignUp(User user) {
        userDao.InsertForSignUp(user);
    }

    public boolean SelctForIDConfirm(User user) {
        boolean Checker = userDao.SelctForIDConfirm(user);
        return Checker;
    }

    public boolean SelectPwdForConfirmToFindPwd(User user) {
        boolean PwdChecker = userDao.SelectPwdForConfirmToFindPwd(user);
        return PwdChecker;
    }

    public String SelectForShowPassword(User user) {// 임시 비밀번호 생성
        boolean Checker = userDao.SelectForShowPassword(user);
        Random RandomNum = new Random();
        String Result = "";
        if (Checker) {
            Result = Integer.toString(RandomNum.nextInt(8) + 1);
            for (int i = 0; i < 7; i++) {
                Result += Integer.toString(RandomNum.nextInt(9));
            }
        } else {
            Result = "false";
        }
        return Result;
    }

    public int SelectUserID(Student student) {
        return userDao.SelectUserID(student);
    }

    public int SelectUserID(Professor professor) {
        return userDao.SelectUserID(professor);
    }

    public void UpdateLoginDate(User user) {
        userDao.UpdateLoginDate(user);
    }

    public String SelectCurrentPwd(String id) {
        return userDao.SelectCurrentPwd(id);
    }

    public void UpdatePwd(User user) {
        userDao.UpdatePwd(user);
    }

    public ArrayList<String> SelectUserProfileInfo(String id) {
        ArrayList<String> Info = new ArrayList<String>();
        Info = userDao.SelectUserProfileInfo(id);
        return Info;
    }

    public void updateUserPhoneNumber(User user) {
        userDao.updateUserPhoneNumber(user);
    }

    public void UpdateUserMajor(User user) {
        userDao.updateUserMajor(user);
    }

    public void UpdateUserColleges(User user) {
        userDao.updateUserColleges(user);
    }

    public ArrayList<String> SelectUserInformation(String userId) {
        ArrayList<String> UserInfo = new ArrayList<String>();
        UserInfo = userDao.SelectUserInformation(userId);
        return UserInfo;
    }

    public boolean SelectForPwdCheckBeforeModify(String id, String pw) {
        return userDao.SelectForPwdCheckBeforeModify(id, pw);
    }

    public void UpdateTemporaryPwd(User user) {
        userDao.UpdateTemporaryPwd(user);

    }

    public void UpdateDoWithdrawalRecoveryByAdmin(String ajaxMsg) {
        userDao.UpdateDoWithdrawalRecoveryByAdmin(ajaxMsg);
    }

    public void UpdateDormantOneToZero(String id) {
        userDao.UpdateDormantOneToZero(id);
    }

    public void UpdateUserRole(String id, String optionValue) {
        userDao.UpdateUserRole(id, optionValue);
    }

    public void UpdateAdminRole(String id, String optionValue) {
        userDao.UpdateAdminRole(id, optionValue);
    }

    public ArrayList<String> SelectMyPageUserInfo(String userId) {
        ArrayList<String> Info = new ArrayList<String>();
        ArrayList<String> UserInfo = new ArrayList<String>();
        ArrayList<String> StudentInfo = new ArrayList<String>();
        ArrayList<String> ProfessorInfo = new ArrayList<String>();

        UserInfo = userDao.SelectMyPageUserInfo(userId);
        StudentInfo = studentDao.SelectMyPageUserInfo(UserInfo.get(0));
        ProfessorInfo = professorDao.SelectMyPageUserInfo(UserInfo.get(0));
        UserInfo.remove(0);

        for (int i = 0; i < UserInfo.size(); i++) {
            Info.add(UserInfo.get(i));
        }
        for (int i = 0; i < StudentInfo.size(); i++) {
            Info.add(StudentInfo.get(i));
        }
        for (int i = 0; i < ProfessorInfo.size(); i++) {
            Info.add(ProfessorInfo.get(i));
        }
        return Info;
    }

    public ArrayList<String> SelectUserProfileInfoByID(String mysqlID) {
        ArrayList<String> Info = new ArrayList<String>();
        ArrayList<String> UserInfo = new ArrayList<String>();
        ArrayList<String> StudentInfo = new ArrayList<String>();
        ArrayList<String> ProfessorInfo = new ArrayList<String>();
        // 유저정보를 mysqlID를 던져줘서 받아온다.
        UserInfo = userDao.SelectMyPageUserInfoByID(mysqlID);
        // 학생정보를 mysqlID를 통해서 받아온다.
        StudentInfo = studentDao.SelectMyPageUserInfoByID(mysqlID);
        // 교수정보를 mysqlID를 통해서 받아온다.
        ProfessorInfo = professorDao.SelectMyPageUserInfoByID(mysqlID);
        // Data의 크기만큼 Info List에 채워준다.
        for (int i = 0; i < UserInfo.size(); i++) {
            Info.add(UserInfo.get(i));
        }
        // Data의 크기만큼 Info List에 채워준다.
        for (int i = 0; i < StudentInfo.size(); i++) {
            Info.add(StudentInfo.get(i));
        }
        // Data의 크기만큼 Info List에 채워준다.
        for (int i = 0; i < ProfessorInfo.size(); i++) {
            Info.add(ProfessorInfo.get(i));
        }
        return Info;
    }

    public void UpdateUserName(User user) {
        userDao.UpdateUserName(user);
    }

    public void UpdateOpenPhoneNum(User user) {
        userDao.UpdatePhoneNum(user);
    }

    public void UpdateOpenGrade(User user) {
        userDao.UpdateOpenGrade(user);
    }

    public User SelectUserInfo(String userLoginID) {
        return userDao.SelectUserInfo(userLoginID);
    }

    public String SelectOpenInfo(String userID) {
        // XML화해야할지 팀원들과 얘기하기
        // 추후 Entity로 옮겨야함
        List<UserInfoOpen> SelectOpenInfo = userDao.SelectOpenInfo(userID);
        String result = "Error";

        result = SelectOpenInfo.get(0).getOpenGrade() + "," + SelectOpenInfo.get(0).getOpenPhoneNum();

        if (result.contains(",비공개") || result.contains("비공개")) {
            result = result.replaceAll(",비공개", "");
            result = result.replaceAll("비공개", "");
            boolean startComma = result.startsWith(",");
            boolean endComma = result.endsWith(",");
            if (startComma || endComma) {
                result = result.substring(result.length() - (result.length() - 1), result.length());
            }
        }

        return result;

    }

    public int SelectUserIDFromBoardController(String userLoginID) {
        return userDao.SelectUserIDFromBoardController(userLoginID);
    }

    public String SelectUserRole(String userLoginID) {
        return userDao.SelectUserRole(userLoginID);
    }

    public String SelectUserName(String userLoginID) {
        return userDao.SelectUserName(userLoginID);
    }

    public void UpdateWithdrawal(User user) {
        userDao.UpdateWithdrawalUser(user);
    }

    public void UpdateRecoveryWithdrawal(User user) {
        userDao.UpdateRecoveryWithdrawal(user);
    }

    public void UpdateWithdrawalByDormant(String ajxMsg) {
        userDao.UpdateWithdrawalByDormant(ajxMsg);
    }

    public boolean SelectDormant(String loginID) {
        boolean DormantCheck = userDao.SelectDormant(loginID);
        return DormantCheck;
    }

    public void UpdateRecoveryDormant(String loginID) {
        userDao.UpdateRecoveryDormant(loginID);
    }

    public String SelectWriter(String userLoginID) {
        String Output = userDao.SelectWriter(userLoginID);
        return Output;
    }

    public String SelectUserIDForDocument(String userLoginID) {
        String Output = userDao.SelectUserIDForDocument(userLoginID);
        return Output;
    }

    public String SelectTWriterID(String tWriter) {
        return userDao.SelectTWriterID(tWriter);
    }

    public String SelectUserIDForMyBoard(String loginID) {
        return userDao.SelectUserIDForMyBoard(loginID);
    }

    public String SelectEmailForInquiry(String userLoginID) {
        String EmailForInquiry = userDao.SelectEmailForInquiry(userLoginID);
        return EmailForInquiry;
    }

    public String SelectPhoneNumForInquiry(String userLoginID) {
        String PhoneNumForInquiry = userDao.SelectPhoneNumForInquiry(userLoginID);
        return PhoneNumForInquiry;
    }

    public String SelectUserIDForDate(String loginID) {
        return userDao.SelectUserIDForDate(loginID);
    }

    public String SelectIDForReview(String userLoginID) {
        return userDao.SelectIDForReview(userLoginID);
    }

    public User SelectModifyUserInfo(String loginID) {
        return userDao.SelectModifyUserInfo(loginID);
    }
}