package com.indi.basicsocialapp.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${project.test.api_prefix}")
public class TestController {
	
	interface lambdaFxnInterface {
		void theAbstractMethod(int x);
	}
	
	private void testFxn(lambdaFxnInterface fxn) {
		fxn.theAbstractMethod(2);
	}
	
	@GetMapping
	public String testMethod() {
		testFxn(i -> {
			System.out.println("I'm inside the lambda function");
			while(i>0) {
				System.out.println(i*10);
				i--;
			}
		});
		return "Working...";
	}

}
