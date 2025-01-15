package com.ll.groupware_renewal.service;

import com.ll.groupware_renewal.dto.Inquiry;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface InquiryService {

	void InsertInquiry(Inquiry inquiry, HttpServletRequest request);
	
	List<Inquiry> SelectInquiryList();
	
	Inquiry SelectOneInquiryContent(String iboardID);

	String SelectLoginUserIDForInquiry(String loginID);

	void UpdateIBoardDelete(int iboardID);
	
	void InsertInquiryAnswer(Inquiry inquiry, HttpServletRequest request);
	
	void DeleteInquiryAnswer(int iboardID);
	
	List<Inquiry> SelectMyInquiryList(String loginID);
	
}
