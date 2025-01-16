package com.ll.groupware_renewal.service;

import com.ll.groupware_renewal.entity.UserList;
import com.ll.groupware_renewal.repository.UserListJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminService {
    private final UserListJpaRepository userListJpaRepository;

    public List<UserList> SelectUserlist() throws Exception {
        return userListJpaRepository.findAll();
    }

    public List<UserList> SelectDormantUserList() {
        return userListJpaRepository.SelectDormantUserList();
    }

    public List<UserList> SelectWithdrawalUserList() {
        return userListJpaRepository.SelectWithdrawalUserList();
    }

}
