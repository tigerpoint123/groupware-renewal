package com.ll.groupware_renewal.service;

import com.ll.groupware_renewal.repository.UserListJpaRepository;
import com.ll.groupware_renewal.entity.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private UserListJpaRepository userListDao;

	@Override
	public List<UserList> SelectUserlist() throws Exception {
		return userListDao.SelectUserlist();
	}

	@Override
	public List<UserList> SelectDormantUserList() {
		return userListDao.SelectDormantUserList();
	}

	@Override
	public List<UserList> SelectWithdrawalUserList() {
		return userListDao.SelectWithdrawalUserList();
	}

}
