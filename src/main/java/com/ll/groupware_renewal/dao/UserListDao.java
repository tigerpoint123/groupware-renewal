package com.ll.groupware_renewal.dao;

import com.ll.groupware_renewal.dto.UserList;

import java.util.List;

public interface UserListDao {

	public List<UserList> SelectUserlist() throws Exception;

	public List<UserList> SelectDormantUserList();

	public List<UserList> SelectWithdrawalUserList();

}
