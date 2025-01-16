package com.ll.groupware_renewal.repository;

import com.ll.groupware_renewal.entity.UserList;

import java.util.List;

public interface UserListJpaRepository {

	public List<UserList> SelectUserlist() throws Exception;

	public List<UserList> SelectDormantUserList();

	public List<UserList> SelectWithdrawalUserList();

	List<UserList> findAll();
}
