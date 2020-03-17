package com.indi.basicsocialapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.indi.basicsocialapp.model.AuthRequest;
import com.indi.basicsocialapp.model.AuthResponse;
import com.indi.basicsocialapp.service.AuthUserDetailsService;
import com.indi.basicsocialapp.util.JwtUtils;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private AuthUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtils jwtTokenUtil;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authenticationRequest) throws Exception {
		try {
			authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthResponse(jwt));
	}
	
}
