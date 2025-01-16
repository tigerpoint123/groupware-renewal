package com.ll.groupware_renewal.service;

import com.ll.groupware_renewal.entity.UserEmail;
import com.ll.groupware_renewal.repository.UserEmailDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEmailService {
	private final UserEmailDao userEmailDao;

	public void InsertCertification(UserEmail userEmail) {
		userEmailDao.InsertCertification(userEmail);
	}

	public boolean SelectForCheckCertification(String authNum) {
		boolean Checker = userEmailDao.SelectForCheckCertification(Integer.parseInt(authNum));
		return Checker;
	}

	public void DeleteInfo(UserEmail userEmail) {
		userEmailDao.DeleteCertification(userEmail);
	}

}
