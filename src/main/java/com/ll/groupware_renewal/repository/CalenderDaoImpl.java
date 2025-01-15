package com.ll.groupware_renewal.repository;

import com.ll.groupware_renewal.constant.ConstantCalenderDao;
import com.ll.groupware_renewal.entity.Calender;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@Repository
public class CalenderDaoImpl implements CalenderJpaRepository {
	private ConstantCalenderDao Constant;
	private CalenderJpaRepository calenderJpaDao;

	@SuppressWarnings("resource")
	public CalenderDaoImpl() {
		GenericXmlApplicationContext CTX = new GenericXmlApplicationContext();
		CTX.load("classpath:/xmlForProperties/CalenderDao.xml");
		CTX.refresh();
		this.Constant = (ConstantCalenderDao) CTX.getBean("CalenderID");
	}

	@Override
	public int InsertSchedule(Calender calender) {
		int Count = sqlSession.insert(this.Constant.getInsertSchedule(), calender);
		return Count;
	}

	@Override
	public List<HashMap<String, Object>> SelectSchedule(int userID) {
		return sqlSession.selectList(this.Constant.getSelectSchedule(), userID);
	}

	@Override
	public Integer SelectUserIdForCalender(String loginID) {
		Integer UserID = sqlSession.selectOne(this.Constant.getSelectUserID(), loginID);
		if (UserID == null) {
			return 0;
		} else {
			return UserID;
		}
	}

	@Override
	public int UpdateSchedule(String userId, String id, Calender calender) {
		HashMap<String, String> Map = new HashMap<String, String>();
		Map.put(this.Constant.getUserID(), userId);
		Map.put(this.Constant.getScheduleID(), id);
		Map.put(this.Constant.getTitle(), calender.getTitle());
		Map.put(this.Constant.getStart(), calender.getStart());
		Map.put(this.Constant.getEnd(), calender.getEnd());
		Map.put(this.Constant.getBackgroundColor(), calender.getBackgroundColor());
		Map.put(this.Constant.getDescription(), calender.getDescription());

		return sqlSession.update(this.Constant.getUpdateSchedule(), Map);
	}

	@Override
	public int DeleteSchedule(String userId, String id) {
		HashMap<String, String> Map = new HashMap<String, String>();
		Map.put("userId", userId);
		Map.put("scheduleID", id);

		return sqlSession.delete(this.Constant.getDeleteSchedule(), Map);

	}

	@Override
	public int UpdateTimeInMonth(HashMap<String, String> map) {
		return sqlSession.update(this.Constant.getUpdateTimeInMonth(), map);
	}

	@Override
	public int UpdateTimeInWeek(String userId, String id, Calender calender) {
		HashMap<String, String> Map = new HashMap<String, String>();
		Map.put(this.Constant.getUserID(), userId);
		Map.put(this.Constant.getStart(), calender.getStart());
		Map.put(this.Constant.getEnd(), calender.getEnd());
		Map.put(this.Constant.getScheduleID(), id);
		return sqlSession.update(this.Constant.getUpdateTimeInWeek(), Map);
	}

}
