package com.indi.basicsocialapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.indi.basicsocialapp.filter.JwtRequestFilter;
import com.indi.basicsocialapp.service.AuthUserDetailsService;

@Configuration
@EnableWebSecurity
public class SpringCustomSecurity extends WebSecurityConfigurerAdapter {
	
//	@Autowired
//	private Environment env;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	private AuthUserDetailsService authUserDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/api/auth/login").permitAll()
		.antMatchers(HttpMethod.POST, "/api/user").permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic()
		.and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.cors().and().csrf().disable();
		
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//		
//		auth.inMemoryAuthentication()
//		.withUser(env.getProperty("project.basic_auth.user"))
//		.password(encoder.encode(env.getProperty("project.basic_auth.password")))
//		.roles("USER");
		
		auth.userDetailsService(authUserDetailsService);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
