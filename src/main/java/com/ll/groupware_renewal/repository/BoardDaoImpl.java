package com.ll.groupware_renewal.repository;

import com.ll.groupware_renewal.entity.Board;
import com.ll.groupware_renewal.entity.TeamBoard;
import com.ll.groupware_renewal.entity.FileInfo;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Repository
public class BoardDaoImpl implements BoardJpaRepository {
	@PersistenceContext
	private EntityManager em;

	public void InsertBoardInfo(Board board) {
		em.persist(board);
	}

	@Override
	public void InsertFile(Map<String, Object> map) {
		FileInfo fileInfo = new FileInfo();
		// map의 데이터를 fileInfo에 설정
		em.persist(fileInfo);
	}

	@Override
	public void InsertTeamFile(Map<String, Object> map) {
		sqlSession.insert("InsertTeamFile", map);
	}

	@Override
	public void InsertTeamFileInfo(Map<String, Object> map) {
		sqlSession.insert("InsertTeamFileInfo", map);
	}

	@Override
	public List<Board> SelectCommunityBoardList() {
		return em.createQuery("select b from Board b where b.boardType = :type", Board.class)
				.setParameter("type", "COMMUNITY")
				.getResultList();
	}

	@Override
	public List<TeamBoard> SelectTeamBoardList() {
		return em.createQuery("select t from TeamBoard t", TeamBoard.class)
				.getResultList();
	}

	@Override
	public List<Board> SelectNoticeBoardList() {
		return em.createQuery("select b from Board b where b.boardType = :type", Board.class)
				.setParameter("type", "NOTICE")
				.getResultList();
	}

	@Override
	public void UpdateHitCount(String boardID) {
		Board board = em.find(Board.class, boardID);
		board.increaseHitCount();  // Board 엔티티에 해당 메소드 추가 필요
	}

	@Override
	public void insertFile(Map<String, Object> map) {
		sqlSession.insert("InsertFile", map);
	}

	@Override
	public int SelectBoardID(Board board) {
		return board.getId();  // Board 엔티티의 ID 필드 반환
	}

	@Override
	public Board SelectOneCommunityContent(String boardID) {
		return em.find(Board.class, boardID);
	}

	@Override
	public Board SelectOneNoticeContent(String boardID) {
		return em.find(Board.class, boardID);
	}

	@Override
	public String SelectLoginUserID(String loginID) {
		return em.createQuery("select u.id from User u where u.loginID = :loginID", String.class)
				.setParameter("loginID", loginID)
				.getSingleResult();
	}

	@Override
	public void UpdateModifiedContent(Board board) {
		em.merge(board);
	}

	@Override
	public void UpdateTeamBoardModifiedContent(TeamBoard teamBoard) {
		em.merge(teamBoard);
	}

	@Override
	public void DeleteCommunity(int boardID) {
		Board board = em.find(Board.class, boardID);
		if (board != null) {
			em.remove(board);
		}
	}

	@Override
	public void DeleteNotice(int boardID) {
		Board board = em.find(Board.class, boardID);
		if (board != null) {
			em.remove(board);
		}
	}

	@Override
	public List<Map<String, Object>> SelectCommunityFileList(int BNo) {
		return em.createQuery(
			"select new map(f.fileName as fileName, f.fileSize as fileSize, f.fileOriginName as fileOriginName) " +
			"from FileInfo f where f.boardId = :boardId", Map.class)
			.setParameter("boardId", BNo)
			.getResultList();
	}

	@Override
	public Map<String, Object> SelectCommunityFileInfo(Map<String, Object> map) {
		Map<String, Object> SelectCommunityFileInfo = sqlSession.selectOne("SelectCommunityFileInfo", map);
		return SelectCommunityFileInfo;
	}

	public List<Map<String, Object>> SelectNoticeFileList(int BNo) {
		List<Map<String, Object>> SelectNoticeFileList = sqlSession.selectList("SelectNoticeFileList", BNo);

		return SelectNoticeFileList;
	}

	@Override
	public Map<String, Object> SelectNoticeFileInfo(Map<String, Object> map) {
		Map<String, Object> SelectNoticeFileInfo = sqlSession.selectOne("SelectNoticeFileInfo", map);
		return SelectNoticeFileInfo;
	}

	@Override
	public void UpdateFile(Map<String, Object> map) {
		// 파일 삭제버튼을 누르면 작동하게된다.
		sqlSession.update("UpdateFile", map);
	}

	@Override
	public void UpdateTeamFile(Map<String, Object> map) {
		// 파일 삭제버튼을 누르면 작동하게된다.
		sqlSession.update("UpdateTeamFile", map);
	}

	@Override
	public void UpdateBoardDelete(int boardID) {
		sqlSession.update("UpdateBoardDelete", boardID);
	}

	@Override
	public void InsertTeamDocument(TeamBoard teamBoard) {
		sqlSession.insert("InsertTeamDocument", teamBoard);
	}

	@Override
	public int SelectTBoardID(TeamBoard teamBoard) {
		int Output = sqlSession.selectOne("SelectTBoardID", teamBoard);
		return Output;
	}

	@Override
	public TeamBoard SelectTeamBoardContent(String tBoardID) {
		return sqlSession.selectOne("SelectTeamBoardContent", tBoardID);
	}

	@Override
	public List<Map<String, Object>> SelectTeamBoardFileList(int bNo) {
		List<Map<String, Object>> SelectTeamBoardFileList = sqlSession.selectList("SelectTeamBoardFileList", bNo);
		return SelectTeamBoardFileList;
	}

	@Override
	public void UpdateTBoardDelete(int tBoardID) {
		sqlSession.update("UpdateTBoardDelete", tBoardID);
	}

	@Override
	public String SelectWriterID(TeamBoard teamBoard) {
		return sqlSession.selectOne("SelectWriterID", teamBoard);
	}

	@Override
	public Map<String, Object> SelectTeamBoardFileInfo(Map<String, Object> map) {
		Map<String, Object> SelectCommunityFileInfo = sqlSession.selectOne("SelectTeamBoardFileInfo", map);
		return SelectCommunityFileInfo;
	}

	@Override
	public List<Board> SelectMyBoardList(String login) {
		return sqlSession.selectList("SelectMyBoardList", login);
	}

}
