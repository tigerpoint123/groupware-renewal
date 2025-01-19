package com.ll.groupware_renewal.controller;

import com.ll.groupware_renewal.config.SearchConfig;
import com.ll.groupware_renewal.constant.ConstantSearchController;
import com.ll.groupware_renewal.dto.*;
import com.ll.groupware_renewal.function.UserInfoMethod;
import com.ll.groupware_renewal.service.ProfessorService;
import com.ll.groupware_renewal.service.SearchService;
import com.ll.groupware_renewal.service.StudentService;
import com.ll.groupware_renewal.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SearchController {
	private final UserService userService;
	private final StudentService studentService;
	private final ProfessorService professorService;
	private final UserInfoMethod userInfoMethod;
	private final SearchService searchService;
	private final SearchConfig searchConfig;

	// review 사용자 검색
	@RequestMapping(value = "/search/searchUser", method = RequestMethod.GET)
	public String searchUser(Principal principal, Model model, User user) {
		// 유저 정보
		GetUserInformation(principal, user, model);
		return this.searchConfig.getUrls().getRSearchUser();
	}

	@ResponseBody
	@RequestMapping(value = "/search/searchUser.do", method = { RequestMethod.GET, RequestMethod.POST })
	public List<HashMap<String, Object>> DoSearchUser(Principal principal, Model model, HttpServletRequest request,
			@RequestBody SearchKeyWord searchKeyWord) {

		List<User> InfoList = searchService.SelectKeyWord(searchKeyWord);
		List<HashMap<String, Object>> MapInfo = new ArrayList<HashMap<String, Object>>();
		if (!InfoList.isEmpty()) {
			for (int i = 0; i < InfoList.size(); i++) {
				HashMap<String, Object> Map = new HashMap<String, Object>();
				if (InfoList.get(i).getUserRole().equals(searchConfig.getFields().getSRole())) {
					Map = addStudentInfo(InfoList.get(i));
					MapInfo.add(Map);
				} else if (InfoList.get(i).getUserRole().equals(searchConfig.getFields().getPRole())) {
					Map = addProfessorInfo(InfoList.get(i));
					MapInfo.add(Map);
				}
			}

			return MapInfo;
		} else {
			return MapInfo;
		}
	}

	private HashMap<String, Object> addProfessorInfo(User user) {
		HashMap<String, Object> Map = new HashMap<String, Object>();
		Map.put(this.searchConfig.getFields().getUName(), user.getUserName());
		Professor professor = searchService.SelectProfessorInfo(user.getUserID());

		Map.put(this.searchConfig.getFields().getUserEmail(), user.getUserEmail());

		Map.put("Gender", "비공개");
		if (user.getOpenPhoneNum().equals("비공개")) {
			Map.put(this.searchConfig.getFields().getPhoneNum(), user.getOpenPhoneNum());
		} else {
			Map.put(this.searchConfig.getFields().getPhoneNum(), user.getUserPhoneNum());
		}
		Map.put("UserMajor", professor.getProfessorMajor());
		Map.put("Role", "교수님");
		return Map;
	}

	private HashMap<String, Object> addStudentInfo(User user) {
		HashMap<String, Object> Map = new HashMap<String, Object>();
		Map.put(this.searchConfig.getFields().getUName(), user.getUserName());
		Student student = searchService.SelectStudentInfo(user.getUserID());
		Map.put("UserMajor", student.getStudentMajor());

		Map.put(this.searchConfig.getFields().getUserEmail(), user.getUserEmail());

		if (user.getOpenPhoneNum().equals("비공개")) {
			Map.put(this.searchConfig.getFields().getPhoneNum(), user.getOpenPhoneNum());
		} else {
			Map.put(this.searchConfig.getFields().getPhoneNum(), user.getUserPhoneNum());
		}
		Map.put("Major", student.getStudentMajor());
		Map.put("Gender", student.getStudentGender());
		Map.put("Role", "학생");
		return Map;
	}

	// review list 검색
	@RequestMapping(value = "/search/reviewList", method = RequestMethod.GET)
	public String reviewList(Principal principal, Model model, User user, HttpServletRequest request, RedirectAttributes rttr) {
		// 유저 정보
		GetUserInformation(principal, user, model);
		String UserEmail = request.getParameter("no");
		String UserID = userService.SelectIDForReview(UserEmail);
		List<UserReview> Review = searchService.SelectUserReview(UserID);
		if (Review.isEmpty()) {
			rttr.addFlashAttribute("Checker", "NoReiveiwList");
			return this.searchConfig.getUrls().getRSearchUser();
		} else {
			model.addAttribute("list", Review);
		}
		return this.searchConfig.getUrls().getReviewList();
	}

	private void GetUserInformation(Principal principal, User user, Model model) {

		String LoginID = principal.getName();// 로그인 한 아이디
		ArrayList<String> SelectUserProfileInfo = new ArrayList<String>();
		SelectUserProfileInfo = userService.SelectUserProfileInfo(LoginID);
		user.setUserLoginID(LoginID);
		if (SelectUserProfileInfo.get(2).equals(this.searchConfig.getFields().getSRole())) {
			ArrayList<String> StudentInfo = new ArrayList<String>();
			StudentInfo = studentService.SelectStudentProfileInfo(SelectUserProfileInfo.get(1));
			userInfoMethod.StudentInfo(model, SelectUserProfileInfo, StudentInfo);
		} else if (SelectUserProfileInfo.get(2).equals(this.searchConfig.getFields().getPRole())) {
			ArrayList<String> ProfessorInfo = new ArrayList<String>();
			ProfessorInfo = professorService.SelectProfessorProfileInfo(SelectUserProfileInfo.get(1));
			userInfoMethod.ProfessorInfo(model, SelectUserProfileInfo, ProfessorInfo);
		} else if (SelectUserProfileInfo.get(2).equals(this.searchConfig.getFields().getARole())) {
			userInfoMethod.AdministratorInfo(model, SelectUserProfileInfo);
		}
	}

}
