package com.ll.groupware_renewal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Team")
@Getter
@Setter
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classId")
    private Class classEntity;

    private String teamName;
    private LocalDateTime teamCreationDate;
    private String teamLeaderName;
    private String teamLeaderId;

    @OneToMany(mappedBy = "team")
    private List<TeamUser> teamUsers = new ArrayList<>();

    @OneToMany(mappedBy = "team")
    private List<TeamBoard> teamBoards = new ArrayList<>();
} 