package com.ll.groupware_renewal.repository;

import com.ll.groupware_renewal.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface BoardJpaRepository extends JpaRepository<Board, Integer> {
    void InsertFile(Map<String, Object> map);
    void insertFile(Map<String, Object> map);
    void InsertTeamFileInfo(Map<String, Object> map);
    Board SelectOneCommunityContent(String boardID);
    Map<String, Object> SelectCommunityFileInfo(Map<String, Object> map);
    List<Map<String, Object>> SelectCommunityFileList(int bNo);
    Map<String, Object> SelectNoticeFileInfo(Map<String, Object> map);
    List<Map<String, Object>> SelectNoticeFileList(int bNo);
    void UpdateFile(Map<String, Object> tempMap);
    List<Map<String, Object>> SelectTeamBoardFileList(int bNo);
    void InsertTeamFile(Map<String, Object> tempMap);
    void UpdateTeamFile(Map<String, Object> tempMap);
    Map<String, Object> SelectTeamBoardFileInfo(Map<String, Object> map);
    List<Board> SelectMyBoardList(String login);
}
