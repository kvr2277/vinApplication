package svinbass.theinventory.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import svinbass.theinventory.model.Groceries;

@Controller
@RequestMapping(value={"/bookinventory","/showContent"})
public class InventoryController {
		
	@RequestMapping("/bookinventory")
    public ModelAndView showContacts() {         
		return new ModelAndView("inventoryEntry", "command", new Groceries());
    }
	
	 @RequestMapping(value = "/showContent", method = RequestMethod.POST)
	    public ModelAndView addContact(@ModelAttribute("groceries")
	    			Groceries groceries, BindingResult result) {
		 ModelAndView view = new ModelAndView("showDetails");
	      view.addObject("groceries", groceries);
	      return view;
	    }
}