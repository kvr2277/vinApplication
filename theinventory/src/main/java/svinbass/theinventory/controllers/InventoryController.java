package svinbass.theinventory.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import svinbass.theinventory.helper.ItemFillerHelper;
import svinbass.theinventory.model.Groceries;
import svinbass.theinventory.model.Item;

@Controller
@RequestMapping(value={"/bookinventory","/showContent"})
public class InventoryController {
		
	@RequestMapping("/bookinventory")
    public ModelAndView showContacts() {   
		
		Groceries groceries = new Groceries();
		ItemFillerHelper fillerHelper = new ItemFillerHelper();
		groceries.setItemList(fillerHelper.getItemList());
		 
		 //Testing Start
		 List<Item> itemList = groceries.getItemList();
		 for (Item item : itemList) {
			System.out.println("name "+item.getName()+" & type "+item.getType());
		}
		 //Testing end 
		 
		return new ModelAndView("inventoryEntry", "groceries", groceries);
    }
	
	 @RequestMapping(value = "/showContent", method = RequestMethod.POST)
	    public ModelAndView addContact(@ModelAttribute("groceries")
	    			Groceries groceries, BindingResult result) {
		 
		 ModelAndView view = new ModelAndView("showDetails");
	      view.addObject("groceries", groceries);
	      return view;
	    }
}