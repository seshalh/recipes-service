package com.recipes.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// authentication
	 @Override
	 @Autowired
	    public void configure(AuthenticationManagerBuilder auth) throws Exception 
	    {
	       auth.inMemoryAuthentication().withUser("admin").password("{noop}password").roles("USER");
	    }

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			
			 http.httpBasic().and().authorizeRequests().antMatchers("/api/**").hasRole("USER")
			.and().csrf().disable();
			 http.cors();


		}
	
}
