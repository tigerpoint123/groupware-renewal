package com.ll.groupware_renewal.security;

import com.ll.groupware_renewal.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsersDetailsService implements UserDetailsService {
	
	@Autowired
	private UserJpaRepository userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsersDetails Users = userDao.selectByLoginID(username);
		if(Users == null) {
			 throw new UsernameNotFoundException("username " + username + " not found");
		}
		return Users;
	}

}
