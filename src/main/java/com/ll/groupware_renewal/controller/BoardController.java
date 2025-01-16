package com.ll.groupware_renewal.controller;

import com.ll.groupware_renewal.constant.ConstantBoard;
import com.ll.groupware_renewal.entity.Board;
import com.ll.groupware_renewal.entity.Inquiry;
import com.ll.groupware_renewal.entity.User;
import com.ll.groupware_renewal.service.*;
import com.ll.groupware_renewal.util.UserInfoMethod;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;
	private final InquiryService inquiryService;
	private final UserService userService;
	private final StudentService studentService;
	private final UserInfoMethod userInfoMethod;
	private final ProfessorService professorService;

	// 문의 리스트
	@RequestMapping(value = "/inquiryList", method = RequestMethod.GET)
	public String inquiryList(User user, Principal principal, Model model, HttpServletRequest request) {

		if (principal != null) {
			GetUserInformation(principal, user, model);
		}
		List<Inquiry> InquiryList = inquiryService.SelectInquiryList();
		model.addAttribute("inquiryList", InquiryList);

		return ConstantBoard.RInquiryList;
	}

	// 문의 글 내용
	@RequestMapping(value = "/inquiryContent", method = RequestMethod.GET)
	public String inquiryContent(User user, Principal principal, HttpServletRequest request, Model model,
			Inquiry inquiry) {

		if (principal != null) {
			GetUserInformation(principal, user, model);
		}
		String LoginID = principal.getName();
		String IBoardID = request.getParameter("no");
		inquiry = inquiryService.SelectOneInquiryContent(IBoardID); // 선택한 게시글 ID가 들어감.

		model.addAttribute(ConstantBoard.InquiryTitle, inquiry.getIBoardSubject());
		model.addAttribute(ConstantBoard.InquiryWriter, inquiry.getIBoardWriter());
		model.addAttribute(ConstantBoard.IBoardDate, inquiry.getIBoardDate());
		model.addAttribute(ConstantBoard.InquiryContent, inquiry.getIBoardContent());
		model.addAttribute(ConstantBoard.BoardID, IBoardID);
		model.addAttribute(ConstantBoard.InquiryAnswer, inquiry.getIBoardAnswer());

		String UserID = inquiryService.SelectLoginUserIDForInquiry(LoginID);// 로그인한 사람의 userID를 가져오기 위함
		model.addAttribute(ConstantBoard.UserID, UserID);
		model.addAttribute(ConstantBoard.UserIDFromWriter, inquiry.getUserID());

		return ConstantBoard.RInquiryContent;
	}

	// 문의 글 작성
	@RequestMapping(value = "/inquiryWrite", method = RequestMethod.GET)
	public String inquiryWrite(Locale locale, User user, Principal principal, Model model) {

		if (principal != null) {
			GetUserInformation(principal, user, model);
		}

		// 작성자 이름 자동 세팅 (disabled)
		String UserLoginID = principal.getName();
		String UserName = userService.SelectUserName(UserLoginID);
		String UserEmail = userService.SelectEmailForInquiry(UserLoginID);
		String UserPhoneNum = userService.SelectPhoneNumForInquiry(UserLoginID);
		model.addAttribute(ConstantBoard.InquiryWriter, UserName);
		model.addAttribute(ConstantBoard.InquiryEmail, UserEmail);
		model.addAttribute(ConstantBoard.InquiryPhoneNum, UserPhoneNum);

		List<Inquiry> InquiryList = inquiryService.SelectInquiryList();
		model.addAttribute("inquiryList", InquiryList);

		return ConstantBoard.RInquiryWrite;
	}

	@RequestMapping(value = "/InquiryWrite", method = RequestMethod.POST)
	public String DoInquiryeWrite(Principal principal, HttpServletRequest request, User user, Inquiry inquiry,
			Model model, HttpServletResponse response) throws Exception {

		if (principal != null) {
			GetUserInformation(principal, user, model);
		}

		Date Now = new Date();
		String Title = request.getParameter("InquiryTitle");
		String Content = request.getParameter("InquiryContent");
		String InquiryType = request.getParameter("InquiryType");
		SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String UserLoginID = principal.getName();
		int UserID = userService.SelectUserIDFromBoardController(UserLoginID);
		String UserName = userService.SelectUserName(UserLoginID);
		String UserEmail = userService.SelectEmailForInquiry(UserLoginID);
		String UserPhoneNum = userService.SelectPhoneNumForInquiry(UserLoginID);


		if(Title.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter Out = response.getWriter();
			Out.println("<script>alert('제목을 입력해주세요. ');</script>");
			Out.flush();
			
			return ConstantBoard.RInquiryWrite;
		} else if(Content.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter Out = response.getWriter();
			Out.println("<script>alert('내용을 입력해주세요. ');</script>");
			Out.flush();
			
			return ConstantBoard.RInquiryWrite;
		} else {
		
		inquiry.setIBoardSubject(Title);
		inquiry.setIBoardContent(Content);
		inquiry.setIBoardWriter(UserName);
		inquiry.setIBoardDate(Date.format(Now));
		inquiry.setUserID(UserID);
		inquiry.setIBoardType(InquiryType);
		inquiry.setState("답변 대기");
		inquiry.setUserEmail(UserEmail);
		inquiry.setUserPhoneNum(UserPhoneNum);

		inquiryService.InsertInquiry(inquiry, request);

		return ConstantBoard.RRInquiryList;
		}
	}

	@RequestMapping(value = "/InquiryDelete.do", method = RequestMethod.POST)
	public String deleteInquiry(HttpServletRequest request) {
		int IBoardID = Integer.parseInt(request.getParameter("boardID"));
		inquiryService.UpdateIBoardDelete(IBoardID);

		return ConstantBoard.RInquiryList;
	}

	@RequestMapping(value = "/Answer.do", method = RequestMethod.POST)
	public String DoInquiryAnswer(Principal principal, HttpServletRequest request, User user, Inquiry inquiry,
			Model model) throws Exception {

		GetUserInformation(principal, user, model);

		String IBoardAnswer = request.getParameter("InquiryAnswer");

		int IBoardID = Integer.parseInt(request.getParameter("BoardID"));

		inquiry.setIBoardAnswer(IBoardAnswer);
		inquiry.setState("답변 완료");
		inquiry.setIBoardID(IBoardID);

		inquiryService.InsertInquiryAnswer(inquiry, request);

		return ConstantBoard.RRInquiryList;
	}

	@RequestMapping(value = "/AnswerDelete.do", method = RequestMethod.POST)
	public String deleteInquiryAnswer(HttpServletRequest request) {
		int IBoardID = Integer.parseInt(request.getParameter("boardID"));
		inquiryService.DeleteInquiryAnswer(IBoardID);

		return ConstantBoard.RRInquiryList;
	}

	// 공지사항 리스트
	@RequestMapping(value = "/noticeList", method = RequestMethod.GET)
	public String noticeList(User user, HttpServletRequest request, Model model, Principal principal) {
		if (principal != null) {
			// 유저 정보
			GetUserInformation(principal, user, model);
		}
		List<Board> NoticeList = boardService.findNoticeBoardList();
		model.addAttribute("noticeList", NoticeList);

		return ConstantBoard.RNoticeList;
	}

	// 공지사항 글 작성
	@RequestMapping(value = "/noticeWrite", method = RequestMethod.GET)
	public String noticeWrite(User user, HttpServletRequest request, Model model, Principal principal) {
		// 유저 정보

		GetUserInformation(principal, user, model);

		// 작성자 이름 자동 세팅 (disabled)
		String UserLoginID = principal.getName();
		String UserName = userService.SelectUserName(UserLoginID);
		Date Now = new Date();
		SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd");

		model.addAttribute(ConstantBoard.NoticeWriter, UserName);
		model.addAttribute(ConstantBoard.BoardDate, Date.format(Now));

		List<Board> NoticeList = boardService.findNoticeBoardList();
		model.addAttribute("noticeList", NoticeList);

		return ConstantBoard.RNoticeWrite;
	}

	@RequestMapping(value = "/noticeWrite", method = RequestMethod.POST)
	public String DoNoticeWrite(Principal principal, HttpServletRequest request, User user, Board board, Model model, HttpServletResponse response)
			throws Exception {
		// 유저 정보
		GetUserInformation(principal, user, model);
		//

		Date Now = new Date();
		String Title = request.getParameter("NoticeTitle");
		String Content = request.getParameter("NoticeContent");
		SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String UserLoginID = principal.getName();
		int UserID = userService.SelectUserIDFromBoardController(UserLoginID);
		String UserName = userService.SelectUserName(UserLoginID);

		if(Title.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter Out = response.getWriter();
			Out.println("<script>alert('제목을 입력해주세요. ');</script>");
			Out.flush();
			
			return ConstantBoard.RNoticeWrite;
		} else if(Content.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter Out = response.getWriter();
			Out.println("<script>alert('내용을 입력해주세요. ');</script>");
			Out.flush();
			
			return ConstantBoard.RNoticeWrite;
		} else {
		
		board.setBoardSubject(Title);
		board.setBoardContent(Content);
		board.setBoardWriter(UserName);
		board.setBoardDate(Date.format(Now));
		board.setUserID(UserID);
		board.setBoardType("공지사항");

		boardService.saveBoard(board, request);

		return ConstantBoard.RRNoticeList;
		}
	}

	// 공지사항 글 수정
	@RequestMapping(value = "/noticeModify", method = RequestMethod.GET)
	public String noticeModify(User user, Model model, Board board, Principal principal, HttpServletRequest request) {

		// 유저 정보
		GetUserInformation(principal, user, model);
		//
		String BoardID = request.getParameter("boardID");
		board = boardService.findNoticeContentByBoardId(BoardID);
		model.addAttribute(ConstantBoard.NoticeTitle, board.getBoardSubject());
		model.addAttribute(ConstantBoard.NoticeWriter, board.getBoardWriter());
		model.addAttribute("Date", board.getBoardDate());
		model.addAttribute(ConstantBoard.NoticeContent, board.getBoardContent());
		model.addAttribute(ConstantBoard.BoardID, board.getBoardID());
		model.addAttribute(ConstantBoard.BoardType, board.getBoardType());

		// 수정된 file을 보여주는곳
		List<Map<String, Object>> NoticeFileList = boardService.SelectNoticeFileList(Integer.parseInt(BoardID));
		model.addAttribute("NoticeFile", NoticeFileList);

		return ConstantBoard.RNoticeModify;
	}

	@RequestMapping(value = "/NoticeModify", method = RequestMethod.POST)
	public String noticeModifyDO(Model model, Board board, HttpServletRequest request, RedirectAttributes rttr,
			Principal principal, @RequestParam(value = "FileDeleteList[]") String[] FileList,
			@RequestParam(value = "FileDeleteNameList[]") String[] FileNameList,
			@RequestParam(value = "BoardID") String BoardID) {

		Date Now = new Date();
		String Title = request.getParameter("NoticeTitle");
		String Content = request.getParameter("NoticeContent");
		SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String UserLoginID = principal.getName();
		int BoardID2 = Integer.parseInt(request.getParameter("BoardID"));
		String UserName = userService.SelectUserName(UserLoginID);

		board.setBno(BoardID2);
		board.setBoardSubject(Title);
		board.setBoardContent(Content);
		board.setBoardWriter(UserName);
		board.setBoardDate(Date.format(Now));
		board.setBoardID(BoardID2);
		board.setBoardType("공지사항");

		boardService.updateModifiedContent(board, FileList, FileNameList, request);

		return ConstantBoard.RRNoticeList;
	}

	// 공지사항 리스트에서 제목 선택시 내용 출력
	@RequestMapping(value = "/noticeContent", method = RequestMethod.GET)
	public String noticeContent(User user, Principal principal, HttpServletRequest request, Model model, Board board) {
		String LoginID = "";

		if (principal != null) {
			// 유저 정보
			GetUserInformation(principal, user, model);
			LoginID = principal.getName();// 로그인 한 아이디
		}
		// 누르면 조회수 증가하는 로직
		String BoardID = request.getParameter("no");
		boardService.updateHitCountByBoardId(BoardID);

		/*-----------------------------------*/
		board = boardService.findCommunityContentByBoardId(BoardID); // 선택한 게시글을 쓴 userID가 들어감.
		model.addAttribute("NoticeTitle", board.getBoardSubject());
		model.addAttribute("NoticeWriter", board.getBoardWriter());
		model.addAttribute("BoardDate", board.getBoardDate());
		model.addAttribute("NoticeContent", board.getBoardContent());
		model.addAttribute("BoardID", BoardID);
		model.addAttribute("BoardType", board.getBoardType());

		String UserID = boardService.findLoginUserID(LoginID);// 로그인한 사람의 userID를 가져오기 위함
		model.addAttribute("UserID", UserID);
		model.addAttribute("UserIDFromWriter", board.getUserID());

		List<Map<String, Object>> NoticeFileList = boardService.SelectNoticeFileList(Integer.parseInt(BoardID));
		model.addAttribute("NoticeFile", NoticeFileList);

		return ConstantBoard.RNoticeContent;
	}

	@RequestMapping(value = "/NoticeDelete.do", method = RequestMethod.POST)
	public String deleteNotice(HttpServletRequest request) {
		int BoardID = Integer.parseInt(request.getParameter("boardID"));
		boardService.UpdateBoardDelete(BoardID);

		return ConstantBoard.RRNoticeList;
	}

	// 커뮤니티 리스트
	@RequestMapping(value = "/communityList", method = RequestMethod.GET)
	public String communityList(User user, HttpServletRequest request, Model model, Principal principal) {
		if (principal != null) {
			// 유저 정보
			GetUserInformation(principal, user, model);
		}
		List<Board> CommunityList = boardService.findCommunityBoardList();
		model.addAttribute("communityList", CommunityList);

		return ConstantBoard.RCommunityList;
	}

	// 커뮤니티 글 작성
	@RequestMapping(value = "/communityWrite", method = RequestMethod.GET)
	public String communityWrite(User user, HttpServletRequest request, Model model, Principal principal) {
		List<Board> CommunityList = boardService.findCommunityBoardList();
		// 유저 정보
		GetUserInformation(principal, user, model);

		// 작성자 이름 자동 세팅 (disabled)
		String UserLoginID = principal.getName();
		String UserName = userService.SelectUserName(UserLoginID);
		Date Now = new Date();
		SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd");

		model.addAttribute(ConstantBoard.CommunityWriter, UserName);
		model.addAttribute(ConstantBoard.BoardDate, Date.format(Now));
		model.addAttribute("communityList", CommunityList);

		return ConstantBoard.RCommunityWrite;
	}

	@RequestMapping(value = "/communityWrite", method = RequestMethod.POST)
	public String communityWriteDo(Principal principal, HttpServletRequest request, User user, Board board,
			Model model, HttpServletResponse response) throws IOException {
		// 유저 정보
		GetUserInformation(principal, user, model);
		//
		Date Now = new Date();
		String Title = request.getParameter("CommunityTitle");
		String Content = request.getParameter("CommunityContent");
		SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String UserLoginID = principal.getName();
		int UserID = userService.SelectUserIDFromBoardController(UserLoginID);
		String UserName = userService.SelectUserName(UserLoginID);


		if(Title.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter Out = response.getWriter();
			Out.println("<script>alert('제목을 입력해주세요. ');</script>");
			Out.flush();
			
			return ConstantBoard.RCommunityWrite;
		} else if(Content.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter Out = response.getWriter();
			Out.println("<script>alert('내용을 입력해주세요. ');</script>");
			Out.flush();
			
			return ConstantBoard.RCommunityWrite;
		} else {
		
		board.setBoardSubject(Title);
		board.setBoardContent(Content);
		board.setBoardWriter(UserName);
		board.setBoardDate(Date.format(Now));
		board.setUserID(UserID);
		board.setBoardType("커뮤니티");

		boardService.saveBoard(board, request);

		return ConstantBoard.RRCommunityList;
		}
	}

	// 커뮤니티 글 수정
	@RequestMapping(value = "/communityModify", method = RequestMethod.GET)
	public String communityModify(User user, Model model, Board board, Principal principal,
			HttpServletRequest request) {
		// 유저 정보
		GetUserInformation(principal, user, model);
		//
		String BoardID = request.getParameter("no");
		board = boardService.findCommunityContentByBoardId(BoardID);
		model.addAttribute(ConstantBoard.CommunityTitle, board.getBoardSubject());
		model.addAttribute(ConstantBoard.CommunityWriter, board.getBoardWriter());
		model.addAttribute("Date", board.getBoardDate());
		model.addAttribute(ConstantBoard.CommunityContent, board.getBoardContent());
		model.addAttribute(ConstantBoard.BoardID, board.getBoardID());

		// 수정된 file을 보여주는곳
		List<Map<String, Object>> CommunityFile = boardService.SelectCommunityFileList(Integer.parseInt(BoardID));
		model.addAttribute("CommunityFile", CommunityFile);

		return ConstantBoard.RCommunityModify;
	}

	@RequestMapping(value = "/CommunityModify.do", method = RequestMethod.POST)
	public String communityModifyDO(Model model, Board board, HttpServletRequest request, RedirectAttributes rttr,
			Principal principal, @RequestParam(value = "FileDeleteList[]") String[] FileList,
			@RequestParam(value = "FileDeleteNameList[]") String[] FileNameList,
			@RequestParam(value = "BoardID") String BoardID) {
		Date Now = new Date();
		String Title = request.getParameter("CommunityTitle");
		String Content = request.getParameter("CommunityContent");
		SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int BoardID2 = Integer.parseInt(request.getParameter("BoardID"));
		String UserLoginID = principal.getName();// 로그인 한 아이디
		String UserName = userService.SelectUserName(UserLoginID);

		board.setBno(BoardID2);
		board.setBoardSubject(Title);
		board.setBoardContent(Content);
		board.setBoardWriter(UserName);
		board.setBoardDate(Date.format(Now));
		board.setBoardID(BoardID2);

		boardService.updateModifiedContent(board, FileList, FileNameList, request);

		return ConstantBoard.RRCommunityList;
	}

	@RequestMapping(value = "/FileDown")
	public void fileDown(@RequestParam Map<String, Object> map, HttpServletResponse response) throws Exception {

		// xml처리는 준현맨이 해준다구!
		Map<String, Object> ResultMap = boardService.SelectCommunityFileInfo(map);
		String StoredFileName = (String) ResultMap.get("BStoredFileName");
		String OriginalFileName = (String) ResultMap.get("BOriginalFileName");
		// 파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환한다.
		byte FileByte[] = org.apache.commons.io.FileUtils
				.readFileToByteArray(new File(ConstantBoard.FilePath + StoredFileName));
		response.setContentType("application/octet-stream");
		response.setContentLength(FileByte.length);
		response.setHeader("Content-Disposition",
				"attachment; fileName=\"" + URLEncoder.encode(OriginalFileName, "UTF-8") + "\";");
		response.getOutputStream().write(FileByte);
		response.getOutputStream().flush();
		response.getOutputStream().close();

	}

	// 커뮤니티 리스트에서 제목 선택시 내용 출력
	@RequestMapping(value = "/communityContent", method = RequestMethod.GET)
	public String communityContent(User user, Principal principal, HttpServletRequest request, Model model,
			Board board) {
		// 유저 정보
		String LoginID = principal.getName();// 로그인 한 아이디
		GetUserInformation(principal, user, model); //
		// 누르면 조회수 증가하는 로직
		String BoardID = request.getParameter("no");
		boardService.updateHitCountByBoardId(BoardID);
		/*-----------------------------------*/
		board = boardService.findCommunityContentByBoardId(BoardID); // 선택한 게시글을 쓴 userID가 들어감.
		model.addAttribute(ConstantBoard.CommunityTitle, board.getBoardSubject());
		model.addAttribute(ConstantBoard.CommunityWriter, board.getBoardWriter());
		model.addAttribute(ConstantBoard.BoardDate, board.getBoardDate());
		model.addAttribute(ConstantBoard.CommunityContent, board.getBoardContent());
		model.addAttribute(ConstantBoard.BoardID, BoardID);

		String UserID = boardService.findLoginUserID(LoginID);// 로그인한 사람의 userID를 가져오기 위함
		model.addAttribute(ConstantBoard.UserID, UserID);
		model.addAttribute(ConstantBoard.UserIDFromWriter, board.getUserID());

		List<Map<String, Object>> CommunityFile = boardService.SelectCommunityFileList(Integer.parseInt(BoardID));
		model.addAttribute("CommunityFile", CommunityFile);

		return ConstantBoard.RCommunityContent;
	}

	@RequestMapping(value = "/CommunityDelete.do", method = RequestMethod.POST)
	public String deleteCommunity(HttpServletRequest request) {
		int BoardID = Integer.parseInt(request.getParameter("boardID"));
		boardService.UpdateBoardDelete(BoardID);

		return ConstantBoard.RRCommunityList;
	}

	private void GetUserInformation(Principal principal, User user, Model model) {
		String LoginID = principal.getName();// 로그인 한 아이디
		ArrayList<String> SelectUserProfileInfo = new ArrayList<String>();
		SelectUserProfileInfo = userService.SelectUserProfileInfo(LoginID);
		user.setUserLoginID(LoginID);
		if (SelectUserProfileInfo.get(2).equals(ConstantBoard.STUDENT)) {
			ArrayList<String> StudentInfo = new ArrayList<String>();
			StudentInfo = studentService.SelectStudentProfileInfo(SelectUserProfileInfo.get(1));
			userInfoMethod.StudentInfo(model, SelectUserProfileInfo, StudentInfo);
		} else if (SelectUserProfileInfo.get(2).equals(ConstantBoard.PROFESSOR)) {
			ArrayList<String> ProfessorInfo = new ArrayList<String>();
			ProfessorInfo = professorService.SelectProfessorProfileInfo(SelectUserProfileInfo.get(1));
			userInfoMethod.ProfessorInfo(model, SelectUserProfileInfo, ProfessorInfo);
		} else if (SelectUserProfileInfo.get(2).equals(ConstantBoard.ADMINISTRATOR)) {
			userInfoMethod.AdministratorInfo(model, SelectUserProfileInfo);
		}
	}

}