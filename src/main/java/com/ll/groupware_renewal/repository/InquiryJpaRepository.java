package com.ll.groupware_renewal.repository;

import com.ll.groupware_renewal.entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InquiryJpaRepository extends JpaRepository<Inquiry, Integer> {

	void InsertIBoardInfo(Inquiry inquiry);

	List<Inquiry> SelectInquiryList();

	int SelectIBoardID(Inquiry inquiry);

	Inquiry SelectOneInquiryContent(String iboardID);

	String SelectLoginUserIDForInquiry(String loginID);

	void UpdateIBoardDelete(int iboardID);
	
	void InsertInquiryAnswer(Inquiry inquiry);
	
	void DeleteInquiryAnswer(int iboardID);
	
	List<Inquiry> SelectMyInquiryList(String LoginID);

}
