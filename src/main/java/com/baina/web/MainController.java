package com.baina.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/login")
	public String getlogin() {
		return "login";
	}
	
	@GetMapping("/")
	public String getIndex() {
		return "index";
	}

}
