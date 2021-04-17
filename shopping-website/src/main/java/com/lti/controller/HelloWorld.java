package com.lti.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
	@GetMapping("/hello")
	public String helloWorld() {
		System.out.println("Hiii Jeshma");
		System.out.println("Hii Muskaan");
	
		
		System.out.println("Hii Shreyash");

		System.out.println("prathmesh");
		System.out.println("Hi shreyash prathmesh");

		return "from shopping project";
	}
}
