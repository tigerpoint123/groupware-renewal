package com.ll.groupware_renewal.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ConstantCalenderDao {

	private String UserID;
	private String ScheduleID;
	private String Title;
	private String Start;
	private String End;
	private String BackgroundColor;
	private String Description;
	
	private String InsertSchedule;
	private String SelectSchedule;
	private String SelectUserID;
	private String UpdateSchedule;
	private String DeleteSchedule;
	private String UpdateTimeInMonth;
	private String UpdateTimeInWeek;
	
}
