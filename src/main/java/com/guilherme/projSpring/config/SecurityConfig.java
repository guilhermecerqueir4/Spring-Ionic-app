/*package com.guilherme.projSpring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication
.builders.AuthenticationManagerBuilder;

@Configuration
public class SecurityConfig {
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		builder.inMemoryAuthentication().withUser("user").password("{noop}user").roles("USER")
		.and().
		withUser("avanco").password("{noop}ads").roles("ADMIN");
	}
}*/