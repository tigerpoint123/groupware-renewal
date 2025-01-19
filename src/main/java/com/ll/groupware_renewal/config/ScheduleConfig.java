package com.ll.groupware_renewal.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "schedule")
public class ScheduleConfig {
    private Urls urls = new Urls();
    private Fields fields = new Fields();
    private String string = new String();
    private Sql sql = new Sql();

    @Getter
    @Setter
    public static class Urls {
        private String schedule;
        private String modify;
        private String detail;
        private String reSchedule;
        private String reModify;
        private String reDetail;
    }

    @Getter
    @Setter
    public static class Fields {
        private String scheduleId;
        private String userId;
        private String title;
        private String content;
        private String date;
        private String startTime;
        private String endTime;
        private String completed;
        private String year;
        private String month;
        private String day;
        private String dayOfWeek;
        private String sRole;
        private String pRole;
        private String aRole;
        private String start;
        private String end;
    }

    @Getter
    @Setter
    public static class String {
        private String completed;
        private String notCompleted;
    }

    @Getter
    @Setter
    public static class Sql {
        private Select select = new Select();
        private Insert insert = new Insert();
        private Update update = new Update();
        private Delete delete = new Delete();
    }

    @Getter
    @Setter
    public static class Select {
        private String schedule;
        private String scheduleById;
        private String scheduleByDate;
        private String scheduleByMonth;
        private String scheduleByYear;
        private String scheduleByYearMonth;
    }

    @Getter
    @Setter
    public static class Insert {
        private String schedule;
    }

    @Getter
    @Setter
    public static class Update {
        private String schedule;
        private String completed;
    }

    @Getter
    @Setter
    public static class Delete {
        private String schedule;
    }
} 