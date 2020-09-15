package com.baina.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.baina.dto.UserRegistrationDto;
import com.baina.model.User;

public interface UserService extends UserDetailsService {

	User save(UserRegistrationDto userRegistrationDto);
	
}
