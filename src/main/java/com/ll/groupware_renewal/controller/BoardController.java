package com.ll.groupware_renewal.controller;

import com.ll.groupware_renewal.config.BoardConfig;
import com.ll.groupware_renewal.dto.Board;
import com.ll.groupware_renewal.dto.Inquiry;
import com.ll.groupware_renewal.dto.User;
import com.ll.groupware_renewal.function.UserInfoMethod;
import com.ll.groupware_renewal.service.*;
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
	private final BoardConfig boardConfig;
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

		return this.boardConfig.getString().getInquiryList().toString();
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

		model.addAttribute(this.boardConfig.getFields().getTitle().toString(), inquiry.getIBoardSubject());
		model.addAttribute(this.boardConfig.getFields().getWriter().toString(), inquiry.getIBoardWriter());
		model.addAttribute(this.boardConfig.getFields().getDate().toString(), inquiry.getIBoardDate());
		model.addAttribute(this.boardConfig.getFields().getContent().toString(), inquiry.getIBoardContent());
		model.addAttribute(this.boardConfig.getFields().getBoardID().toString(), IBoardID);
		model.addAttribute(this.boardConfig.getFields().getInquiryAnswer().toString(), inquiry.getIBoardAnswer());

		String UserID = inquiryService.SelectLoginUserIDForInquiry(LoginID);// 로그인한 사람의 userID를 가져오기 위함
		model.addAttribute(this.boardConfig.getFields().getUserID().toString(), UserID);
		model.addAttribute(this.boardConfig.getFields().getUserIDFromWriter().toString(), inquiry.getUserID());

		return this.boardConfig.getUrls().getInquiryContent().toString();
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
		model.addAttribute(this.boardConfig.getFields().getInquiryWriter().toString(), UserName);
		model.addAttribute(this.boardConfig.getFields().getInquiryEmail().toString(), UserEmail);
		model.addAttribute(this.boardConfig.getFields().getInquiryPhoneNum().toString(), UserPhoneNum);

		List<Inquiry> InquiryList = inquiryService.SelectInquiryList();
		model.addAttribute("inquiryList", InquiryList);

		return this.boardConfig.getUrls().getInquiryWrite().toString();
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
			
			return this.boardConfig.getUrls().getInquiryWrite().toString();
		} else if(Content.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter Out = response.getWriter();
			Out.println("<script>alert('내용을 입력해주세요. ');</script>");
			Out.flush();
			
			return this.boardConfig.getUrls().getInquiryWrite().toString();
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

		return this.boardConfig.getUrls().getReInquiryList().toString();
		}
	}

	@RequestMapping(value = "/InquiryDelete.do", method = RequestMethod.POST)
	public String deleteInquiry(HttpServletRequest request) {
		int IBoardID = Integer.parseInt(request.getParameter("boardID"));
		inquiryService.UpdateIBoardDelete(IBoardID);

		return this.boardConfig.getUrls().getReInquiryList().toString();
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

		return this.boardConfig.getUrls().getReInquiryList().toString();
	}

	@RequestMapping(value = "/AnswerDelete.do", method = RequestMethod.POST)
	public String deleteInquiryAnswer(HttpServletRequest request) {
		int IBoardID = Integer.parseInt(request.getParameter("boardID"));
		inquiryService.DeleteInquiryAnswer(IBoardID);

		return this.boardConfig.getUrls().getReInquiryList().toString();
	}

	// 공지사항 리스트
	@RequestMapping(value = "/noticeList", method = RequestMethod.GET)
	public String noticeList(User user, HttpServletRequest request, Model model, Principal principal) {
		if (principal != null) {
			// 유저 정보
			GetUserInformation(principal, user, model);
		}
		List<Board> NoticeList = boardService.SelectNoticeBoardList();
		model.addAttribute("noticeList", NoticeList);

		return this.boardConfig.getUrls().getRNoticeList().toString();
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

		model.addAttribute(this.boardConfig.getFields().getNoticeWriter().toString(), UserName);
		model.addAttribute(this.boardConfig.getFields().getBoardDate().toString(), Date.format(Now));

		List<Board> NoticeList = boardService.SelectNoticeBoardList();
		model.addAttribute("noticeList", NoticeList);

		return this.boardConfig.getUrls().getRNoticeWrite().toString();
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
			
			return this.boardConfig.getUrls().getRNoticeWrite().toString();
		} else if(Content.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter Out = response.getWriter();
			Out.println("<script>alert('내용을 입력해주세요. ');</script>");
			Out.flush();
			
			return this.boardConfig.getUrls().getRNoticeWrite().toString();
		} else {
		
		board.setBoardSubject(Title);
		board.setBoardContent(Content);
		board.setBoardWriter(UserName);
		board.setBoardDate(Date.format(Now));
		board.setUserID(UserID);
		board.setBoardType("공지사항");

		boardService.InsertBoard(board, request);

		return this.boardConfig.getUrls().getRNoticeList().toString();
		}
	}

	// 공지사항 글 수정
	@RequestMapping(value = "/noticeModify", method = RequestMethod.GET)
	public String noticeModify(User user, Model model, Board board, Principal principal, HttpServletRequest request) {

		// 유저 정보
		GetUserInformation(principal, user, model);
		//
		String BoardID = request.getParameter("boardID");
		board = boardService.SelectOneNoticeContent(BoardID);
		model.addAttribute(this.boardConfig.getFields().getNoticeTitle().toString(), board.getBoardSubject());
		model.addAttribute(this.boardConfig.getFields().getNoticeWriter().toString(), board.getBoardWriter());
		model.addAttribute("Date", board.getBoardDate());
		model.addAttribute(this.boardConfig.getFields().getNoticeContent().toString(), board.getBoardContent());
		model.addAttribute(this.boardConfig.getFields().getBoardID().toString(), board.getBoardID());
		model.addAttribute(this.boardConfig.getFields().getBoardType().toString(), board.getBoardType());

		// 수정된 file을 보여주는곳
		List<Map<String, Object>> NoticeFileList = boardService.SelectNoticeFileList(Integer.parseInt(BoardID));
		model.addAttribute("NoticeFile", NoticeFileList);

		return this.boardConfig.getUrls().getNoticeModify().toString();
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

		boardService.UpdateModifiedContent(board, FileList, FileNameList, request);

		return this.boardConfig.getUrls().getRNoticeList().toString();
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
		boardService.UpdateHitCount(BoardID);

		/*-----------------------------------*/
		board = boardService.SelectOneCommunityContent(BoardID); // 선택한 게시글을 쓴 userID가 들어감.
		model.addAttribute(this.boardConfig.getFields().getNoticeTitle().toString(), board.getBoardSubject());
		model.addAttribute(this.boardConfig.getFields().getNoticeWriter().toString(), board.getBoardWriter());
		model.addAttribute("Date", board.getBoardDate());
		model.addAttribute(this.boardConfig.getFields().getNoticeContent().toString(), board.getBoardContent());
		model.addAttribute(this.boardConfig.getFields().getBoardID().toString(), BoardID);
		model.addAttribute("BoardType", board.getBoardType());

		String UserID = boardService.SelectLoginUserID(LoginID);// 로그인한 사람의 userID를 가져오기 위함
		model.addAttribute(this.boardConfig.getFields().getUserID().toString(), UserID);
		model.addAttribute(this.boardConfig.getFields().getUserIDFromWriter().toString(), board.getUserID());

		List<Map<String, Object>> NoticeFileList = boardService.SelectNoticeFileList(Integer.parseInt(BoardID));
		model.addAttribute("NoticeFile", NoticeFileList);

		return this.boardConfig.getUrls().getRNoticeContent().toString();
	}

	@RequestMapping(value = "/NoticeDelete.do", method = RequestMethod.POST)
	public String deleteNotice(HttpServletRequest request) {
		int BoardID = Integer.parseInt(request.getParameter("boardID"));
		boardService.UpdateBoardDelete(BoardID);

		return this.boardConfig.getUrls().getRNoticeList().toString();
	}

	// 커뮤니티 리스트
	@RequestMapping(value = "/communityList", method = RequestMethod.GET)
	public String communityList(User user, HttpServletRequest request, Model model, Principal principal) {
		if (principal != null) {
			// 유저 정보
			GetUserInformation(principal, user, model);
		}
		List<Board> CommunityList = boardService.SelectCommunityBoardList();
		model.addAttribute("communityList", CommunityList);

		return this.boardConfig.getUrls().getRCommunityList().toString();
	}

	// 커뮤니티 글 작성
	@RequestMapping(value = "/communityWrite", method = RequestMethod.GET)
	public String communityWrite(User user, HttpServletRequest request, Model model, Principal principal) {
		List<Board> CommunityList = boardService.SelectCommunityBoardList();
		// 유저 정보
		GetUserInformation(principal, user, model);

		// 작성자 이름 자동 세팅 (disabled)
		String UserLoginID = principal.getName();
		String UserName = userService.SelectUserName(UserLoginID);
		Date Now = new Date();
		SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd");

		model.addAttribute(this.boardConfig.getFields().getCommunityWriter().toString(), UserName);
		model.addAttribute(this.boardConfig.getFields().getBoardDate().toString(), Date.format(Now));
		model.addAttribute("communityList", CommunityList);

		return this.boardConfig.getUrls().getRCommunityWrite().toString();
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
			
			return this.boardConfig.getUrls().getRCommunityWrite().toString();
		} else if(Content.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter Out = response.getWriter();
			Out.println("<script>alert('내용을 입력해주세요. ');</script>");
			Out.flush();
			
			return this.boardConfig.getUrls().getRCommunityWrite().toString();
		} else {
		
		board.setBoardSubject(Title);
		board.setBoardContent(Content);
		board.setBoardWriter(UserName);
		board.setBoardDate(Date.format(Now));
		board.setUserID(UserID);
		board.setBoardType("커뮤니티");

		boardService.InsertBoard(board, request);

		return this.boardConfig.getUrls().getRrCommunityList().toString();
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
		board = boardService.SelectOneCommunityContent(BoardID);
		model.addAttribute(this.boardConfig.getFields().getCommunityTitle().toString(), board.getBoardSubject());
		model.addAttribute(this.boardConfig.getFields().getCommunityWriter().toString(), board.getBoardWriter());
		model.addAttribute("Date", board.getBoardDate());
		model.addAttribute(this.boardConfig.getFields().getCommunityContent().toString(), board.getBoardContent());
		model.addAttribute(this.boardConfig.getFields().getBoardID().toString(), board.getBoardID());

		// 수정된 file을 보여주는곳
		List<Map<String, Object>> CommunityFile = boardService.SelectCommunityFileList(Integer.parseInt(BoardID));
		model.addAttribute("CommunityFile", CommunityFile);

		return this.boardConfig.getUrls().getRCommunityModify().toString();
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

		boardService.UpdateModifiedContent(board, FileList, FileNameList, request);

		return this.boardConfig.getUrls().getRrCommunityList().toString();
	}

	@RequestMapping(value = "/FileDown")
	public void fileDown(@RequestParam Map<String, Object> map, HttpServletResponse response) throws Exception {

		// xml처리는 준현맨이 해준다구!
		Map<String, Object> ResultMap = boardService.SelectCommunityFileInfo(map);
		String StoredFileName = (String) ResultMap.get("BStoredFileName");
		String OriginalFileName = (String) ResultMap.get("BOriginalFileName");
		// 파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환한다.
		byte FileByte[] = org.apache.commons.io.FileUtils
				.readFileToByteArray(new File(this.boardConfig.getFile().getPath() + StoredFileName));
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
		boardService.UpdateHitCount(BoardID);
		/*-----------------------------------*/
		board = boardService.SelectOneCommunityContent(BoardID); // 선택한 게시글을 쓴 userID가 들어감.
		model.addAttribute(this.boardConfig.getFields().getCommunityTitle().toString(), board.getBoardSubject());
		model.addAttribute(this.boardConfig.getFields().getCommunityWriter().toString(), board.getBoardWriter());
		model.addAttribute("Date", board.getBoardDate());
		model.addAttribute(this.boardConfig.getFields().getCommunityContent().toString(), board.getBoardContent());
		model.addAttribute(this.boardConfig.getFields().getBoardID().toString(), BoardID);

		String UserID = boardService.SelectLoginUserID(LoginID);// 로그인한 사람의 userID를 가져오기 위함
		model.addAttribute(this.boardConfig.getFields().getUserID().toString(), UserID);
		model.addAttribute(this.boardConfig.getFields().getUserIDFromWriter().toString(), board.getUserID());

		List<Map<String, Object>> CommunityFile = boardService.SelectCommunityFileList(Integer.parseInt(BoardID));
		model.addAttribute("CommunityFile", CommunityFile);

		return this.boardConfig.getUrls().getCommunityContent().toString();
	}

	@RequestMapping(value = "/CommunityDelete.do", method = RequestMethod.POST)
	public String deleteCommunity(HttpServletRequest request) {
		int BoardID = Integer.parseInt(request.getParameter("boardID"));
		boardService.UpdateBoardDelete(BoardID);

		return this.boardConfig.getUrls().getRrCommunityList().toString();
	}

	private void GetUserInformation(Principal principal, User user, Model model) {
		String LoginID = principal.getName();// 로그인 한 아이디
		ArrayList<String> SelectUserProfileInfo = new ArrayList<String>();
		SelectUserProfileInfo = userService.SelectUserProfileInfo(LoginID);
		user.setUserLoginID(LoginID);
		if (SelectUserProfileInfo.get(2).equals(this.boardConfig.getFields().getStudent())) {
			ArrayList<String> StudentInfo = new ArrayList<String>();
			StudentInfo = studentService.SelectStudentProfileInfo(SelectUserProfileInfo.get(1));
			userInfoMethod.StudentInfo(model, SelectUserProfileInfo, StudentInfo);
		} else if (SelectUserProfileInfo.get(2).equals(this.boardConfig.getFields().getProfessor())) {
			ArrayList<String> ProfessorInfo = new ArrayList<String>();
			ProfessorInfo = professorService.SelectProfessorProfileInfo(SelectUserProfileInfo.get(1));
			userInfoMethod.ProfessorInfo(model, SelectUserProfileInfo, ProfessorInfo);
		} else if (SelectUserProfileInfo.get(2).equals(this.boardConfig.getFields().getAdministrator())) {
			userInfoMethod.AdministratorInfo(model, SelectUserProfileInfo);
		}
	}

}