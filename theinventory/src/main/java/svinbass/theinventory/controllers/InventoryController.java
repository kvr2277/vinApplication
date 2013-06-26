package svinbass.theinventory.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.util.AutoPopulatingList;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import svinbass.theinventory.helper.ItemFillerHelper;
import svinbass.theinventory.helper.WebServiceHelper;
import svinbass.theinventory.model.Business;
import svinbass.theinventory.model.Groceries;
import svinbass.theinventory.model.Item;

@Controller
@RequestMapping(value = { "/bookinventory", "/showContent", "/getContact" })
public class InventoryController {

	WebServiceHelper wsHelper;

	@RequestMapping("/bookinventory")
	public ModelAndView showContacts() {

		Groceries groceries = new Groceries();

		Map<String, String> itemList = new LinkedHashMap<String, String>();
		itemList.put("Rice", "Biyyam");
		itemList.put("Banana", "Arati Pandu");
		itemList.put("Milk", "Paalu");
		itemList.put("Wheat Flour", "Goduma Pindi");
		itemList.put("Chicken", "Chicken");
		groceries.setItemList(itemList);
		Item item = new Item();

		groceries.getGroceryList().add(item);
		groceries.getGroceryList().add(item);
		groceries.getGroceryList().add(item);
		return new ModelAndView("inventoryEntry", "groceries", groceries);
	}

	@RequestMapping(value = "/showContent", method = RequestMethod.POST)
	public ModelAndView addContact(@ModelAttribute("groceries") Groceries groceries,BindingResult result) {
		
		ModelAndView view = new ModelAndView("showDetails");
		view.addObject("groceries", groceries);
		return view;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAutoGrowNestedPaths(false);
	}
	

	@RequestMapping(value = "/getContact", method = RequestMethod.POST)
	public @ResponseBody
		String showVendorContact(@ModelAttribute("groceries") Groceries groceries, BindingResult result) {
		String str = "No Value Received";
		
		if (!result.hasErrors()) {
			contactBuilder(groceries);
			str = groceries.getVendorContact();
		} else {
			str = "An error has occured while binding";
		}

		return str;
	}
	
	private void contactBuilder(Groceries groceries) {
		String contact = null;
		Business bus = new Business();
		bus.setName(groceries.getVendor());
		bus.setIdNumber(groceries.getVendorID());

		wsHelper = new WebServiceHelper();
		contact = wsHelper.getContactNumber(bus);

		if (contact != null) {
			groceries.setVendorContact(contact);
		}
	}
}