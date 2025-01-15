package com.ll.groupware_renewal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class LectureRoom {
	private int LectureRoomNo;
	private String RoomLocation;
	private int RoomFloor;
	private int MaxNumOfPeople;
	private String RoomType;

    @Id
    private Long id;
}
