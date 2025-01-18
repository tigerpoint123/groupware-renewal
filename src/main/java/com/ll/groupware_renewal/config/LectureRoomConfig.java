package com.ll.groupware_renewal.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "lecture-room")
public class LectureRoomConfig {
    private Urls urls = new Urls();
    private Fields fields = new Fields();
    private String string = new String();
    private Sql sql = new Sql();
    private Time time = new Time();

    @Getter
    @Setter
    public static class Urls {
        private String list;
        private String reservation;
        private String inquiry;
        private String modify;
        private String reList;
        private String reReservation;
        private String reInquiry;
        private String reModify;
        private String confirmMyReservation;
        private String confirm;
        private String myPageStudent;
    }

    @Getter
    @Setter
    public static class Fields {
        private String roomId;
        private String maxPeople;
        private String roomNumber;
        private String roomName;
        private String reservationId;
        private String userId;
        private String startTime;
        private String endTime;
        private String date;
        private String purpose;
    }

    @Getter
    @Setter
    public static class Time {
        private String nine;
        private String eleven;
        private String thirteen;
        private String fifteen;
        private String seventeen;
        private String nineteen;
    }

    @Getter
    @Setter
    public static class String {
        private String available;
        private String unavailable;
        private String reserved;
        private String canceled;
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
        private String roomList;
        private String roomById;
        private String reservationById;
        private String reservationByDate;
        private String reservationByRoom;
    }

    @Getter
    @Setter
    public static class Insert {
        private String reservation;
    }

    @Getter
    @Setter
    public static class Update {
        private String reservation;
        private String status;
    }

    @Getter
    @Setter
    public static class Delete {
        private String reservation;
    }
} 