package com.ll.groupware_renewal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TeamBoard")
@Getter
@Setter
@NoArgsConstructor
public class TeamBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tBoardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamId")
    private Team team;

    private String tBoardSubject;
    private String tBoardContent;
    private String tBoardWriter;
    private boolean tBoardDelete;
    private LocalDateTime tBoardDate;
    private String tUserLoginId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classId")
    private Class classEntity;

    @OneToMany(mappedBy = "teamBoard", cascade = CascadeType.ALL)
    private List<TeamFile> teamFiles = new ArrayList<>();
} 