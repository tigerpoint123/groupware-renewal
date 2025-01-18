package com.ll.groupware_renewal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "UserReservation")
@Getter
@Setter
@NoArgsConstructor
public class UserReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lectureRoomNo")
    private LectureRoom lectureRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    private LocalDateTime reservationDate;
    private LocalDateTime reservationStartTime;
    private LocalDateTime reservationEndTime;
    private Integer reservationNumOfPeople;
} 