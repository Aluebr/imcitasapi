package com.cji.citas.service;

import com.cji.citas.entity.Users;
import com.cji.citas.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails; 
import org.springframework.security.core.userdetails.UserDetailsService; 
import org.springframework.security.core.userdetails.UsernameNotFoundException; 
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.stereotype.Service; 

import java.util.Optional; 

@Service
public class UserInfoService implements UserDetailsService { 

	@Autowired
	private UserInfoRepository repository;

	@Autowired
	private PasswordEncoder encoder; 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Users> userDetail = repository.findByName(username);

		// Converting userDetail to UserDetails 
		return userDetail.map(UserInfoDetails::new) 
				.orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
	}

	public String addUser(Users userInfo) {
		try {
			if (userInfo.getPassword() == null || userInfo.getPassword().trim().isEmpty()) {
				return "";
			}
			userInfo.setPassword(encoder.encode(userInfo.getPassword()));
			if (userInfo.getRoles() == null) {
				userInfo.setRoles("ROLE_USER");
			}
			repository.save(userInfo);
			return "User Added Successfully";
		} catch (Exception ex) {
			ex.getMessage();
			return "El usuario o el correo ya existen";
		}
	}


	public String getEmailByUsername(String username) {
		Optional<Users> userDetail = repository.findByName(username);
		return userDetail.map(Users::getEmail).orElse(null);
	}

} 
