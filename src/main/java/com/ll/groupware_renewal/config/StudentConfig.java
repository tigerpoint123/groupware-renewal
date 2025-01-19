package com.ll.groupware_renewal.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "student")
public class StudentConfig {
    private Urls urls = new Urls();
    private Fields fields = new Fields();
    private String string = new String();
    private Sql sql = new Sql();

    @Getter
    @Setter
    public static class Urls {
        private String myPage;
        private String modify;
        private String signup;
    }

    @Getter
    @Setter
    public static class Fields {
        private String userName;
        private String userNameForOpen;
        private String userLoginId;
        private String userPhoneNum;
        private String userPhone;
        private String userEmail;
        private String userMajor;
        private String userGrade;
        private String grade;
        private String email;
    }

    @Getter
    @Setter
    public static class String {
        private String student;
        private String user;
        private String admin;
        private String publicValue;
        private String privateValue;
    }

    @Getter
    @Setter
    public static class Sql {
        private Select select = new Select();
        private Update update = new Update();
    }

    @Getter
    @Setter
    public static class Select {
        private String studentInfo;
        private String studentById;
    }

    @Getter
    @Setter
    public static class Update {
        private String studentInfo;
        private String phoneNum;
        private String email;
        private String openPhone;
        private String openEmail;
        private String openGrade;
    }
} 