package com.ll.groupware_renewal.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "board")
public class BoardConfig {
    private Roles roles = new Roles();
    private Urls urls = new Urls();
    private Fields fields = new Fields();
    private File file = new File();
    private String string = new String();
    private Inquiry inquiry = new Inquiry();

    @Getter
    @Setter
    public static class Roles {
        private String student;
        private String professor;
        private String administrator;
    }

    @Getter
    @Setter
    public static class Urls {
        private String board;
        private String notice;
        private String inquiry;
        private String team;
        private String search;
        private String write;
        private String detail;
        private String modify;
        private String inquiryList;
        private String inquiryContent;
        private String inquiryWrite;
        private String noticeList;
        private String noticeWrite;
        private String noticeContent;
        private String communityList;
        private String communityWrite;
        private String communityContent;
        private String communityModify;
        private String noticeModify;
        
        // redirect URLs
        private String reBoard;
        private String reNotice;
        private String reInquiry;
        private String reTeam;
        private String reWrite;
        private String reDetail;
        private String reModify;
        private String reInquiryList;
        private String rrInquiryList;
        private String rNoticeList;
        private String rrNoticeList;
        private String rCommunityList;
        private String rrCommunityList;
        private String rCommunityModify;
        private String rCommunityWrite;
        private String rNoticeWrite;
        private String rNoticeContent;
    }

    @Getter
    @Setter
    public static class Fields {
        private String title;
        private String writer;
        private String content;
        private String date;
        private String hits;
        private String file;
        private String boardType;
        private String boardDate;
        private String boardID;
        private String userID;
        private String userIDFromWriter;
        
        private String noticeTitle;
        private String noticeWriter;
        private String noticeContent;
        
        private String communityTitle;
        private String communityWriter;
        private String communityContent;
        
        private String inquiryWriter;
        private String inquiryEmail;
        private String inquiryPhoneNum;
        private String inquiryAnswer;
        
        private String student;
        private String professor;
        private String administrator;
    }

    @Getter
    @Setter
    public static class File {
        private String originalName;
        private String storedName;
        private String size;
        private String delete;
        private String path;
    }

    @Getter
    @Setter
    public static class String {
        private String answerCompleted;
        private String answerWaiting;
        private String notice;
        private String community;
        private String inquiryTitle;
        private String inquiryWriter;
        private String iBoardDate;
        private String inquiryContent;
        private String inquiryEmail;
        private String inquiryPhoneNum;
        private String inquiryList;
        private String inquiryType;
        private String inquiryAnswer;
    }

    @Getter
    @Setter
    public static class Inquiry {
        private String title;
        private String writer;
        private String content;
        private String answer;
        private String delete;
        private String type;
        private String date;
        private String state;
    }
} 