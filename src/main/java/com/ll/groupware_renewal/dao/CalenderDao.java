package com.ll.groupware_renewal.dao;

import com.ll.groupware_renewal.dto.Calender;

import java.util.HashMap;
import java.util.List;

public interface CalenderDao {

	int InsertSchedule(Calender calender);

	List<HashMap<String, Object>> SelectSchedule(int userID);

	Integer SelectUserIdForCalender(String loginID); // get userId

	int UpdateSchedule(String userId, String id, Calender calender);

	int DeleteSchedule(String userId, String id);

	int UpdateTimeInMonth(HashMap<String, String> map);
	
	int UpdateTimeInWeek(String userId, String id, Calender calender);

}