package com.ll.groupware_renewal.controller;

import com.ll.groupware_renewal.config.StudentConfig;
import com.ll.groupware_renewal.constant.admin.ConstantAdminStudentController;
import com.ll.groupware_renewal.dto.Student;
import com.ll.groupware_renewal.dto.User;
import com.ll.groupware_renewal.service.StudentService;
import com.ll.groupware_renewal.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class StudentController {
	private final UserService userService;
	private final StudentService studentService;
	private final StudentConfig studentConfig;

	private String studentColleges;
	private String studentGrade;
	private String userMajorForShow;
	private String userName;

	@RequestMapping(value = "/signupStudent", method = RequestMethod.GET)
	public String signupStudent() {
		return this.studentConfig.getUrls().getSignup().toString();
	}

	/* 학생 마이페이지 */
	@RequestMapping(value = "/myPageStudent", method = RequestMethod.GET)
	public String myPageStudent(User user, Model model, HttpServletRequest requestm, Principal Principal) {

		String LoginID = Principal.getName();// 로그인 한 아이디
		ArrayList<String> SelectUserProfileInfo = new ArrayList<String>();
		SelectUserProfileInfo = userService.SelectUserProfileInfo(LoginID);

		user.setUserLoginID(LoginID);
		ArrayList<String> StudentInfo = new ArrayList<String>();
		StudentInfo = studentService.SelectStudentProfileInfo(SelectUserProfileInfo.get(1));

		// 학생 이름
		userName = SelectUserProfileInfo.get(0);
		model.addAttribute("UserName", userName);
		// 학생 소속
		studentColleges = StudentInfo.get(0);
		model.addAttribute("Colleges", studentColleges);

		userMajorForShow = StudentInfo.get(1);
		model.addAttribute("UserMajor", userMajorForShow);

		studentGrade = StudentInfo.get(2);
		model.addAttribute(this.studentConfig.getFields().getGrade().toString(), studentGrade);

		// user role 가져오기
		String UserLoginID = Principal.getName();
		ArrayList<String> Info = new ArrayList<String>();
		Info = userService.SelectUserProfileInfo(UserLoginID);
		model.addAttribute("UserRole", Info.get(2));

		// -------------------------------------------------------

		String UserID = Principal.getName();
		ArrayList<String> SelectUserInfo = new ArrayList<String>();
		SelectUserInfo = userService.SelectMyPageUserInfo(UserID);

		String SelectOpenInfo = userService.SelectOpenInfo(UserID);
		// jsp화면 설정
		// 아이디 0
		model.addAttribute(this.studentConfig.getFields().getUserLoginId().toString(), SelectUserInfo.get(0));
		// 이름 1
		model.addAttribute(this.studentConfig.getFields().getUserName().toString(), SelectUserInfo.get(1));
		// 성별 8
		model.addAttribute("StudentGender", SelectUserInfo.get(8));
		// 연락처 2
		model.addAttribute(this.studentConfig.getFields().getUserPhoneNum().toString(), SelectUserInfo.get(2));
		// 학년 6
		model.addAttribute("StudentGrade", SelectUserInfo.get(6));
		// 단과대학 4
		model.addAttribute("StudentColleges", SelectUserInfo.get(4));
		// 전공 5
		model.addAttribute("StudentMajor", SelectUserInfo.get(5));
		// 복수전공 7
		model.addAttribute("StudentDoubleMajor", SelectUserInfo.get(7));
		// 이메일 5
		int Idx = SelectUserInfo.get(3).indexOf("@"); // 메일에서 @의 인덱스 번호를 찾음
		String Email = SelectUserInfo.get(3).substring(0, Idx);
		model.addAttribute(this.studentConfig.getFields().getUserEmail().toString(), Email);

		// 정보공개여부
		if (!SelectOpenInfo.equals("Error")) {
			model.addAttribute("UserInfoOpen", SelectOpenInfo);
		}
		return this.studentConfig.getUrls().getMyPage().toString();
	}

	/* 학생 정보 수정 화면 */
	@RequestMapping(value = "/modifyStudent", method = RequestMethod.GET)
	public String modifyStudent(Principal principal, Model model) {
		String LoginID = principal.getName();
		User SelectUserProfileInfo = userService.SelectModifyUserInfo(LoginID);
		Student student = studentService.SelectModifyStudentInfo(SelectUserProfileInfo.getUserID());
		// 학번
		model.addAttribute("UserLoginID", SelectUserProfileInfo.getUserLoginID());
		// 이름
		model.addAttribute("UserName", SelectUserProfileInfo.getUserName());
		// 이메일
		String UserEmail = SelectUserProfileInfo.getUserEmail();
		int Location = UserEmail.indexOf("@");
		UserEmail = UserEmail.substring(0, Location);
		model.addAttribute(this.studentConfig.getFields().getEmail().toString(), UserEmail);
		// 성별
		model.addAttribute("StudentGender", student.getStudentGender());
		// 단과대학
		model.addAttribute("StudentColleges", student.getStudentColleges());
		// 전공
		model.addAttribute("StudentMajor", student.getStudentMajor());
		// 복수전공
		model.addAttribute("StudentDoubleMajor", student.getStudentDoubleMajor());
		// 연락처 공개
        model.addAttribute("OpenPhoneNum", SelectUserProfileInfo.getOpenPhoneNum());
        // 학년 공개
        model.addAttribute("OpenGrade", SelectUserProfileInfo.getOpenGrade());
		return this.studentConfig.getUrls().getModify().toString();
	}

	// 학생 정보 수정
	@RequestMapping(value = "/modifyStudent.do", method = RequestMethod.POST)
	public String DoModifyStudent(HttpServletResponse response, HttpServletRequest request, Model model,
								  Student student, User user, Principal Principal) {
		// 업데이트문 where절을 위한 데이터 get
		String UserID = Principal.getName();
		ArrayList<String> UserInfo = new ArrayList<String>();
		UserInfo = userService.SelectUserInformation(UserID);
		UserInfo.get(0); // 유저아이디 get
		UserInfo.get(1); // 로그인아이디 get

		user.setUserLoginID(UserInfo.get(1));
		student.setUserID(Integer.parseInt(UserInfo.get(0)));

		// 연락처
		if (!((String) request.getParameter(this.studentConfig.getFields().getUserPhoneNum().toString())).equals("")) {
			user.setUserPhoneNum((String) request.getParameter(this.studentConfig.getFields().getUserPhoneNum().toString()));
			userService.updateUserPhoneNumber(user);
		}
		// 학년
		if (!((String) request.getParameter("StudentGrade")).equals(" ")) {
			student.setStudentGrade((String) request.getParameter("StudentGrade"));
			studentService.updateStudentGrade(student);
		}

		// 정보공개여부 선택
		if (request.getParameter(this.studentConfig.getFields().getUserPhone().toString()) != null) {
			String OpenPhoneNum = "전화번호";
			user.setOpenPhoneNum(OpenPhoneNum);
			userService.UpdateOpenPhoneNum(user);
		} else if (request.getParameter(this.studentConfig.getFields().getUserPhone().toString()) == null) {
			String NotOpen = "비공개";
			user.setOpenPhoneNum(NotOpen);
			userService.UpdateOpenPhoneNum(user);
		}

		if (request.getParameter(this.studentConfig.getFields().getUserGrade().toString()) != null) {
			String OpenGrade = "학년";
			user.setOpenGrade(OpenGrade);
			userService.UpdateOpenGrade(user);
		} else if (request.getParameter(this.studentConfig.getFields().getUserGrade().toString()) == null) {
			String NotOpen = "비공개";
			user.setOpenGrade(NotOpen);
			userService.UpdateOpenGrade(user);
		}

		return this.studentConfig.getUrls().getModify().toString();
	}

}