package com.ll.groupware_renewal.service;

import com.ll.groupware_renewal.entity.Class;
import com.ll.groupware_renewal.entity.*;
import com.ll.groupware_renewal.repository.TeamJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {
	TeamJpaRepository teamDao;

	public void InsertTeamInfo(Team team) {
		teamDao.InsertTeamInfo(team);
	}

	public int SelectClassID(Class classInfo) {
		return teamDao.SelectClassID(classInfo);
	}

	public int SelectUserIDForTeamUser(User user) {
		return teamDao.SelectUserIDForTeamUser(user);
	}

	public void InsertTeamUserInfo(TeamUser teamUser) {
		teamDao.InsertTeamUserInfo(teamUser);
	}

	public List<Class> SelectLectureInfo(String lectureName) {
		List<Class> LectureInfo = teamDao.SelectLectureInfo(lectureName);
		return LectureInfo;
	}

	public int SelectTeamLeaderUserID(String name) {
		return teamDao.SelectTeamLeaderUserID(name);
	}

	public List<Team> SelectTeamList() {
		List<Team> SelectTeamList = teamDao.SelectTeamList();
		return SelectTeamList;
	}

	public Class SelectClassList(int classID) {
		Class SelectClassList = teamDao.SelectClassList(classID);
		return SelectClassList;
	}

	public int SelectClassIDForCheckTeam(int teamID) {
		return teamDao.SelectClassIDForCheckTeam(teamID);
	}

	public List<Class> SelectClassInfoForCheckTeam(int classID) {
		return teamDao.SelectClassInfoForCheckTeam(classID);
	}

	public String SelectTeamName(int teamID) {
		return teamDao.SelectTeamName(teamID);
	}

	public List<TeamUser> SelectTeamMemberInfo(int teamID) {
		return teamDao.SelectTeamMemberInfo(teamID);
	}

	public String SelectLeaderName(int userID) {
		return teamDao.SelectLeaderName(userID);
	}

	public String SelectLeaderLoginID(int userID) {
		return teamDao.SelectLeaderLoginID(userID);
	}

	public List<TeamUser> SelectMyTeamList(String loginID) {
		return teamDao.SelectMyTeamList(loginID);
	}

	public void DeleteTeamMemberInfo(int teamID) {
		teamDao.DeleteTeamMemberInfo(teamID);
	}

	public List<Team> SelectMyTeamInfo(int teamID) {
		return teamDao.SelectMyTeamInfo(teamID);
	}

	public List<Class> SelectClassInfo(int classID) {
		return teamDao.SelectClassInfo(classID);
	}

	public List<TeamBoard> SelectTeamBoardListInfo(String teamID) {
		return teamDao.SelectTeamBoardListInfo(teamID);
	}

	public String SelectTeamIDForDocument(String userID) {
		return teamDao.SelectTeamIDForDocument(userID);
	}

	public String SelectTeamIDForDelete(String tUserID) {
		return teamDao.SelectTeamIDForDelete(tUserID);
	}

	public Integer SelectClassIDFromTeam(Integer teamID) {
		return teamDao.SelectClassIDFromTeam(teamID);
	}

	public List<Integer> SelectTeamNameWithLoginUser(String name) {
		return teamDao.SelectTeamNameWithLoginUser(name);
	}

	public String SelectTeamIDForReview(String teamName) {
		return teamDao.SelectTeamIDForReview(teamName);
	}

	public List<TeamUser> SelectTeamMember(String teamID) {
		return teamDao.SelectTeamMember(teamID);
	}

	public String SelectTeamUserID(String userLoginID) {
		return teamDao.SelectTeamUserID(userLoginID);
	}

	public void InsertUserReview(UserReview userReview) {
		teamDao.InsertUserReview(userReview);
	}

	public String SelectTeamLeaderLoginID(String teamID) {
		return teamDao.SelectTeamLeaderLoginID(teamID);
	}

	public void DeleteTeam(String teamID) {
		teamDao.DeleteTeam(teamID);
	}

	public String SelectWriterUserID(String name) {
		return teamDao.SelectWriterUserID(name);
	}

	public int SelectColumnCount(UserReview userReview) {
		return teamDao.SelectColumnCount(userReview);
	}

	public String SelectTeamNameWithTeamID(int teamID) {
		return teamDao.SelectTeamNameWithTeamID(teamID);
	}
}
