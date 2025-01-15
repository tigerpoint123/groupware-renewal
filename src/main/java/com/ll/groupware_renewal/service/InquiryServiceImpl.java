package com.ll.groupware_renewal.service;

import com.ll.groupware_renewal.dao.InquiryDao;
import com.ll.groupware_renewal.dto.Inquiry;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquiryServiceImpl implements InquiryService {
	@Autowired
	InquiryDao inquiryDao;

	@Override
	public List<Inquiry> SelectInquiryList() {
		return inquiryDao.SelectInquiryList();
	}

	@Override
	public void InsertInquiry(Inquiry inquiry, HttpServletRequest request) {
		inquiryDao.InsertIBoardInfo(inquiry);
			int Ino = inquiryDao.SelectIBoardID(inquiry);
			inquiry.setIno(Ino);
	}
	
	@Override
	public Inquiry SelectOneInquiryContent(String iboardID) {
		return inquiryDao.SelectOneInquiryContent(iboardID);
	}
	
	@Override
	public String SelectLoginUserIDForInquiry(String loginID) {
		return inquiryDao.SelectLoginUserIDForInquiry(loginID);
	}

	@Override
	public void UpdateIBoardDelete(int iboardID) {
		inquiryDao.UpdateIBoardDelete(iboardID);
	}
	
	@Override
	public void InsertInquiryAnswer(Inquiry inquiry, HttpServletRequest request) {
		inquiryDao.InsertInquiryAnswer(inquiry);
	}
	
	@Override
	public void DeleteInquiryAnswer(int iboardID) {
		inquiryDao.DeleteInquiryAnswer(iboardID);
	}
	
	@Override
	public List<Inquiry> SelectMyInquiryList(String loginID) {
		return inquiryDao.SelectMyInquiryList(loginID);
	}
}
