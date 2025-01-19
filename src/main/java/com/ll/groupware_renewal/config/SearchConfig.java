package com.ll.groupware_renewal.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "search")
public class SearchConfig {
    private Urls urls = new Urls();
    private Fields fields = new Fields();

    @Getter
    @Setter
    public static class Urls {
        private String searchUser;
        private String reviewList;
        private String rSearchUser;
    }

    @Getter
    @Setter
    public static class Fields {
        private String sRole;
        private String pRole;
        private String aRole;
        private String uName;
        private String userEmail;
        private String phoneNum;

    }
}
