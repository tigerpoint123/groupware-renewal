package com.ll.groupware_renewal.constant.admin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ConstantAdminUserListDao {
	private String UserListDaoID;
	private String SelectUserList;
	private String SelectDormantList;
	private String SelectWithdrawalList;
	private String SelectWithdrawalUserListForRecovery;
	private String SelectWithdrawalStudentListForRecovery;
	private String SelectWithdrawalProfessorListForRecovery;
	private String InsertUserForRecovery;
	private String InsertStudentForRecovery;
	private String InsertProfessorForRecovery;
	
}
