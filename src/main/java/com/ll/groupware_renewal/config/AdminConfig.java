package com.ll.groupware_renewal.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "admin")
public class AdminConfig {
    // 역할 관련
    private String studentRole = "STUDENT";
    private String professorRole = "PROFESSOR";
    private String administratorRole = "ADMINISTRATOR";

    // URL 관련
    private Urls urls = new Urls();
    
    // 사용자 필드 관련
    private UserFields userFields = new UserFields();

    @Getter
    @Setter
    public static class Urls {
        private String signupStudent = "/student/signup";
        private String myPageStudent = "/student/mypage";
        private String modifyStudent = "/student/modify";
        // ... 다른 URL들
    }

    @Getter
    @Setter
    public static class UserFields {
        private String userName = "userName";
        private String userEmail = "userEmail";
        private String phoneNumber = "phoneNumber";
        // ... 다른 필드명들
    }
} 