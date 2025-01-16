package com.ll.groupware_renewal.constant;

public class ConstantUserDao {
    // User Operations
    public static final String InsertUser = "InsertUser";
    public static final String SelectByLoginID = "SelectByLoginID";
    public static final String SelctForIDConfirm = "SelctForIDConfirm";
    public static final String SelectPwdForConfirmToFindPwd = "SelectPwdForConfirmToFindPwd";
    public static final String SelectUserID = "SelectUserID";
    public static final String SelectForShowPassword = "SelectForShowPassword";
    public static final String SelectForEmailDuplicateCheck = "SelectForEmailDuplicateCheck";
    
    // Login Related
    public static final String UpdateLoginDate = "UpdateLoginDate";
    public static final String SelectCurrentPwd = "SelectCurrentPwd";
    public static final String UpdatePwd = "UpdatePwd";
    public static final String SelectForPwdCheckBeforeModify = "SelectForPwdCheckBeforeModify";
    
    // User Info
    public static final String SelectUserInfo = "SelectUserInfo";
    public static final String UpdateUserPhoneNum = "UpdateUserPhoneNum";
    public static final String UpdateUserMajor = "UpdateUserMajor";
    public static final String UpdateUserColleges = "UpdateUserColleges";
    public static final String SelectUserInformation = "SelectUserInformation";
    
    // Password
    public static final String UpdateTemporaryPwd = "UpdateTemporaryPwd";
    
    // Withdrawal
    public static final String UpdateWithdrawal = "UpdateWithdrawal";
    public static final String UpdateDoWithdrawalRecoveryByAdmin = "UpdateDoWithdrawalRecoveryByAdmin";
    public static final String UpdateDormantOneToZero = "UpdateDormantOneToZero";
    
    // Roles
    public static final String ROLE_USER = "ROLE_USER";
    public static final String UpdateUserRole = "UpdateUserRole";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String UpdateAdminRole = "UpdateAdminRole";
    
    // MyPage
    public static final String SelectMyPageInfo = "SelectMyPageInfo";
    public static final String SelectMyPageInfoByID = "SelectMyPageInfoByID";
    
    // User Updates
    public static final String UpdateUserName = "UpdateUserName";
    public static final String UpdateOpenName = "UpdateOpenName";
    public static final String UpdateOpenEmail = "UpdateOpenEmail";
    public static final String UpdateOpenPhoneNum = "UpdateOpenPhoneNum";
    public static final String UpdateOpenMajor = "UpdateOpenMajor";
    public static final String UpdateOpenGrade = "UpdateOpenGrade";
    
    // Withdrawal Operations
    public static final String SelectUserInfoForWithdrawal = "SelectUserInfoForWithdrawal";
    public static final String InsertWithdrawalUser = "InsertWithdrawalUser";
    public static final String DeleteWithdrawalUser = "DeleteWithdrawalUser";
    public static final String DeleteWithdrawalUserList = "DeleteWithdrawalUserList";
    
    // Open Info
    public static final String SelectOpenInfo = "SelectOpenInfo";
    public static final String SelectUserIDFromBoardController = "SelectUserIDFromBoardController";
} 