package com.indi.basicsocialapp.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityRelated {

	public String encryptPassword(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}
	
}
