package com.ll.groupware_renewal.constant;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ConstantAdmin {
    // Role Constants
    public static final String SRole = "STUDENT";
    public static final String PRole = "PROFESSOR";
    public static final String ARole = "ADMINISTRATOR";
    public static final String TemporaryPwd = "00000000";

    // URL Constants
    public static final String Home = "/admin/homeAdmin";
    public static final String List = "/admin/manageList";
    public static final String ReList = "redirect:manageList";
    public static final String ReSleep = "redirect:manageSleep";
    public static final String SleepList = "/admin/manageSleep";
    public static final String SecessionList = "/admin/manageSecession";
    public static final String ReSecessionList = "redirect:manageSecession";
    public static final String Detail = "/admin/detail";
    
    // Redirect URLs
    public static final String ReSDetail = "redirect:detailStudent";
    public static final String RePDetail = "redirect:detailProfessor";
    
    // Detail & Modify URLs
    public static final String SDetail = "/admin/detailStudent";
    public static final String PDetail = "/admin/detailProfessor";
    public static final String SModify = "/admin/ModifyStudent";
    public static final String PModify = "/admin/ModifyProfessor";
    
    // Management URLs
    public static final String SManage = "/admin/manageStudent";
    public static final String PManage = "/admin/manageProfessor";
    public static final String SManageModify = "/admin/manageModifyStudent";
    public static final String PManageModify = "/admin/manageModifyProfessor";

    // Role Constants
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_SUSER = "ROLE_SUSER";
    public static final String ROLE_PUSER = "ROLE_PUSER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    // User Types
    public static final String STUDENT = "STUDENT";
    public static final String PROFESSOR = "PROFESSOR";
    public static final String ADMINISTRATOR = "ADMINISTRATOR";

    // User Information Fields
    public static final String UserPhoneNum = "UserPhoneNum";
    public static final String UserEmail = "UserEmail";
    public static final String ProfessorRoomNum = "ProfessorRoomNum";
    public static final String Email = "Email";

}