package com.ll.groupware_renewal.repository;

import com.ll.groupware_renewal.entity.Calender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;
import java.util.List;

public interface CalenderJpaRepository extends JpaRepository<Calender, Integer> {
	List<HashMap<String, Object>> SelectSchedule(int userID);
	Integer SelectUserIdForCalender(String loginID); // get userId
	int UpdateSchedule(String userId, String id, Calender calender);
	int DeleteSchedule(String userId, String id);
	int UpdateTimeInMonth(HashMap<String, String> map);
	int UpdateTimeInWeek(String userId, String id, Calender calender);

    int InsertSchedule(Calender calender);
}