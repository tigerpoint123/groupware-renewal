package com.ll.groupware_renewal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "BoardFile")
@Getter
@Setter
@NoArgsConstructor
public class BoardFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bFileId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardId")
    private Board board;

    private String bOriginalFileName;
    private String bStoredFileName;
    private Long bFileSize;
    private boolean bDelete;
} 