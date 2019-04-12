package com.travelexperts.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	// Controller which redirect user to the index page
	@RequestMapping("/")
	@ResponseBody
	public String testFunction() {
		return "index";
	}
}
