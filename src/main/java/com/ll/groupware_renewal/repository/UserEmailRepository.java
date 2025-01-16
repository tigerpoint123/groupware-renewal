package com.ll.groupware_renewal.repository;

import com.ll.groupware_renewal.entity.UserEmail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEmailRepository extends JpaRepository<UserEmail, Integer> {

	 void InsertCertification(UserEmail userEmail);

	// 인증번호 비교(디비)
	 boolean SelectForCheckCertification(int authNum);

	// 일정 시간 후 인증번호 삭제
	 void DeleteCertification(UserEmail userEmail);

}
