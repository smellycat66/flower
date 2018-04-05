package com.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	@RequestMapping(value="login",method=RequestMethod.POST)
	public ModelAndView login(String username,String password) {
		if(this.checkParams(new String[]{username,password})) {
			//if(checkData(username,password))
			ModelAndView mav=new ModelAndView("succ");
			mav.addObject("username",username);
			mav.addObject("password",password);
			return mav;
		}
		else return new ModelAndView("wrong");
	}
	
	
	private boolean checkParams(String[] params) {
		for(String param:params){  
            if(param==""||param==null||param.isEmpty()){  
                return false;  
            }  
        }  
        return true;  
	}
	
	
}
