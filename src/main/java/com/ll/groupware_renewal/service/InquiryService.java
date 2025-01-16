package com.ll.groupware_renewal.service;

import com.ll.groupware_renewal.entity.Inquiry;
import com.ll.groupware_renewal.repository.InquiryJpaRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InquiryService {
	private final InquiryJpaRepository inquiryJpaRepository;

	public List<Inquiry> SelectInquiryList() {
		return inquiryJpaRepository.SelectInquiryList();
	}

	public void InsertInquiry(Inquiry inquiry, HttpServletRequest request) {
		inquiryJpaRepository.InsertIBoardInfo(inquiry);
		int Ino = inquiryJpaRepository.SelectIBoardID(inquiry);
		inquiry.setIno(Ino);
	}

	public Inquiry SelectOneInquiryContent(String iboardID) {
		return inquiryJpaRepository.SelectOneInquiryContent(iboardID);
	}

	public String SelectLoginUserIDForInquiry(String loginID) {
		return inquiryJpaRepository.SelectLoginUserIDForInquiry(loginID);
	}

	public void UpdateIBoardDelete(int iboardID) {
		inquiryJpaRepository.UpdateIBoardDelete(iboardID);
	}

	public void InsertInquiryAnswer(Inquiry inquiry, HttpServletRequest request) {
		inquiryJpaRepository.InsertInquiryAnswer(inquiry);
	}

	public void DeleteInquiryAnswer(int iboardID) {
		inquiryJpaRepository.DeleteInquiryAnswer(iboardID);
	}

	public List<Inquiry> SelectMyInquiryList(String loginID) {
		return inquiryJpaRepository.SelectMyInquiryList(loginID);
	}
}
