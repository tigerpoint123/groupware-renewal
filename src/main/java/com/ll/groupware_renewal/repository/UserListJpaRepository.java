package com.ll.groupware_renewal.repository;

import com.ll.groupware_renewal.entity.UserList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserListJpaRepository extends JpaRepository<UserList, Long> {

    List<UserList> SelectUserlist() throws Exception;

    List<UserList> SelectDormantUserList();

    List<UserList> SelectWithdrawalUserList();

    List<UserList> findAll();
}
