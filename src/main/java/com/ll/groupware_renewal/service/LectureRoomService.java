package com.ll.groupware_renewal.service;

import com.ll.groupware_renewal.dto.LectureRoom;
import com.ll.groupware_renewal.dto.UserReservation;

import java.util.List;

public interface LectureRoomService {

	List<LectureRoom> SelectLectureRoomList();

	int SelectMaxNumOfPeople(String lectureRoomNo);

	String SelectLoginUserID(String userLoginID);

	void InsertReservation(UserReservation userReservation);

	List<UserReservation> SelectStartTime(String lectureRoomNo);

	int SelectReservationUserID(int userID);

	String SelectUserIDForReservationConfirm(String loginID);

	int SelectLectureRoomNo(String userID);

	int SelectRoomFloor(int lectureRoomNo);

	int SelectLectureRoomMaxNumOfPeople(int lectureRoomNo);

	int SelectReservationNumOfPeople(String userID);

	String SelectReservationStartTime(String userID);

	String SelectLectureRoomLocation(int lectureRoomNo);

	String SelectReservationStartTimeForException(String startTime);

	boolean DeleteReservation(UserReservation userReservation);

	UserReservation SelectRoomInfo(String userID, UserReservation userReservation);


}
