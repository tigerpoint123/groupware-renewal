package com.ll.groupware_renewal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TeamFile")
@Getter
@Setter
@NoArgsConstructor
public class TeamFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tFileId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tBoardId")
    private TeamBoard teamBoard;

    private String tOriginalFileName;
    private String tStoredFileName;
    private Long tFileSize;
    private boolean tFileDelete;
} 