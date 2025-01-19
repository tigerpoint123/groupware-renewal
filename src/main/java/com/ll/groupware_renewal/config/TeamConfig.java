package com.ll.groupware_renewal.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "team")
public class TeamConfig {
    private Urls urls = new Urls();
    private Fields fields = new Fields();
    private String string = new String();
    private Sql sql = new Sql();

    @Getter
    @Setter
    public static class Urls {
        private String team;
        private String teamList;
        private String teamCreate;
        private String teamDetail;
        private String teamSearch;
        private String teamBoard;
        private String teamBoardDetail;
        private String teamBoardModify;
        private String teamBoardWrite;
        private String reTeam;
        private String reTeamList;
        private String reTeamCreate;
        private String reTeamDetail;
        private String reTeamBoard;
        private String reTeamBoardDetail;
        private String reSearchLecture;
        private String reHome;
        private String myTeamList;
        private String documentList;
        private String documentContent;
        private String documentWrite;
        private String documentListNo;
        private String documentModify;
        private String searchLecture;
        private String checkTeam;
        private String modifyTeam;
        private String reList;
        private String reviewWrite;
        private String reSearchMyTeam;
    }

    @Getter
    @Setter
    public static class Fields {
        private String teamId;
        private String teamName;
        private String teamLeader;
        private String teamLeaderId;
        private String teamMember;
        private String teamMemberId;
        private String classId;
        private String className;
        private String classProfessor;
        private String userId;
        private String userName;
        private String userLoginId;
        private String boardId;
        private String boardTitle;
        private String boardWriter;
        private String boardContent;
        private String boardDate;
        private String boardHits;
        private String path;
    }

    @Getter
    @Setter
    public static class String {
        private String student;
        private String professor;
        private String administrator;
        private String teamLeader;
        private String teamMember;
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
        private String team;
        private String teamByClass;
        private String teamByLeader;
        private String teamByMember;
        private String teamBoard;
        private String teamBoardById;
    }

    @Getter
    @Setter
    public static class Insert {
        private String team;
        private String teamMember;
        private String teamBoard;
    }

    @Getter
    @Setter
    public static class Update {
        private String teamName;
        private String teamLeader;
        private String boardHits;
        private String boardContent;
    }

    @Getter
    @Setter
    public static class Delete {
        private String team;
        private String teamMember;
        private String teamBoard;
    }
} 