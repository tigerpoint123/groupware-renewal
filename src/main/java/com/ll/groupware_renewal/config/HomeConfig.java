package com.ll.groupware_renewal.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "home")
public class HomeConfig {
    private Urls urls = new Urls();

    @Getter
    @Setter
    public static class Urls {
        private String home;
        private String select;
        private String consent;
        private String login;
        private String adminLogin;
        private String denied;
    }
}
