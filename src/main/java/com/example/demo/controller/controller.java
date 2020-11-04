package com.example.demo.controller;

import java.io.File;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {

	public final static String Report_Path = "/tmp/api-reports/";
	
	@GetMapping("/createFolder")
	public String createDir() {
		new File(Report_Path).mkdir();
		File file = new File(Report_Path + "TEST-.html");
		return "created";
	}
}
