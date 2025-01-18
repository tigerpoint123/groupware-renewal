package com.ll.groupware_renewal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "LectureRoom")
@Getter
@Setter
@NoArgsConstructor
public class LectureRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureRoomNo;

    private String roomLocation;
    private String roomFloor;
    private Integer maxNumOfPeople;
    private String roomType;

    @OneToMany(mappedBy = "lectureRoom")
    private List<UserReservation> reservations = new ArrayList<>();
}