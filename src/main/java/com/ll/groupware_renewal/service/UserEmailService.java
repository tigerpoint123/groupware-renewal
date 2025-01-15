package com.ll.groupware_renewal.service;

import com.ll.groupware_renewal.entity.UserEmail;
import com.ll.groupware_renewal.repository.UserEmailDao;
import org.springframework.beans.factory.annotation.Autowired;

public class UserEmailService {

	@Autowired
	UserEmailDao userEmailDao;

	@Override
	public void InsertCertification(UserEmail userEmail) {
		userEmailDao.InsertCertification(userEmail);
	}

	@Override
	public boolean SelectForCheckCertification(String authNum) {
		boolean Checker = userEmailDao.SelectForCheckCertification(Integer.parseInt(authNum));
		return Checker;
	}

	@Override
	public void DeleteInfo(UserEmail userEmail) {
		userEmailDao.DeleteCertification(userEmail);
	}

}
