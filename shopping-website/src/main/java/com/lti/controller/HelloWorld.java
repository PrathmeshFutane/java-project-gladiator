package com.lti.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
	@GetMapping("/hello")
	public String helloWorld() {
		System.out.println("Hiii Jeshma");
		return "from shopping project";
	}
}
