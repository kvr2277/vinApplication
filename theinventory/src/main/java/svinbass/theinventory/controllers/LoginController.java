package svinbass.theinventory.controllers;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import svinbass.theinventory.model.Login;

public class LoginController {
	
	 @RequestMapping(value = "/login", method = RequestMethod.POST)
	    public String addContact(@ModelAttribute("login")
	    			Login login, BindingResult result) {
		if("admin".equals(login.getUserName()) && "admin".equals(login.getPassWord())) { 
			return "redirect:/bookinventory";
		}else{
			login.setMsg("Invalid Login");
			return "forward:/login.jsp";
		}
    }
	

}
