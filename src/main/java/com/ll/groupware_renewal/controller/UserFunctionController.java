package com.ll.groupware_renewal.controller;

import com.ll.groupware_renewal.constant.*;
import com.ll.groupware_renewal.entity.*;
import com.ll.groupware_renewal.util.UserInfoMethod;
import com.ll.groupware_renewal.service.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserFunctionController {
   private final UserService userService;
   private final StudentService studentService;
   private final ProfessorService professorService;
   private final EmailService emailService;
   private final UserEmailService userEmailService;
   private final UserInfoMethod userInfoMethod;
   private final BoardService boardService;
   private final InquiryService inquiryService;
   private final GenericXmlApplicationContext ctx;

   private String StudentColleges;
   private String UserLoginID;

   private String StudentGender;
   private String StudentGradeForSignUp;
   private String StudentMajor;
   private String StudentDoubleMajor;
   private String ProfessorColleges;
   private String ProfessorMajor;
   private String ProfessorRoom;
   private String ProfessorRoomNum;
   private String UserEmail;
   private boolean IDChecker = false;
   private boolean EmailChecker = false;
   private boolean NameChecker = false;
   private boolean EmailCheck = true;
   private String Address;
   private Date Now;
   private Calendar Calendear;
   private DateFormat DateFormat;

   @RequestMapping(value = "/findPassword", method = RequestMethod.GET)
   public String findPassword() {
      return ConstantUserFunction.FPUrl;
   }

   /* 이메일 인증 후 비밀번호 보여주기 */
   @RequestMapping(value = "/showPassword", method = RequestMethod.GET)
   public String showPassword(User user, RedirectAttributes redirectAttributes, Model model,
                              HttpServletRequest request, HttpServletResponse response) throws IOException {
      return ConstantUserFunction.SPUrl;
   }

   // home 로그인 완료 화면 + 날짜 업데이트
   @RequestMapping(value = "/home", method = RequestMethod.GET)
   public String home(User user, Principal principal, Model model, HttpServletRequest request, Student student,
         Professor professor) {
      if (principal != null) {
         // 유저 정보
         String LoginID = principal.getName();// 로그인 한 아이디
         ArrayList<String> SelectUserProfileInfo = new ArrayList<String>();
         SelectUserProfileInfo = userService.SelectUserProfileInfo(LoginID);
         int UserID = Integer.parseInt(userService.SelectUserIDForDate(LoginID));
         user.setUserLoginID(LoginID);

         // 휴먼계정 여부 확인 및 update
         boolean DormantCheck = userService.SelectDormant(LoginID);
         if (DormantCheck) {
            userService.UpdateRecoveryDormant(LoginID);
         }

         if (SelectUserProfileInfo.get(2).equals(ConstantUserFunction.SRole)) {
            ArrayList<String> StudentInfo = new ArrayList<String>();
            StudentInfo = studentService.SelectStudentProfileInfo(SelectUserProfileInfo.get(1));

            userInfoMethod.StudentInfo(model, SelectUserProfileInfo, StudentInfo);
         } else if (SelectUserProfileInfo.get(2).equals(ConstantUserFunction.PRole)) {

            ArrayList<String> ProfessorInfo = new ArrayList<String>();
            ProfessorInfo = professorService.SelectProfessorProfileInfo(SelectUserProfileInfo.get(1));

            userInfoMethod.ProfessorInfo(model, SelectUserProfileInfo, ProfessorInfo);
         } else if (SelectUserProfileInfo.get(2).equals(ConstantUserFunction.ARole)) {
            userInfoMethod.AdministratorInfo(model, SelectUserProfileInfo);
         }
         Date Now = new Date();
         SimpleDateFormat Date = new SimpleDateFormat(ConstantUserFunction.DFormat);
         user.setDate(Date.format(Now));
         student.setDate(Date.format(Now));
         student.setUserID(UserID);
         professor.setDate(Date.format(Now));
         professor.setUserID(UserID);
         userService.UpdateLoginDate(user);
         studentService.UpdateStudentLoginDate(student);
         professorService.UpdateProfessorLoginDate(professor);

      }

      // 공지사항 리스트 띄우기
      List<Board> NoticeList = boardService.findNoticeBoardList();
      model.addAttribute(ConstantUserFunction.NL, NoticeList);

      // 커뮤니티 리스트 띄우기
      List<Board> CommunityList = boardService.findCommunityBoardList();
      model.addAttribute(ConstantUserFunction.CL, CommunityList);

      return ConstantUserFunction.Url;
   }

   @RequestMapping(value = "/", method = RequestMethod.GET)
   public String BlankHome(User user, Principal principal, Model model, HttpServletRequest request, Student student,
         Professor professor) {
      if (principal != null) {
         // 유저 정보
         String LoginID = principal.getName();// 로그인 한 아이디
         ArrayList<String> SelectUserProfileInfo = new ArrayList<String>();
         SelectUserProfileInfo = userService.SelectUserProfileInfo(LoginID);
         int UserID = Integer.parseInt(userService.SelectUserIDForDate(LoginID));
         user.setUserLoginID(LoginID);

         // 휴먼계정 여부 확인 및 update
         boolean DormantCheck = userService.SelectDormant(LoginID);
         if (DormantCheck) {
            userService.UpdateRecoveryDormant(LoginID);
         }

         if (SelectUserProfileInfo.get(2).equals(ConstantUserFunction.SRole)) {
            ArrayList<String> StudentInfo = new ArrayList<String>();
            StudentInfo = studentService.SelectStudentProfileInfo(SelectUserProfileInfo.get(1));

            userInfoMethod.StudentInfo(model, SelectUserProfileInfo, StudentInfo);
         } else if (SelectUserProfileInfo.get(2).equals(ConstantUserFunction.PRole)) {

            ArrayList<String> ProfessorInfo = new ArrayList<String>();
            ProfessorInfo = professorService.SelectProfessorProfileInfo(SelectUserProfileInfo.get(1));

            userInfoMethod.ProfessorInfo(model, SelectUserProfileInfo, ProfessorInfo);
         } else if (SelectUserProfileInfo.get(2).equals(ConstantUserFunction.ARole)) {
            userInfoMethod.AdministratorInfo(model, SelectUserProfileInfo);
         }

         Date Now = new Date();
         SimpleDateFormat Date = new SimpleDateFormat(ConstantUserFunction.DFormat);
         user.setDate(Date.format(Now));
         student.setDate(Date.format(Now));
         student.setUserID(UserID);
         professor.setDate(Date.format(Now));
         professor.setUserID(UserID);
         userService.UpdateLoginDate(user);
         studentService.UpdateStudentLoginDate(student);
         professorService.UpdateProfessorLoginDate(professor);
      }

      // 공지사항 리스트 띄우기
      List<Board> NoticeList = boardService.findNoticeBoardList();
      model.addAttribute("noticeList", NoticeList);

      // 커뮤니티 리스트 띄우기
      List<Board> CommunityList = boardService.findCommunityBoardList();
      model.addAttribute(ConstantUserFunction.CL, CommunityList);

      return ConstantUserFunction.Url;
   }

   // home에서 마이페이지 클릭 시 회원 role에 따라 페이지 리턴
   @RequestMapping(value = "/myPage", method = RequestMethod.GET)
   public String myPageByRole(HttpServletRequest request, Model model) throws IOException {
      String MysqlRole = request.getParameter("R");
      if (MysqlRole.equals(ConstantUserFunction.SRole)) {
         return ConstantUserFunction.MPSUrl;
      } else if (MysqlRole.equals(ConstantUserFunction.PRole)) {
         return ConstantUserFunction.MPPUrl;
      } else if (MysqlRole.equals(ConstantUserFunction.ARole)) {
         return ConstantUserFunction.RUrl;
      }
      return ConstantUserFunction.RUrl;
   }

   // 마이페이지 - 내 게시글 조회
   @RequestMapping(value = "/myPostList", method = RequestMethod.GET)
   public String myPostList(Model model, Principal principal, User user) {
      // 유저 정보
      String LoginID = principal.getName();// 로그인 한 아이디
      GetUserInformation(principal, user, model);
      //
      String UserID = userService.SelectUserIDForMyBoard(LoginID);
      List<Board> MyBoardList = boardService.SelectMyBoardList(UserID);

      model.addAttribute(ConstantUserFunction.MBList, MyBoardList);

      return ConstantUserFunction.MBUrl;
   }

   // 마이페이지 - 내 문의 조회
   @RequestMapping(value = "/myInquiryList", method = RequestMethod.GET)
   public String myInquiryList(Model model, Principal principal, User user) {
      // 유저 정보
      String LoginID = principal.getName();// 로그인 한 아이디
      GetUserInformation(principal, user, model);
      //
      String UserID = userService.SelectUserIDForMyBoard(LoginID);
      List<Inquiry> MyInquiryList = inquiryService.SelectMyInquiryList(UserID);
      if (!MyInquiryList.isEmpty()) {
         model.addAttribute(ConstantUserFunction.MIList, MyInquiryList);
      }
      System.out.println(ConstantUserFunction.MIUrl);
      return ConstantUserFunction.MIUrl;
   }

   /* 마이페이지 - 내 팀 조회 */
   @RequestMapping(value = "/checkMyTeam", method = RequestMethod.GET)
   public String checkMyTeam(Model model, Principal principal, User user) {
      // 유저 정보
      GetUserInformation(principal, user, model);

      return ConstantUserFunction.CMTUrl;
   }

   /* 정보 수정 버튼 클릭 시 비밀번호 확인 */
   @RequestMapping(value = "/checkPassword", method = RequestMethod.GET)
   public String checkPassword() {
      return ConstantUserFunction.CPUrl;
   }

   /* 비밀번호 변경 화면 */
   @RequestMapping(value = "/modifyPassword", method = RequestMethod.GET)
   public String modifyPassword() {
      return ConstantUserFunction.MPUrl;
   }

   // 탈퇴 매뉴얼
   @RequestMapping(value = "/withdrawal", method = RequestMethod.GET)
   public String withdrawal() {
      return ConstantUserFunction.Url;
   }

   @RequestMapping(value = "/withdrawal", method = RequestMethod.POST)
   public String DoWithdrawl(HttpServletRequest request, Principal Principal, User user, Student student,
         Professor professor) {
      String UserLoginID = Principal.getName();
      user.setUserLoginID(UserLoginID);

      if ((String) request.getParameter(ConstantUserFunction.Parameter1) != null) {
         // user정보 select해서 user에 set
         User UserInfo = userService.SelectUserInfo(UserLoginID);
         user.setUserLoginID(UserInfo.getUserLoginID());
         // 탈퇴한 날짜 set
         Date Now = new Date();
         SimpleDateFormat Date = new SimpleDateFormat(ConstantUserFunction.Parameter2);
         user.setDate(Date.format(Now));

         userService.UpdateWithdrawal(user);
      }
      return ConstantUserFunction.Url;
   }

   // 이거는 탈퇴 전에 비밀번호를 확인하기 위함. 수정하기 눌렀을때의 화면, 로직 다 똑같음.
   @RequestMapping(value = "/checkPassword2", method = RequestMethod.GET)
   public String checkPassword2() {
      return ConstantUserFunction.CPWUrl;
   }

   @RequestMapping(value = "/checkPassword2.do", method = RequestMethod.POST)
   public String checkPassword2(HttpServletResponse response, HttpServletRequest request, Principal Principal)
         throws IOException {
      String UserID = Principal.getName();

      boolean Checker = userService.SelectForPwdCheckBeforeModify(UserID,
            (String) request.getParameter(ConstantUserFunction.ULPWD));
      if (Checker == true) {
         return ConstantUserFunction.RWUrl;
      } else {
         response.setContentType("text/html; charset=UTF-8");
         PrintWriter Out = response.getWriter();
         Out.println("<script>alert('비밀번호를 다시 입력해주세요.' );</script>");
         Out.flush();
         return ConstantUserFunction.CPWUrl;
      }
   }

   @RequestMapping(value = "/emailAuthentication", method = RequestMethod.GET)
   public String emailAuthentication() {
      return ConstantUserFunction.EAUrl;
   }

   // 이메일 중복확인, 이메일 발송
   @RequestMapping(value = "/email.do", method = RequestMethod.POST)
   public String DoEmail(User user, UserEmail userEmail, RedirectAttributes redirectAttributes, Model model,
         HttpServletRequest request, HttpServletResponse response) throws IOException {
      UserEmail = (String) request.getParameter(ConstantUserFunction.EM);
      if ((String) request.getParameter(ConstantUserFunction.EM) != null) {
         model.addAttribute(ConstantUserFunction.EM, UserEmail);
         Address = ConstantUserFunction.EmailAddress;
         UserEmail = UserEmail + Address;
         user.setUserEmail(UserEmail);
      }
      if ((String) request.getParameter(ConstantUserFunction.AuthNum) != null) {
         model.addAttribute(ConstantUserFunction.AuthNum,
               (String) request.getParameter(ConstantUserFunction.AuthNum));
      }
      if (request.getParameter(ConstantUserFunction.EC) != null && !UserEmail.equals("")) {
         user.setUserEmail(UserEmail);
         // 이메일 중복확인
         EmailCheck = emailService.SelectForEmailDuplicateCheck(user);

         if (!EmailCheck) {
            int Num = emailService.sendEmail(user);
            // 현재 시간 계산
            Calendear = Calendar.getInstance();
            DateFormat = new SimpleDateFormat(ConstantUserFunction.DateFormat);
            Now = new Date();
            Calendear.setTime(Now);
            ///////////////////////////////////////////////////////////
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter Out = response.getWriter();
            Out.println("<script>alert('성공적으로 이메일 발송이 완료되었습니다.' );</script>");
            Out.flush();
            // 유저 이메일과 인증번호, 현재날짜시각을 디비에 저장하기 위한 데이터 셋
            userEmail.setUserEmail(UserEmail);
            userEmail.setUserCertificationNum(Num);
            // 인증 데이터 저장
            userEmail.setUserCertificationTime(DateFormat.format(Calendear.getTime()));
            this.userEmailService.InsertCertification(userEmail);
            ////////////////////////////////////////////////////
         } else {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter Out = response.getWriter();
            Out.println("<script>alert('이미 등록된 이메일 입니다.' );</script>");
            Out.flush();
         }
         return ConstantUserFunction.AuthUrl;
      } else if (UserEmail.equals("")) {
         // 이메일을 입력해주세요
      } else if (request.getParameter(ConstantUserFunction.EV) != null
            && request.getParameter(ConstantUserFunction.AuthNum) != "") {
         boolean Checker = userEmailService
               .SelectForCheckCertification((String) request.getParameter(ConstantUserFunction.AuthNum));
         if (Checker) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter Out = response.getWriter();
            Out.println("<script>alert('인증에 성공하셨습니다.' );</script>");
            Out.flush();
            EmailChecker = true;
         } else {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter Out = response.getWriter();
            Out.println("<script>alert('일치하지 않는 인증번호입니다. 다시한번 확인해주세요' );</script>");
            Out.flush();
            EmailChecker = false;
            return ConstantUserFunction.AuthUrl;
         }
      }

      if (request.getParameter(ConstantUserFunction.BA) != null && EmailChecker) {
         return ConstantUserFunction.AgreeUrl;
      } else {
         return ConstantUserFunction.AuthUrl;
      }
   }

   // 학생 회원가입
   @RequestMapping(value = "/signupStudent.do", method = RequestMethod.POST)
   public String DoSignUp(User user, Student student, RedirectAttributes redirectAttributes, Model model,
         HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      UserLoginID = (String) request.getParameter("UserLoginID");
      StudentGender = (String) request.getParameter("StudentGender");
      StudentGradeForSignUp = (String) request.getParameter("StudentGrade");
      StudentColleges = (String) request.getParameter("StudentColleges");
      StudentMajor = (String) request.getParameter("StudentMajor");
      StudentDoubleMajor = (String) request.getParameter("StudentDoubleMajor");

      if ((String) request.getParameter("UserLoginID") != null) {
         model.addAttribute("UserLoginID", UserLoginID);
      }
      if ((String) request.getParameter(ConstantUserFunction.Pwd) != null) {
         model.addAttribute(ConstantUserFunction.Pwd, (String) request.getParameter(ConstantUserFunction.Pwd));
      }
      if ((String) request.getParameter(ConstantUserFunction.SName) != null) {
         model.addAttribute(ConstantUserFunction.SName, (String) request.getParameter(ConstantUserFunction.SName));
      }
      if ((String) request.getParameter("StudentGender") != null) {
         model.addAttribute("StudentGender", StudentGender);
      }
      if ((String) request.getParameter("UserPhoneNum") != null) {
         model.addAttribute(ConstantUserFunction.PhoneNum,
               (String) request.getParameter(ConstantUserFunction.PhoneNum));
      }
      if ((String) request.getParameter(ConstantUserFunction.SNum) != null) {
         model.addAttribute(ConstantUserFunction.SNum, request.getParameter(ConstantUserFunction.SNum));
      }
      if ((String) request.getParameter("StudentGrade") != null) {
         model.addAttribute("StudentGrade", StudentGradeForSignUp);
      }
      if ((String) request.getParameter("UserColleges") != null) {
         model.addAttribute("UserColleges", StudentColleges);
      }
      if ((String) request.getParameter("UserMajor") != null) {
         model.addAttribute("UserMajor", StudentMajor);
      }
      if ((String) request.getParameter("StudentDoubleMajor") != null) {
         model.addAttribute("StudentDoubleMajor", StudentDoubleMajor);
      }

      if (request.getParameter("IdCheck") != null) {
         // name을 통해서 jsp에서 값을 받아온다.
         String UserLoginID = (String) request.getParameter("UserLoginID");

         if (UserLoginID.equals("")) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter Out = response.getWriter();
            Out.println("<script>alert('계정을 입력하지 않으셨습니다. 입력해주세요' );</script>");
            Out.flush();
            return ConstantUserFunction.SSUrl;
         } else if (UserLoginID.length() != 8) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter Out = response.getWriter();
            Out.println("<script>alert('학번(8자리)을 입력해주세요. ' );</script>");
            Out.flush();
            return ConstantUserFunction.SSUrl;
         } else {
            user.setUserLoginID(UserLoginID);
            boolean Checker = this.userService.SelctForIDConfirm(user);
            if (Checker) {
               UserLoginID = "";
               model.addAttribute("check", UserLoginID);
               Checker = false;
               response.setContentType("text/html; charset=UTF-8");
               PrintWriter Out = response.getWriter();
               Out.println("<script>alert('이미 등록된 계정 입니다.' );</script>");
               Out.flush();
               IDChecker = false;
               return ConstantUserFunction.SSUrl;
            } else {
               response.setContentType("text/html; charset=UTF-8");
               PrintWriter Out = response.getWriter();
               Checker = true;
               Out.println("<script>alert('등록 가능한 계정 입니다.');</script>");
               Out.flush();
               IDChecker = true;
               return ConstantUserFunction.SSUrl;
            }
         }
      } else if (request.getParameter("submitName") != null && IDChecker) {
         if (StudentColleges.equals("")) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter Out = response.getWriter();
            Out.println("<script>alert('단과대학을 입력해주세요.');</script>");
            Out.flush();
            return ConstantUserFunction.SSUrl;
         } else if (StudentMajor.equals("-선택-")) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter Out = response.getWriter();
            Out.println("<script>alert('전공을 입력해주세요.');</script>");
            Out.flush();
            return ConstantUserFunction.SSUrl;

         } else {
            String HashedPw = BCrypt.hashpw(user.getUserLoginPwd(), BCrypt.gensalt());
            user.setUserLoginPwd(HashedPw);
            user.setUserRole(ConstantUserFunction.SRole); // user role = 학생
            user.setUserEmail(UserEmail);
            this.userService.InsertForSignUp(user); // insert into user table
            user.setUserID(this.userService.SelectUserID(student)); // db의 userID(foreign key)를 user클래스 userID에 set
            student.setStudentColleges(StudentColleges);
            student.setStudentMajor(StudentMajor);
            student.setUserID(user.getUserID());
            if (!((String) request.getParameter("member")).equals("Y")) {
               student.setStudentDoubleMajor("없음");
            } else {
               student.setStudentDoubleMajor(student.getStudentDoubleMajor());
            }
            this.studentService.InsertInformation(student); // insert into student table

            redirectAttributes.addFlashAttribute("msg", "signupERED");
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter Out = response.getWriter();
            Out.println("<script>alert('회원가입이 완료 되었습니다.');</script>");
            Out.flush();
            return ConstantUserFunction.SLUrl;
         }
      } else {
         return ConstantUserFunction.SSUrl;
      }
   }

   // 교수 회원가입
   @RequestMapping(value = "/signupProfessor.do", method = RequestMethod.POST)
   public String dosignup(User user, Professor professor, RedirectAttributes redirectAttributes, Model model,
         HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      UserLoginID = (String) request.getParameter("UserLoginID");

      ProfessorColleges = (String) request.getParameter("ProfessorColleges");
      ProfessorMajor = (String) request.getParameter("ProfessorMajor");
      ProfessorRoom = (String) request.getParameter("ProfessorRoom");
      ProfessorRoomNum = (String) request.getParameter("ProfessorRoomNum");

      if ((String) request.getParameter("UserLoginID") != null) {
         model.addAttribute("UserLoginID", UserLoginID);
      }
      if ((String) request.getParameter(ConstantUserFunction.Pwd) != null) {
         model.addAttribute(ConstantUserFunction.Pwd, (String) request.getParameter(ConstantUserFunction.Pwd));
      }
      if ((String) request.getParameter(ConstantUserFunction.PName) != null) {
         model.addAttribute(ConstantUserFunction.PName, (String) request.getParameter(ConstantUserFunction.PName));
      }
      if ((String) request.getParameter("UserPhoneNum") != null) {
         model.addAttribute(ConstantUserFunction.PhoneNum,
               (String) request.getParameter(ConstantUserFunction.PhoneNum));
      }
      if ((String) request.getParameter(ConstantUserFunction.PNum) != null) {
         model.addAttribute(ConstantUserFunction.PNum, request.getParameter(ConstantUserFunction.PNum));
      }
      if ((String) request.getParameter("UserColleges") != null) {
         model.addAttribute("UserColleges", ProfessorColleges);
      }
      if ((String) request.getParameter("UserMajor") != null) {
         model.addAttribute("UserMajor", ProfessorMajor);
      }
      if ((String) request.getParameter("ProfessorRoom") != null) {
         model.addAttribute("ProfessorRoom", ProfessorRoom);
      }
      if ((String) request.getParameter("ProfessorRoomNum") != null) {
         model.addAttribute("ProfessorRoomNum", ProfessorRoomNum);
      }

      if (request.getParameter("IdCheck") != null) {
         // name을 통해서 jsp에서 값을 받아온다.
         String UserLoginID = (String) request.getParameter("UserLoginID");

         if (UserLoginID.equals("")) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter Out = response.getWriter();
            Out.println("<script>alert('계정을 입력하지 않으셨습니다. 입력해주세요' );</script>");
            Out.flush();
            return "/signup/signupProfessor";
         } else if (UserLoginID.length() != 8) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter Out = response.getWriter();
            Out.println("<script>alert('교번(8자리)을 입력해주세요. ' );</script>");
            Out.flush();
            return "/signup/signupProfessor";
         } else {
            user.setUserLoginID(UserLoginID);
            boolean Checker = this.userService.SelctForIDConfirm(user);
            if (Checker) {
               UserLoginID = "";
               model.addAttribute("check", UserLoginID);
               Checker = false;
               response.setContentType("text/html; charset=UTF-8");
               PrintWriter Out = response.getWriter();
               Out.println("<script>alert('이미 등록된 계정 입니다.' );</script>");
               Out.flush();
               IDChecker = false;
               return "/signup/signupProfessor";
            } else {
               response.setContentType("text/html; charset=UTF-8");
               PrintWriter Out = response.getWriter();
               Checker = true;
               Out.println("<script>alert('등록 가능한 계정 입니다.');</script>");
               Out.flush();
               IDChecker = true;
               return "/signup/signupProfessor";
            }
         }
      } else if (request.getParameter("submitName") != null && IDChecker) {

         if (ProfessorColleges.equals("")) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter Out = response.getWriter();
            Out.println("<script>alert('단과대학을 입력해주세요.');</script>");
            Out.flush();
            return "/signup/signupProfessor";
         } else if (ProfessorMajor.equals("-선택-")) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter Out = response.getWriter();
            Out.println("<script>alert('전공을 입력해주세요.');</script>");
            Out.flush();
            return "/signup/signupProfessor";
         } else {
            String HashedPw = BCrypt.hashpw(user.getUserLoginPwd(), BCrypt.gensalt());
            user.setUserLoginPwd(HashedPw);

            user.setUserRole(ConstantUserFunction.PRole); // user role = 교수
            user.setUserEmail(UserEmail);

            this.userService.InsertForSignUp(user); // insert into user table
            user.setUserID(this.userService.SelectUserID(professor)); // db의 userID(foreign key)를 user클래스 userID에
                                                         // set
            professor.setProfessorColleges(ProfessorColleges);
            professor.setProfessorMajor(ProfessorMajor);
            professor.setProfessorRoom(ProfessorRoom);
            professor.setProfessorRoomNum(ProfessorRoomNum);
            professor.setUserID(user.getUserID());

            this.professorService.InsertInformation(professor); // insert into student table

            redirectAttributes.addFlashAttribute("msg", "signupERED");
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter Out = response.getWriter();
            Out.println("<script>alert('회원가입이 완료 되었습니다.');</script>");
            Out.flush();
            return "/signin/login";
         }
      } else {
         return "/signup/signupProfessor";
      }
   }

   // 비밀번호 찾기 findPassword.do여기서부터하기
   @RequestMapping(value = "/findPassword.do", method = RequestMethod.POST)
   public String findPassword(User user, RedirectAttributes redirectAttributes, Model model,
         HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // xml값가져오기

      UserLoginID = (String) request.getParameter("UserLoginID");
      UserEmail = (String) request.getParameter("UserEmail");
      if (request.getParameter("IdCheck") != null) {
         user.setUserLoginID(UserLoginID);
         user.setUserName((String) request.getParameter(ConstantUserFunction.UName));
         if (UserLoginID.equals("")) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter Out = response.getWriter();
            Out.println("<script>alert('계정을 입력하지 않으셨습니다.');</script>");
            Out.flush();
         } else if (((String) request.getParameter(ConstantUserFunction.UName)).equals("")) {
            model.addAttribute("UserLoginID", UserLoginID);
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter Out = response.getWriter();
            Out.println("<script>alert('이름을 입력하지 않으셨습니다.');</script>");
            Out.flush();
         }
         boolean IDChecker = this.userService.SelectPwdForConfirmToFindPwd(user);
         if (IDChecker) {
            model.addAttribute("UserLoginID", UserLoginID);
            model.addAttribute(ConstantUserFunction.UName,
                  (String) request.getParameter(ConstantUserFunction.UName));
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter Out = response.getWriter();
            Out.println("<script>alert('계정이 확인되었습니다.');</script>");
            Out.flush();
            this.IDChecker = true;
            return ConstantUserFunction.FPUrl;
         } else {
            model.addAttribute("UserLoginID", UserLoginID);
            model.addAttribute(ConstantUserFunction.UName,
                  (String) request.getParameter(ConstantUserFunction.UName));
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter Out = response.getWriter();
            Out.println("<script>alert('등록된 사용자가 아닙니다.');</script>");
            Out.flush();
            this.IDChecker = false;
            return ConstantUserFunction.FPUrl;
         }
      } else if (request.getParameter("EmailCheck") != null) {
         if (UserEmail.equals("")) {
            model.addAttribute("UserLoginID", UserLoginID);
            model.addAttribute(ConstantUserFunction.UName,
                  (String) request.getParameter(ConstantUserFunction.UName));
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter Out = response.getWriter();
            Out.println("<script>alert('이메일을 입력하지 않으셨습니다.');</script>");
            Out.flush();
         } else {
            model.addAttribute("UserLoginID", UserLoginID);
            model.addAttribute(ConstantUserFunction.UName,
                  (String) request.getParameter(ConstantUserFunction.UName));
            model.addAttribute("UserEmail", UserEmail);
            UserEmail = UserEmail + "@mju.ac.kr";
            user.setUserEmail(UserEmail);
            // 이메일 중복검사
            EmailCheck = emailService.SelectForEmailDuplicateCheck(user);
            if (EmailCheck) {
               emailService.sendEmail(user);
               response.setContentType("text/html; charset=UTF-8");
               PrintWriter Out = response.getWriter();
               Out.println("<script>alert('성공적으로 이메일 발송이 완료되었습니다.');</script>");
               Out.flush();
            } else {
               response.setContentType("text/html; charset=UTF-8");
               PrintWriter Out = response.getWriter();
               Out.println("<script>alert('등록되지 않은 이메일입니다.');</script>");
               Out.flush();
            }
            return ConstantUserFunction.FPUrl;
         }

      } else if (request.getParameter("EmailValid") != null) {
         model.addAttribute("UserLoginID", UserLoginID);
         model.addAttribute(ConstantUserFunction.UName,
               (String) request.getParameter(ConstantUserFunction.UName));
         model.addAttribute("UserEmail", UserEmail);
         NameChecker = emailService.AuthNum((String) request.getParameter(ConstantUserFunction.AuthNum));
         if (NameChecker) {
            model.addAttribute(ConstantUserFunction.AuthNum,
                  (String) request.getParameter(ConstantUserFunction.AuthNum));
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('인증번호가 일치합니다.');</script>");
            out.flush();
         } else {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('인증번호가 일치하지 않습니다.');</script>");
            out.flush();

         }
         return ConstantUserFunction.FPUrl;
      } else if (request.getParameter("SubmitName") != null && NameChecker && IDChecker) {
         user.setUserLoginID(UserLoginID);
         user.setUserName((String) request.getParameter(ConstantUserFunction.UName));
         String NewPwd = userService.SelectForShowPassword(user);
         String HashedPw = BCrypt.hashpw(NewPwd, BCrypt.gensalt());// 바꿀 비밀번호 암호화
         user.setUserLoginPwd(HashedPw);
         model.addAttribute("UserLoginPwd", NewPwd);
         userService.UpdateTemporaryPwd(user);

         return ConstantUserFunction.SSPUrl;
      }
      return ConstantUserFunction.FPUrl;
   }

   /* 수정하기 전 비밀번호 확인 */
   @RequestMapping(value = "/checkPassword.do", method = RequestMethod.POST)
   public String checkPassword(HttpServletResponse response, HttpServletRequest request, Principal Principal)
         throws IOException {

      String UserLoginID = Principal.getName();
      boolean Checker = userService.SelectForPwdCheckBeforeModify(UserLoginID,
            (String) request.getParameter(ConstantUserFunction.Pwd));
      String MysqlRole = userService.SelectUserRole(UserLoginID);
      if (Checker == true) {
         if (MysqlRole.equals(ConstantUserFunction.SRole)) {
            return ConstantUserFunction.RMS;
         } else if (MysqlRole.equals(ConstantUserFunction.PRole)) {
            return ConstantUserFunction.RMP;
         }
      } else {
         response.setContentType("text/html; charset=UTF-8");
         PrintWriter Out = response.getWriter();
         Out.println("<script>alert('비밀번호를 다시 입력해주세요.' );</script>");
         Out.flush();
         return ConstantUserFunction.CPUrl;
      }
      return "/";
   }

   // 비밀번호 변경할 때 현재 비번 체크
   @RequestMapping(value = "/checkPassword3", method = RequestMethod.GET)
   public String checkPassword3() {
      return ConstantUserFunction.CPUrl3;
   }

   @RequestMapping(value = "/checkPassword3.do", method = RequestMethod.POST)
   public String checkPassword3(HttpServletResponse response, HttpServletRequest request, Principal Principal)
         throws IOException {

      String UserID = Principal.getName();
      boolean Checker = userService.SelectForPwdCheckBeforeModify(UserID,
            (String) request.getParameter(ConstantUserFunction.ULPWD));
      if (Checker == true) {
         return ConstantUserFunction.RMPWD;
      } else {
         response.setContentType("text/html; charset=UTF-8");
         PrintWriter Out = response.getWriter();
         Out.println("<script>alert('비밀번호를 다시 입력해주세요.' );</script>");
         Out.flush();
         return ConstantUserFunction.CPUrl3;
      }
   }

   // 비밀번호 수정
   @RequestMapping(value = "/modifyPassword.do", method = RequestMethod.POST)
   public String modifyPassword(HttpServletResponse response, HttpServletRequest request, User user,
         Principal Principal) throws IOException {

      String UserLoginID = Principal.getName();
      String HashedPwd = BCrypt.hashpw((String) request.getParameter(ConstantUserFunction.UNPWD),
            BCrypt.gensalt());
      user.setUserModifiedPW(HashedPwd);
      // 새로입력한 비밀번호와 확인 비밀번호가 일치하면
      if ((request.getParameter(ConstantUserFunction.UNPWD)
            .equals((String) request.getParameter(ConstantUserFunction.UNPWDC)))) {
         String UserLoginPwd = userService.SelectCurrentPwd(UserLoginID);
         user.setUserLoginPwd(UserLoginPwd);
         userService.UpdatePwd(user);

         return ConstantUserFunction.MPUrl;
      }
      return ConstantUserFunction.MPUrl;
   }

   // 이메일 로그인 화면
   @RequestMapping(value = "/email/emailLogin", method = RequestMethod.GET)
   public String emailLogin() {
      return ConstantUserFunction.EMURL;
   }

   @RequestMapping(value = "/email/emailList", method = RequestMethod.POST)
   public String DoEmailLogin(HttpServletRequest request, Model model, Principal principal, User user,
         RedirectAttributes rttr) {
      // 유저 정보
      GetUserInformation(principal, user, model);
      // 여기서 아이디 비밀번호 확인후에 띄울지 말지
      // 여기 바꾸기

      String ID = request.getParameter("EmailLoginID") + ConstantUserFunction.EmailAddress;

      boolean CheckID = emailService.CheckEmailLogin(ID, request.getParameter(ConstantUserFunction.EPwd));
      if (CheckID) {
         return ConstantUserFunction.REURL;
      } else {
         rttr.addFlashAttribute("Checker", "LoginFail");
         return ConstantUserFunction.RELURL;
      }
   }

   // 이메일 리스트 화면
   @RequestMapping(value = "/email/emailList", method = RequestMethod.GET)
   public String emailList(HttpServletRequest request, Model model, Principal principal, User user) {

      GetUserInformation(principal, user, model);
      List<UserEmail> EmailList = emailService.PrintEmailList();// 번호 + 보낸이 + 제목
      if (EmailList.isEmpty()) {
         return ConstantUserFunction.EURL;
      } else {
         model.addAttribute("EmailList", EmailList);
         return ConstantUserFunction.EURL;
      }
   }

   // 이메일 리스트에서 제목 클릭 시 해당 이메일 내용 출력
   // 이메일 내용 화면
   @RequestMapping(value = "/email/emailContent", method = RequestMethod.GET)
   public String emailContent(HttpServletRequest request, Model model, Principal principal, User user) {
      GetUserInformation(principal, user, model);

      // 가지고오게 할꺼임
      String Num = request.getParameter("no");
      int IntNum = Integer.parseInt(Num) - 1;
      List<UserEmail> EmailList = emailService.GetEmailList();
      if (EmailList.isEmpty()) {

      } else {
         model.addAttribute("EmailSender", EmailList.get(IntNum).getFrom());
         model.addAttribute("EmailTitle", EmailList.get(IntNum).getTitle());
         model.addAttribute("EmailDate", EmailList.get(IntNum).getDate());
         model.addAttribute("EmailContent", EmailList.get(IntNum).getContent());
      }
      return ConstantUserFunction.ECURL;
   }

   private void GetUserInformation(Principal principal, User user, Model model) {
      String LoginID = principal.getName();// 로그인 한 아이디
      ArrayList<String> SelectUserProfileInfo = new ArrayList<String>();
      SelectUserProfileInfo = userService.SelectUserProfileInfo(LoginID);
      user.setUserLoginID(LoginID);

      if (SelectUserProfileInfo.get(2).equals(ConstantUserFunction.SRole)) {
         ArrayList<String> StudentInfo = new ArrayList<String>();
         StudentInfo = studentService.SelectStudentProfileInfo(SelectUserProfileInfo.get(1));
         userInfoMethod.StudentInfo(model, SelectUserProfileInfo, StudentInfo);
      } else if (SelectUserProfileInfo.get(2).equals(ConstantUserFunction.PRole)) {
         ArrayList<String> ProfessorInfo = new ArrayList<String>();
         ProfessorInfo = professorService.SelectProfessorProfileInfo(SelectUserProfileInfo.get(1));
         userInfoMethod.ProfessorInfo(model, SelectUserProfileInfo, ProfessorInfo);
      } else if (SelectUserProfileInfo.get(2).equals(ConstantUserFunction.ARole)) {
         userInfoMethod.AdministratorInfo(model, SelectUserProfileInfo);
      }
   }

}