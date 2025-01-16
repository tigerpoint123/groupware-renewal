package com.ll.groupware_renewal.service;

import com.ll.groupware_renewal.entity.Calender;
import com.ll.groupware_renewal.repository.CalenderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalenderService {
	private final CalenderJpaRepository calenderJpaRepository;

	public int InsertSchedule(Calender calender) {
		int Count = calenderJpaRepository.InsertSchedule(calender);
		return Count;
	}

	public List<HashMap<String, Object>> SelectSchedule(int userID) {
		return calenderJpaRepository.SelectSchedule(userID);
	}

	public int SelectUserIdForCalender(String loginID) {
		Integer UserID = calenderJpaRepository.SelectUserIdForCalender(loginID);
		if (UserID == null) {
			return 0;
		} else {
			return UserID;
		}
	}

	public int UpdateSchedule(String userId, String id, Calender calender) {
		int Count = calenderJpaRepository.UpdateSchedule(userId, id, calender);
		return Count;

	}

	public int DeleteSchedule(String userId, String id) {
		int Count = calenderJpaRepository.DeleteSchedule(userId, id);
		return Count;
	}

	public int UpdateTimeInMonth(HashMap<String, String> map) {
		return calenderJpaRepository.UpdateTimeInMonth(map);
	}

	public int UpdateTimeInWeek(String userId, String id, Calender calender) {
		int Count = calenderJpaRepository.UpdateTimeInWeek(userId, id, calender);
		return Count;
	}

}
