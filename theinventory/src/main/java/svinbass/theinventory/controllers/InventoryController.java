package svinbass.theinventory.controllers;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import svinbass.theinventory.helper.WebServiceHelper;
import svinbass.theinventory.model.Business;
import svinbass.theinventory.model.Groceries;
import svinbass.theinventory.model.Item;

@Controller
@RequestMapping(value = { "/bookinventory", "/showContent", "/getContact",
		"/testFileUpload", "/upload" })
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

	@RequestMapping(value = "/getContact", method = RequestMethod.POST)
	public @ResponseBody
	String showVendorContact(@ModelAttribute("groceries") Groceries groceries,
			BindingResult result) {
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

	@RequestMapping(value = "/testFileUpload", method = RequestMethod.POST)
	public ModelAndView showVendorContact() {
		ModelAndView view = new ModelAndView("testFileUpload");

		return view;
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody
	String showVendorContact(MultipartHttpServletRequest request,
			HttpServletResponse response) {
		
		String reslt = "File Upload Failed";
		if (request instanceof MultipartHttpServletRequest) {
			System.out.println(" Inside Multipart");

			Iterator<String> itr = ((MultipartHttpServletRequest) request)
					.getFileNames();

			MultipartFile mpf = ((MultipartHttpServletRequest) request)
					.getFile(itr.next());
			File tmpFile = new File(System.getProperty("java.io.tmpdir")
					+ System.getProperty("file.separator")
					+ mpf.getOriginalFilename());
			try {
				mpf.transferTo(tmpFile);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(mpf.getOriginalFilename() + " uploaded!");
			reslt = mpf.getOriginalFilename();
			System.out.println("result "+reslt);
		}

		return reslt;
	}
}