package com.ll.groupware_renewal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "InquiryBoard")
@Getter
@Setter
@NoArgsConstructor
public class InquiryBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iBoardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    private String iBoardSubject;
    private String iBoardContent;
    private String iBoardWriter;
    private String iBoardAnswer;
    private boolean iBoardDelete;
    private String iBoardType;
    private String state;
    private String userEmail;
    private String userPhoneNum;
    private LocalDateTime iBoardDate;
} 