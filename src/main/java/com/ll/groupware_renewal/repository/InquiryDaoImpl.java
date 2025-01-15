package com.ll.groupware_renewal.repository;

import com.ll.groupware_renewal.entity.Inquiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public class InquiryDaoImpl implements InquiryJpaRepository {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public void InsertIBoardInfo(Inquiry inquiry) {
		sqlSession.insert("InsertInquiry", inquiry);

	}
	@Override
	public List<Inquiry> SelectInquiryList() {
		List<Inquiry> InquiryOutput = sqlSession.selectList("SelectInquiryList");
		return InquiryOutput;
	}
	
	@Override
	public int SelectIBoardID(Inquiry inquiry) {
		int Bno = sqlSession.selectOne("SelectIBoardID", inquiry);
		return Bno;
	}

	@Override
	public Inquiry SelectOneInquiryContent(String iboardID) {
		return sqlSession.selectOne("SelectOneInquiryContent", iboardID);
	}
	

	@Override
	public String SelectLoginUserIDForInquiry(String loginID) {
		return sqlSession.selectOne("SelectLoginUserIDForInquiry", loginID);
	}
	
	@Override
	public void UpdateIBoardDelete(int iboardID) {
		sqlSession.delete("UpdateIBoardDelete", iboardID);
	}
	
	@Override
	public void InsertInquiryAnswer(Inquiry inquiry) {
		sqlSession.update("UpdateInquiryAnswer", inquiry);

	}
	
	@Override
	public void DeleteInquiryAnswer(int iboardID) {
		sqlSession.update("DeleteInquiryAnswer", iboardID);
	}
	
	@Override
	public List<Inquiry> SelectMyInquiryList(String loginID) {
		return sqlSession.selectList("SelectMyInquiryList", loginID);
	}
}
