package svinbass.theinventory.controllers;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
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

import com.model.Address;

import svinbass.theinventory.logic.BusinessLogic;
import svinbass.theinventory.model.Business;
import svinbass.theinventory.model.Groceries;
import svinbass.theinventory.model.Item;
import svinbass.theinventory.ws.WebServiceHelper;

@Controller
@RequestMapping(value = { "/bookinventory", "/showContent", "/getContact",
		"/testFileUpload", "/upload", "/uploadToWS", "/getFullName", "/getAddress" })
public class InventoryController {

	WebServiceHelper wsHelper = new WebServiceHelper();
	BusinessLogic bs = new BusinessLogic();

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
	public ModelAndView addContent(
			@ModelAttribute("groceries") Groceries groceries,
			BindingResult result) {

		bs.saveAddrandVendor();
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
	
	@RequestMapping(value = "/getFullName", method = RequestMethod.POST)
	public @ResponseBody
	String showVendorFullName(@ModelAttribute("groceries") Groceries groceries,
			BindingResult result) {
		String str = "No Value Received";

		if (!result.hasErrors()) {
			str = fullNameBuilder(groceries);
		
		} else {
			str = "An error has occured while binding";
		}

		return str;
	}
	
	@RequestMapping(value = "/getAddress", method = RequestMethod.POST)
	public @ResponseBody
	String getVendorAddress(@ModelAttribute("groceries") Groceries groceries,
			BindingResult result) {
		Address adr = null;
		String str = "No response received";
		
		wsHelper = new WebServiceHelper();
		adr = wsHelper.getVendorAddress(groceries.getVendorID());

		ObjectMapper mapper = new ObjectMapper();
		try {
			str = 	mapper.writeValueAsString(adr);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Address JSON is : "+str);
		return str;
	}
	
	private String fullNameBuilder(Groceries groceries) {
		String vendorFullName = null;
		Business bus = new Business();
		bus.setName(groceries.getVendor());
		bus.setIdNumber(groceries.getVendorID());

		vendorFullName = wsHelper.getVendorFullName(bus.getIdNumber());

		return vendorFullName;
	}

	private void contactBuilder(Groceries groceries) {
		String contact = null;
		Business bus = new Business();
		bus.setName(groceries.getVendor());
		bus.setIdNumber(groceries.getVendorID());

		contact = wsHelper.contactNumberClient(bus);

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
	String uploadOnSameServer(MultipartHttpServletRequest request,
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
			reslt = mpf.getOriginalFilename();
		}

		return reslt;
	}
	
	@RequestMapping(value = "/uploadToWS", method = RequestMethod.POST)
	public @ResponseBody
	String uploadToService(MultipartHttpServletRequest request,
			HttpServletResponse response) {
		
		String reslt = "File Upload to Service Failed";
		
		if (request instanceof MultipartHttpServletRequest) {
			
			Iterator<String> itr = ((MultipartHttpServletRequest) request)
					.getFileNames();

			MultipartFile mpf = ((MultipartHttpServletRequest) request)
					.getFile(itr.next());
					
			reslt = wsHelper.fileUploadClient(mpf);
		}

		return reslt;
	}
}