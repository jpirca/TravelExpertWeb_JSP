package com.travelexperts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.travelexperts.service.UserService;

@Controller
public class MainController {

	@Autowired
	UserService userService;
	
	// Controller which redirect user to the index page
	@RequestMapping("/")
	//@ResponseBody
	public String testFunction() {
		return "index";
	}
	
	@RequestMapping("/register")
	public String callRegister() {
		return "register";
	}
	
	//
	@RequestMapping("/emailconfirmation")
	public String confirmEmail() {
		return "emailconfirmation";
	}
		
	@RequestMapping("/success")
	public String emailConfirmed(String token, Integer id) {
		return userService.emailConfirmed(token,id);
	}
}
