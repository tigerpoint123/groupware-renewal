package com.ll.groupware_renewal.controller;

import com.ll.groupware_renewal.constant.ConstantProfessorController;
import com.ll.groupware_renewal.entity.Professor;
import com.ll.groupware_renewal.entity.User;
import com.ll.groupware_renewal.service.ProfessorService;
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
public class ProfessorController {
    private final UserService userService;
    private final ProfessorService professorService;

    private String ProfessorColleges;
    private String ProfessorRoom;
    private String UserMajorForShow;
    private String UserName;

    @RequestMapping(value = "/signupProfessor", method = RequestMethod.GET)
    public String signupProfessor() {
        return ConstantProfessorController.RSignupProfessor;
    }

    /* 교수 마이페이지 */
    @RequestMapping(value = "/myPageProfessor", method = RequestMethod.GET)
    public String myPageProfessor(User user, Model model, HttpServletRequest requestm, Principal Principal) {

        String LoginID = Principal.getName();// 로그인 한 아이디
        ArrayList<String> SelectUserProfileInfo = new ArrayList<String>();
        SelectUserProfileInfo = userService.SelectUserProfileInfo(LoginID);

        user.setUserLoginID(LoginID);
        ArrayList<String> ProfessorInfo = new ArrayList<String>();
        ProfessorInfo = professorService.SelectProfessorProfileInfo(SelectUserProfileInfo.get(1));

        // 교수 이름
        UserName = SelectUserProfileInfo.get(0);
        model.addAttribute("UserName", UserName);
        // 교수 소속
        ProfessorColleges = ProfessorInfo.get(0);
        model.addAttribute("Colleges", ProfessorColleges);
        // 교수 전공
        UserMajorForShow = ProfessorInfo.get(1);
        model.addAttribute("UserMajor", UserMajorForShow);
        // 교수실
        ProfessorRoom = ProfessorInfo.get(2);
        model.addAttribute("ProfessorRoom", ProfessorRoom);

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
        model.addAttribute(ConstantProfessorController.UserLoginID, SelectUserInfo.get(0));
        // 이름 1
        model.addAttribute(ConstantProfessorController.UserName, SelectUserInfo.get(1));
        // 교수실 전화번호 7
        model.addAttribute("ProfessorRoomNum", SelectUserInfo.get(12));
        // 연락처 2
        model.addAttribute(ConstantProfessorController.UserPhoneNum, SelectUserInfo.get(2));
        // 단과대학 9
        model.addAttribute("ProfessorColleges", SelectUserInfo.get(9));
        // 전공 10
        model.addAttribute("ProfessorMajor", SelectUserInfo.get(10));
        // 교수실 11
        model.addAttribute("ProfessorRoom", SelectUserInfo.get(11));
        // 이메일 3
        int Idx = SelectUserInfo.get(3).indexOf("@");
        String Email = SelectUserInfo.get(3).substring(0, Idx);
        model.addAttribute(ConstantProfessorController.UserEmail, Email);

        // 정보공개여부
        if (!SelectOpenInfo.equals("Error")) {
            model.addAttribute("UserInfoOpen", SelectOpenInfo);
        }
        return ConstantProfessorController.RMyPageProfessor;

    }

    /* 교수 정보 수정 화면 */
    @RequestMapping(value = "/modifyProfessor", method = RequestMethod.GET)
    public String modifyProfessor(Model model, Principal principal) {
        String LoginID = principal.getName();

        User SelectUserProfileInfo = userService.SelectModifyUserInfo(LoginID);
        Professor professor = professorService.SelectModifyProfessorInfo(SelectUserProfileInfo.getUserID());

        model.addAttribute("UserLoginID", SelectUserProfileInfo.getUserLoginID());
        model.addAttribute("UserName", SelectUserProfileInfo.getUserName());
        String UserEmail = SelectUserProfileInfo.getUserEmail();
        int Location = UserEmail.indexOf("@");
        UserEmail = UserEmail.substring(0, Location);
        model.addAttribute(ConstantProfessorController.Email, UserEmail);
        model.addAttribute(ConstantProfessorController.UserPhoneNum, professor.getUserPhoneNum());
        model.addAttribute("ProfessorColleges", professor.getProfessorColleges());
        model.addAttribute("ProfessorMajor", professor.getProfessorMajor());
        // 연락처 공개
        model.addAttribute("OpenPhoneNum", SelectUserProfileInfo.getOpenPhoneNum());

        return ConstantProfessorController.RModifyProfessor;
    }

    // 교수 정보 수정
    @RequestMapping(value = "/modifyProfessor.do", method = RequestMethod.POST)
    public String DoModifyProfessor(HttpServletResponse response, HttpServletRequest request, Model model,
                                    Professor professor, User user, Principal Principal) {
        // 업데이트문 where절을 위한 데이터 get
        String UserID = Principal.getName();
        ArrayList<String> UserInfo = new ArrayList<String>();
        UserInfo = userService.SelectUserInformation(UserID);
        UserInfo.get(0); // 유저아이디 get
        UserInfo.get(1); // 로그인아이디 get

        user.setUserLoginID(UserInfo.get(1));

        professor.setUserID(Integer.parseInt(UserInfo.get(0)));

        // 연락처
        if (!((String) request.getParameter(ConstantProfessorController.UserPhoneNum)).equals("")) {
            user.setUserPhoneNum((String) request.getParameter(ConstantProfessorController.UserPhoneNum));
            userService.updateUserPhoneNumber(user);
        }
        // 교수실
        if (!((String) request.getParameter("ProfessorRoom")).equals("")) {
            professor.setProfessorRoom((String) request.getParameter("ProfessorRoom"));
            professorService.UpdateProfessorRoom(professor);
        }
        // 교수실 전화번호
        if (!((String) request.getParameter("ProfessorRoomNum")).equals("")) {
            professor.setProfessorRoomNum((String) request.getParameter("ProfessorRoomNum"));
            professorService.UpdateProfessorRoomNum(professor);
        }

        // 정보공개여부 선택
        if (request.getParameter(ConstantProfessorController.UserPhone) != null) {
            String OpenPhoneNum = "전화번호";
            user.setOpenPhoneNum(OpenPhoneNum);
            userService.UpdateOpenPhoneNum(user);
        } else if (request.getParameter(ConstantProfessorController.UserPhone) == null) {
            String NotOpen = "비공개";
            user.setOpenPhoneNum(NotOpen);
            userService.UpdateOpenPhoneNum(user);
        }
        return ConstantProfessorController.RModifyProfessor;
    }

}