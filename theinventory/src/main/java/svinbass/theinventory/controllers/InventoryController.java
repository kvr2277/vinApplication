package svinbass.theinventory.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.util.AutoPopulatingList;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import svinbass.theinventory.helper.ItemFillerHelper;
import svinbass.theinventory.model.Groceries;
import svinbass.theinventory.model.Item;

@Controller
@RequestMapping(value = { "/bookinventory", "/showContent" })
public class InventoryController {

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
	public ModelAndView addContact(
			@ModelAttribute("groceries") Groceries groceries,
			BindingResult result) {

		ModelAndView view = new ModelAndView("showDetails");
		view.addObject("groceries", groceries);
		return view;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAutoGrowNestedPaths(false);
	}
}