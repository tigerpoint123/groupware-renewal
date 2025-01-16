package com.ll.groupware_renewal.service;

import com.ll.groupware_renewal.entity.LectureRoom;
import com.ll.groupware_renewal.entity.UserReservation;
import com.ll.groupware_renewal.repository.LectureRoomJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureRoomService {
	private final LectureRoomJpaRepository lectureRoomDao;

	public List<LectureRoom> SelectLectureRoomList() {
		return lectureRoomDao.SelectLectureRoomList();
	}

	public int SelectMaxNumOfPeople(String lectureRoomNo) {
		return lectureRoomDao.SelectMaxNumOfPeople(lectureRoomNo);
	}

	public String SelectLoginUserID(String userLoginID) {
		return lectureRoomDao.SelectLoginUserID(userLoginID);
	}

	public void InsertReservation(UserReservation userReservation) {
		lectureRoomDao.InsertReservation(userReservation);
	}

	public List<UserReservation> SelectStartTime(String lectureRoomNo) {
		return lectureRoomDao.SelectStartTime(lectureRoomNo);
	}

	public int SelectReservationUserID(int userID) {
		return lectureRoomDao.SelectReservationUserID(userID);
	}

	public String SelectUserIDForReservationConfirm(String loginID) {
		return lectureRoomDao.SelectUserIDForReservationConfirm(loginID);
	}

	public int SelectLectureRoomNo(String userID) {
		return lectureRoomDao.SelectLectureRoomNo(userID);
	}

	public String SelectLectureRoomLocation(int lectureRoomNo) {
		return lectureRoomDao.SelectLectureRoomLocation(lectureRoomNo);
	}

	public int SelectLectureRoomMaxNumOfPeople(int lectureRoomNo) {
		return lectureRoomDao.SelectLectureRoomMaxNumOfPeople(lectureRoomNo);
	}

	public int SelectReservationNumOfPeople(String userID) {
		return lectureRoomDao.SelectReservationNumOfPeople(userID);
	}

	public String SelectReservationStartTime(String userID) {
		return lectureRoomDao.SelectReservationStartTime(userID);
	}

	public int SelectRoomFloor(int lectureRoomNo) {
		return lectureRoomDao.SelectRoomFloor(lectureRoomNo);
	}

	public String SelectReservationStartTimeForException(String startTime) {
		return lectureRoomDao.SelectReservationStartTimeForException(startTime);
	}

	public UserReservation SelectRoomInfo(String UserID, UserReservation userReservation) {
		userReservation = lectureRoomDao.SelectRoomInfo(UserID, userReservation);
		return userReservation;
	}

	public boolean DeleteReservation(UserReservation userReservation) {
		boolean Check = lectureRoomDao.DeleteReservation(userReservation);
		return Check;
	}
}
