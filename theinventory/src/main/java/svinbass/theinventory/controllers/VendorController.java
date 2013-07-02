package svinbass.theinventory.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import svinbass.theinventory.model.KiranaVendor;


@Controller
@RequestMapping(value = { "/addVendor" })
public class VendorController {
	
	
	@RequestMapping("/addVendor")
	public ModelAndView addVendor(){
		KiranaVendor kiranaVendor = new KiranaVendor();
		return new ModelAndView("kiranaVendor", "kiranaVendor", kiranaVendor);
		
	}


}
