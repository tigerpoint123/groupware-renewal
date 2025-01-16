package com.ll.groupware_renewal.service;

import com.ll.groupware_renewal.entity.Board;
import com.ll.groupware_renewal.entity.TeamBoard;
import com.ll.groupware_renewal.repository.BoardJpaRepository;
import com.ll.groupware_renewal.repository.TeamBoardRepository;
import com.ll.groupware_renewal.util.BFileUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class BoardService {
	@Resource(name = "fileUtils")
	private BFileUtils BfileUtils;

	@Resource(name = "TfileUtils")
	private com.ll.groupware_renewal.util.TeamFileUtils TeamFileUtils;

	private final BoardJpaRepository boardJpaRepository;
	private final TeamBoardRepository teamBoardRepository;

	public List<Board> findCommunityBoardList() {
		return boardJpaRepository.findCommunityBoardList();
	}

	public List<Board> findNoticeBoardList() {
		return boardJpaRepository.findNoticeBoardList();
	}

	public void updateHitCountByBoardId(String boardID) {
		boardJpaRepository.updateHitCountByBoardId(boardID);
	}

	public void saveBoard(Board board, HttpServletRequest request) {
		boardJpaRepository.save(board);
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		try {
			Long BNo = board.getId();
			board.setBno(BNo);
			List<Map<String, Object>> List = BfileUtils.InsertFileInfo(board, multipartHttpServletRequest);
			for (int i = 0, size = List.size(); i < size; i++) {
				boardJpaRepository.insertFile(List.get(i));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveTeamDocument(TeamBoard teamBoard, HttpServletRequest request) {
		teamBoardRepository.save(teamBoard);
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		try {

			int TeamBoardID = teamBoard.getTBoardID();
			teamBoard.setTBno(TeamBoardID);
			List<Map<String, Object>> List = TeamFileUtils.InsertTeamFileInfo(teamBoard, multipartHttpServletRequest);
			for (int i = 0, Size = List.size(); i < Size; i++) {
				boardJpaRepository.InsertTeamFileInfo(List.get(i));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Board findCommunityContentByBoardId(String boardID) {
		return boardJpaRepository.findCommunityContentByBoardId(boardID);
	}

	public Board findNoticeContentByBoardId(String boardID) {
		return boardJpaRepository.findCommunityContentByBoardId(boardID);
	}

	public String findLoginUserID(String loginID) {
		return boardJpaRepository.findLoginUserID(loginID);
	}

	public void updateModifiedContent(Board board, String[] FileList, String[] fileNameList,
									  HttpServletRequest request) {
		boardJpaRepository.updateHitCountByBoardId(board.getId().toString());
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;

		List<Map<String, Object>> List;
		try {
			List = BfileUtils.UpdateFileInfo(board, FileList, fileNameList, multipartHttpServletRequest);
			Map<String, Object> TempMap = null;
			int Size = List.size();
			for (int i = 0; i < Size; i++) {
				TempMap = List.get(i);
				// 여기일단조심
				if (TempMap.get("IsNew").equals("1")) {
					boardJpaRepository.insertFile(TempMap);
				} else {
					boardJpaRepository.updateFile(TempMap);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updateTeamBoardModifiedContent(TeamBoard teamBoard, String[] fileList, String[] fileNameList,
											   HttpServletRequest request) {

		boardJpaRepository.updateTeamBoardModifiedContent(teamBoard);

		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;

		List<Map<String, Object>> List;
		try {
			List = TeamFileUtils.UpdateTeamBoardFileInfo(teamBoard, fileList, fileNameList,
					multipartHttpServletRequest);
			Map<String, Object> TempMap = null;
			int Size = List.size();
			for (int i = 0; i < Size; i++) {
				TempMap = List.get(i);
				// 여기일단조심
				if (TempMap.get("IsNew").equals("1")) {
					boardJpaRepository.insertFile(TempMap);
				} else {
					boardJpaRepository.updateFile(TempMap);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<Map<String, Object>> SelectCommunityFileList(int BNo) {
		List<Map<String, Object>> SelectCommunityFileList = boardJpaRepository.SelectCommunityFileList(BNo);
		return SelectCommunityFileList;
	}

	public List<Map<String, Object>> SelectTeamBoardFileList(int BNo) {
		List<Map<String, Object>> SelectTeamBoardFileList = boardJpaRepository.SelectTeamBoardFileList(BNo);
		return SelectTeamBoardFileList;
	}

	public Map<String, Object> SelectCommunityFileInfo(Map<String, Object> map) {
		Map<String, Object> SelectCommunityFileInfo = boardJpaRepository.SelectCommunityFileInfo(map);
		return SelectCommunityFileInfo;
	}

	public List<Map<String, Object>> SelectNoticeFileList(int BNo) {
		List<Map<String, Object>> SelectNoticeFileList = boardJpaRepository.SelectNoticeFileList(BNo);
		return SelectNoticeFileList;
	}

	public Map<String, Object> SelectNoticeFileInfo(Map<String, Object> map) {
		Map<String, Object> SelectNoticeFileInfo = boardJpaRepository.SelectNoticeFileInfo(map);
		return SelectNoticeFileInfo;
	}

	public void UpdateBoardDelete(int boardID) {
		boardJpaRepository.UpdateBoardDelete(boardID);
	}

	public List<TeamBoard> SelectTeamBoardList() {
		return boardJpaRepository.SelectTeamBoardList();
	}

	public TeamBoard SelectTeamBoardContent(String tBoardID) {
		return boardJpaRepository.SelectTeamBoardContent(tBoardID);
	}

	public void UpdateTBoardDelete(int tBoardID) {
		boardJpaRepository.UpdateTBoardDelete(tBoardID);
	}

	public String SelectWriterID(TeamBoard teamBoard) {
		return boardJpaRepository.SelectWriterID(teamBoard);
	}

	public Map<String, Object> SelectTeamBoardFileInfo(Map<String, Object> map) {
		Map<String, Object> SelectCommunityFileInfo = boardJpaRepository.SelectTeamBoardFileInfo(map);
		return SelectCommunityFileInfo;
	}

	public List<Board> SelectMyBoardList(String login) {
		return boardJpaRepository.SelectMyBoardList(login);
	}

}
