package com.ll.groupware_renewal.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "user-function")
public class UserFunctionConfig {
    private Urls urls = new Urls();
    private Fields fields = new Fields();
    private String string = new String();
    private Format format = new Format();

    @Getter
    @Setter
    public static class Urls {
        private String home;
        private String login;
        private String logout;
        private String signup;
        private String signupStudent;
        private String findPassword;
        private String showPassword;
        private String emailVerification;
        private String emailVerificationConfirm;
        private String signupSelect;
        private String withdrawal;
        private String checkPassword;
        private String checkPassword2;
        private String checkPassword3;
        private String modifyPassword;
        private String checkMyTeam;
        private String myPostList;
        private String myInquiryList;
        private String emailLogin;
        private String emailList;
        private String emailContent;
        private String homeView;
        
        private String reHome;
        private String reLogin;
        private String reWithdrawal;
        private String reModifyStudent;
        private String reModifyProfessor;
        private String reModifyPassword;
        private String reMypageStudent;
        private String reMypageProfessor;
        private String reEmailList;
        private String reEmailLogin;
    }

    @Getter
    @Setter
    public static class Fields {
        private String userName;
        private String studentName;
        private String professorName;
        private String userLoginId;
        private String userPassword;
        private String userNewPassword;
        private String userNewPasswordCheck;
        private String emailPassword;
        private String userEmail;
        private String userPhoneNum;
        private String studentNum;
        private String professorNum;
        private String emailCheck;
        private String emailValid;
        private String btnAgree;
        private String agreeWithdrawal;
        private String authNum;
    }

    @Getter
    @Setter
    public static class String {
        private String student;
        private String professor;
        private String admin;
        private String emailDomain;
        private String noticeList;
        private String communityList;
        private String myBoardList;
        private String myInquiryList;
    }

    @Getter
    @Setter
    public static class Format {
        private String date;
        private String dateTime;
    }
} 