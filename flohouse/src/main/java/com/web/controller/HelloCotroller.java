package com.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class HelloCotroller {
	
	@RequestMapping("hello")
	public ModelAndView hello() {
		ModelAndView mav= new ModelAndView("hello");
		return mav;
	}
}
