package com.ll.groupware_renewal.repository;

import com.ll.groupware_renewal.entity.LectureRoom;
import com.ll.groupware_renewal.entity.UserReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRoomJpaRepository extends JpaRepository<LectureRoom, String> {

	List<LectureRoom> SelectLectureRoomList();

	int SelectMaxNumOfPeople(String lectureRoomNo);

	String SelectLoginUserID(String userLoginID);

	void InsertReservation(UserReservation userReservation);

	List<UserReservation> SelectStartTime(String lectureRoomNo);

	int SelectReservationUserID(int userID);

	String SelectUserIDForReservationConfirm(String loginID);

	int SelectLectureRoomNo(String userID);

	String SelectLectureRoomLocation(int lectureRoomNo);

	int SelectLectureRoomMaxNumOfPeople(int lectureRoomNo);

	int SelectReservationNumOfPeople(String userID);

	String SelectReservationStartTime(String userID);

	int SelectRoomFloor(int lectureRoomNo);

	String SelectReservationStartTimeForException(String startTime);

	boolean DeleteReservation(UserReservation userReservation);

	UserReservation SelectRoomInfo(String userID, UserReservation userReservation);


}
