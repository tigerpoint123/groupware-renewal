package com.ll.groupware_renewal.service;

import com.ll.groupware_renewal.dto.Calender;

import java.util.HashMap;
import java.util.List;

public interface CalenderService {

	int InsertSchedule(Calender calender);

	List<HashMap<String, Object>> SelectSchedule(int userId);

	int SelectUserIdForCalender(String loginID);

	int UpdateSchedule(String string, String id, Calender calender);

	int DeleteSchedule(String string, String id);

	int UpdateTimeInMonth(HashMap<String, String> map);
	
	int UpdateTimeInWeek(String string, String id, Calender calender);
}
