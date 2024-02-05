package com.cji.citas.service;

import com.cji.citas.entity.Users;
import com.cji.citas.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails; 
import org.springframework.security.core.userdetails.UserDetailsService; 
import org.springframework.security.core.userdetails.UsernameNotFoundException; 
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
				return "La contraseña no puede estar vacía";
			} else if (userInfo.getName() == null || userInfo.getName().trim().isEmpty()) {
				return "El nombre no puede estar vacío";
			} else if (userInfo.getEmail() == null || userInfo.getEmail().trim().isEmpty()) {
				return "El correo no puede estar vacío";
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
	public String getUsername(String username) {
		Optional<Users> userDetail = repository.findByName(username);
		return userDetail.map(Users::getName).orElse(null);
	}

	public List<Users> getAdminUsers() {
		List<Users> users = repository.findAll();

		return users.stream()
				.filter(user -> Arrays.asList(user.getRoles().split(",")).contains("ROLE_ADMIN"))
				.collect(Collectors.toList());
	}

} 
