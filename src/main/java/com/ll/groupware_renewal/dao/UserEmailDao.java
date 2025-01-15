package com.ll.groupware_renewal.dao;

import com.ll.groupware_renewal.dto.UserEmail;

public interface UserEmailDao {

	public void InsertCertification(UserEmail userEmail);

	// 인증번호 비교(디비)
	public boolean SelectForCheckCertification(int authNum);

	// 일정 시간 후 인증번호 삭제
	public void DeleteCertification(UserEmail userEmail);

}
