package com.ll.groupware_renewal.service;

import com.ll.groupware_renewal.repository.InquiryJpaRepository;
import com.ll.groupware_renewal.entity.Inquiry;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquiryServiceImpl implements InquiryService {
	@Autowired
	InquiryJpaRepository inquiryJpaRepository;

	@Override
	public List<Inquiry> SelectInquiryList() {
		return inquiryJpaRepository.SelectInquiryList();
	}

	@Override
	public void InsertInquiry(Inquiry inquiry, HttpServletRequest request) {
		inquiryJpaRepository.InsertIBoardInfo(inquiry);
			int Ino = inquiryJpaRepository.SelectIBoardID(inquiry);
			inquiry.setIno(Ino);
	}
	
	@Override
	public Inquiry SelectOneInquiryContent(String iboardID) {
		return inquiryJpaRepository.SelectOneInquiryContent(iboardID);
	}
	
	@Override
	public String SelectLoginUserIDForInquiry(String loginID) {
		return inquiryJpaRepository.SelectLoginUserIDForInquiry(loginID);
	}

	@Override
	public void UpdateIBoardDelete(int iboardID) {
		inquiryJpaRepository.UpdateIBoardDelete(iboardID);
	}
	
	@Override
	public void InsertInquiryAnswer(Inquiry inquiry, HttpServletRequest request) {
		inquiryJpaRepository.InsertInquiryAnswer(inquiry);
	}
	
	@Override
	public void DeleteInquiryAnswer(int iboardID) {
		inquiryJpaRepository.DeleteInquiryAnswer(iboardID);
	}
	
	@Override
	public List<Inquiry> SelectMyInquiryList(String loginID) {
		return inquiryJpaRepository.SelectMyInquiryList(loginID);
	}
}
