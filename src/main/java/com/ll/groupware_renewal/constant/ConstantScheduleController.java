package com.ll.groupware_renewal.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@RequiredArgsConstructor
@Component
public class  ConstantScheduleController {

	private String SRole;
	private String PRole;
	private String ARole;
	
	private String Schedule;
	private String userId;
	private String scheduleID;
	private String start;
	private String end;

}
