package com.ll.groupware_renewal.repository;

import com.ll.groupware_renewal.entity.Board;
import com.ll.groupware_renewal.entity.TeamBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface BoardJpaRepository  extends JpaRepository<Board, Integer> {
    void insertFile(Map<String, Object> map);
    void InsertTeamFileInfo(Map<String, Object> map);
    Board findCommunityContentByBoardId(String boardID);
    Map<String, Object> SelectCommunityFileInfo(Map<String, Object> map);
    List<Map<String, Object>> SelectCommunityFileList(int bNo);
    Map<String, Object> SelectNoticeFileInfo(Map<String, Object> map);
    List<Map<String, Object>> SelectNoticeFileList(int bNo);
    List<Map<String, Object>> SelectTeamBoardFileList(int bNo);
    Map<String, Object> SelectTeamBoardFileInfo(Map<String, Object> map);
    List<Board> SelectMyBoardList(String login);

    List<Board> findCommunityBoardList();

    List<Board> findNoticeBoardList();

    void updateHitCountByBoardId(String boardID);

    String findLoginUserID(String loginID);

    void updateTeamBoardModifiedContent(TeamBoard teamBoard);

    void updateFile(Map<String, Object> tempMap);

    void UpdateBoardDelete(int boardID);

    List<TeamBoard> SelectTeamBoardList();

    TeamBoard SelectTeamBoardContent(String tBoardID);

    void UpdateTBoardDelete(int tBoardID);

    String SelectWriterID(TeamBoard teamBoard);
}
