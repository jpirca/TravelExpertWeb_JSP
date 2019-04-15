package com.travelexperts.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableGlobalMethodSecurity(prePostEnabled=true)
@EnableWebSecurity
@Configuration
public class TravelExpertsConfig extends WebSecurityConfigurerAdapter {
	
	@Override
    public void configure(HttpSecurity http) throws Exception {
       http.csrf().disable().authorizeRequests()
        .antMatchers("/").permitAll()
    //    .antMatchers(HttpMethod.POST,"/newuser").permitAll()
    //    .antMatchers(HttpMethod.POST, "/login").permitAll()
        .antMatchers(HttpMethod.POST,"/users/*").permitAll() 
        .antMatchers(HttpMethod.GET,"/packages/*").permitAll()
        .antMatchers(HttpMethod.GET,"/bookings/*").permitAll()
        .antMatchers(HttpMethod.GET,"/customers/*").permitAll()
        .anyRequest().authenticated();
    }
  /*  @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers().antMatchers("/users/all").;
    }*/
}
