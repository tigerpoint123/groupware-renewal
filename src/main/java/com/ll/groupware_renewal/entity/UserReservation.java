package com.ll.groupware_renewal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserReservation {
	private String ReservationDate;
	private String ReservationStartTime;
	private String ReservationEndTime;
	private int ReservationNumOfPeople;
	private int LectureRoomNo;
	private int UserID;
	
    @Id
    private Long id;
}
