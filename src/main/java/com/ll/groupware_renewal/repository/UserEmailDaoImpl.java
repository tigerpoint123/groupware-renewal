package com.ll.groupware_renewal.repository;

import com.ll.groupware_renewal.constant.ConstantAdminUserEmailDao;
import com.ll.groupware_renewal.entity.UserEmail;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository

public class UserEmailDaoImpl implements UserEmailDao {
	private ConstantAdminUserEmailDao Constant;

	@SuppressWarnings("resource")
	public UserEmailDaoImpl() {
		GenericXmlApplicationContext CTX = new GenericXmlApplicationContext();
		CTX.load("classpath:/xmlForProperties/UserEmailDao.xml");
		CTX.refresh();
		this.Constant = (ConstantAdminUserEmailDao) CTX.getBean("UserEmailDaoID");
	}

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public void InsertCertification(UserEmail userEmail) {
		this.sqlSession.insert(this.Constant.getInsertCertification(), userEmail);
	}

	@Override
	public boolean SelectForCheckCertification(int authNum) {
		UserEmail Output = this.sqlSession.selectOne(this.Constant.getSelectForCheckCertification(), authNum);
		if (Output == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void DeleteCertification(UserEmail userEmail) {
		this.sqlSession.delete(this.Constant.getDeleteCertification(), userEmail);
	}
}
