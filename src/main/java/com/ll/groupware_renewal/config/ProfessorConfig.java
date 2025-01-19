package com.ll.groupware_renewal.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "professor")
public class ProfessorConfig {
    private Urls urls = new Urls();
    private Fields fields = new Fields();
    private String string = new String();
    private Sql sql = new Sql();

    @Getter
    @Setter
    public static class Urls {
        private String home;
        private String myPage;
        private String modify;
        private String modifyInfo;
        private String reHome;
        private String reMyPage;
        private String reModify;
        private String reModifyInfo;
        private String signupProfessor;
        private String mypageProfessor;
        private String modifyProfessor;
    }

    @Getter
    @Setter
    public static class Fields {
        private String userName;
        private String userNameForOpen;
        private String userLoginID;
        private String userPhoneNum;
        private String userPhone;
        private String email;
        private String userEmail;
        private String userMajor;
        private String userCollege;
        private String professorRoom;
        private String professorRoomNum;
        private String openPhoneNum;
        private String openEmail;
    }

    @Getter
    @Setter
    public static class String {
        private String professor;
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
        private String professorInfo;
        private String professorById;
    }

    @Getter
    @Setter
    public static class Update {
        private String professorInfo;
        private String phoneNum;
        private String email;
        private String openPhone;
        private String openEmail;
    }
} 