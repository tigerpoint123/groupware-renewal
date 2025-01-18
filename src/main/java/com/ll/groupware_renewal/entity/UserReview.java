package com.ll.groupware_renewal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "UserReview")
@Getter
@Setter
@NoArgsConstructor
public class UserReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    private String positive;
    private String contribute;
    private String respect;
    private String flexible;
    private String className;
    private String classProfessorName;
    private LocalDateTime reviewDate;
    private String writerUserId;
    private String teamName;
} 