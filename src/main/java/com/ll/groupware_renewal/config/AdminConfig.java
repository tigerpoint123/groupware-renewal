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
    private Roles roles = new Roles();
    private Urls urls = new Urls();
    private UserRoles userRoles = new UserRoles();
    private UserTypes userTypes = new UserTypes();
    private Fields fields = new Fields();

    @Getter
    @Setter
    public static class Roles {
        private String student;
        private String professor;
        private String administrator;
        private String temporaryPwd;
    }

    @Getter
    @Setter
    public static class Urls {
        private String home;
        private String list;
        private String reList;
        private String reSleep;
        private String sleepList;
        private String secessionList;
        private String reSecessionList;
        private String detail;
        private String reStudentDetail;
        private String reProfessorDetail;
        private String studentDetail;
        private String professorDetail;
        private String studentModify;
        private String professorModify;
        private String studentManage;
        private String professorManage;
        private String studentManageModify;
        private String professorManageModify;
    }

    @Getter
    @Setter
    public static class UserRoles {
        private String roleUser;
        private String roleStudent;
        private String roleProfessor;
        private String roleAdmin;
    }

    @Getter
    @Setter
    public static class UserTypes {
        private String student;
        private String professor;
        private String administrator;
    }

    @Getter
    @Setter
    public static class Fields {
        private String userName;
        private String userEmail;
        private String phoneNumber;
        private String professorRoom;
        private String email;
    }
} 