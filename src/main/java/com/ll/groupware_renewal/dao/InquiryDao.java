package com.ll.groupware_renewal.dao;

import com.ll.groupware_renewal.dto.Inquiry;

import java.util.List;

public interface InquiryDao {

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
