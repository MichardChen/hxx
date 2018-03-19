package com.framework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("common")
public class CommonController extends AbstractController{
	
	@RequestMapping("/uploadImage")
	public String uploadImage(){
		System.out.println("============");
		return "tnews/tnews.html";
	}
}
