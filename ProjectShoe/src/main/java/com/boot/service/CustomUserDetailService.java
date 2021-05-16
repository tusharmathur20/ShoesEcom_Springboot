package com.boot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.boot.model.CustomUserDetail;
import com.boot.model.User;
import com.boot.repository.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	UserRepo userrepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	Optional<User>user=userrepo.findUserByEmail(email);
	user.orElseThrow(()-> new UsernameNotFoundException("User not found"));
	return user.map(CustomUserDetail::new).get();
		
	}

}
