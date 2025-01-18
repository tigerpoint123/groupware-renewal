package com.ll.groupware_renewal.entity;

import com.ll.groupware_renewal.dto.Board;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "User")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userName;
    private String userEmail;
    private String userPhoneNum;
    private String userLoginId;
    private String userLoginPwd;
    private String userRole;
    private String authority;
    private boolean enabled;
    private String openName;
    private String openEmail;
    private String openPhoneNum;
    private LocalDateTime loginDate;
    private String openGrade;
    private boolean dormant;
    private boolean withdrawal;

    @OneToMany(mappedBy = "user")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserSchedule> schedules = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<InquiryBoard> inquiryBoards = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserReservation> reservations = new ArrayList<>();
}