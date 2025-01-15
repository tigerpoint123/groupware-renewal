package com.ll.groupware_renewal.dao;

import com.ll.groupware_renewal.constant.ConstantAdminUserListDao;
import com.ll.groupware_renewal.dto.UserList;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public class UserListDaoImpl implements UserListDao {
	private ConstantAdminUserListDao Constant;

	@SuppressWarnings("resource")
	public UserListDaoImpl() {
		GenericXmlApplicationContext CTX = new GenericXmlApplicationContext();
		CTX.load("classpath:/xmlForProperties/UserListDao.xml");
		CTX.refresh();
		this.Constant = (ConstantAdminUserListDao) CTX.getBean("UserListDaoID");
	}

	@Autowired
	private SqlSession sqlSession;

	// userList를 호출하기 위한 dao
	@Override
	public List<UserList> SelectUserlist() throws Exception {
		List<UserList> Output = this.sqlSession.selectList(this.Constant.getSelectUserList());
		return Output;
	}

	// 휴먼계정 List 호출
	@Override
	public List<UserList> SelectDormantUserList() {
		List<UserList> Output = this.sqlSession.selectList(this.Constant.getSelectDormantList());
		return Output;
	}

	// 탈퇴계정 List 호출
	@Override
	public List<UserList> SelectWithdrawalUserList() {
		List<UserList> Output = this.sqlSession.selectList(this.Constant.getSelectWithdrawalList());
		return Output;
	}
}
