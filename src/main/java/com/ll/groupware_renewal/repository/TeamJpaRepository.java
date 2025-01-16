package com.ll.groupware_renewal.repository;

import com.ll.groupware_renewal.entity.Class;
import com.ll.groupware_renewal.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamJpaRepository extends JpaRepository<Team, Integer> {

	void InsertTeamInfo(Team team);

	int SelectClassID(Class classInfo);

	int SelectUserIDForTeamUser(User user);

	void InsertTeamUserInfo(TeamUser teamUser);

	List<Class> SelectLectureInfo(String lectureName);

	int SelectTeamLeaderUserID(String name);

	List<Team> SelectTeamList();

	Class SelectClassList(int classID);

	int SelectClassIDForCheckTeam(int teamID);

	List<Class> SelectClassInfoForCheckTeam(int classID);

	String SelectTeamName(int teamID);

	List<TeamUser> SelectTeamMemberInfo(int teamID);

	String SelectLeaderName(int userID);

	String SelectLeaderLoginID(int userID);

	List<TeamUser> SelectMyTeamList(String loginID);

	void DeleteTeamMemberInfo(int teamID);

	List<Team> SelectMyTeamInfo(int teamID);

	List<Class> SelectClassInfo(int classID);

	List<TeamBoard> SelectTeamBoardListInfo(String teamID);

	String SelectTeamIDForDocument(String userID);

	String SelectTeamIDForDelete(String tUserID);

	Integer SelectClassIDFromTeam(Integer teamID);

	List<Integer> SelectTeamNameWithLoginUser(String name);

	String SelectTeamIDForReview(String teamName);

	List<TeamUser> SelectTeamMember(String teamID);

	String SelectTeamUserID(String userLoginID);

	void InsertUserReview(UserReview userReview);

	String SelectTeamLeaderLoginID(String teamID);

	void DeleteTeam(String teamID);

	String SelectWriterUserID(String name);

	int SelectColumnCount(UserReview userReview);

	String SelectTeamNameWithTeamID(int teamID);

}
