package svinbass.theinventory.controllers;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
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

import svinbass.theinventory.logic.BusinessLogic;
import svinbass.theinventory.model.Item;
import svinbass.theinventory.model.Purchase;
import svinbass.theinventory.ws.WebServiceHelper;

import com.model.Address;
import com.model.Vendor;

@Controller
@RequestMapping(value = { "/bookinventory", "/showContent", "/getContact",
		"/testFileUpload", "/upload", "/uploadToWS", "/getFullName",
		"/getAddress" })
public class InventoryController {
	
	private static final Logger logger_c = Logger.getLogger(InventoryController.class);

	WebServiceHelper wsHelper = new WebServiceHelper();
	BusinessLogic bs = new BusinessLogic();

	@RequestMapping("/bookinventory")
	public ModelAndView showContacts(
			@ModelAttribute("purchase") Purchase purchase, BindingResult result) {
		
		logger_c.debug("Inside /bookinventory showContacts");

		Map<String, String> itemList = new LinkedHashMap<String, String>();
		itemList.put("Rice", "Biyyam");
		itemList.put("Banana", "Arati Pandu");
		itemList.put("Milk", "Paalu");
		itemList.put("Wheat Flour", "Goduma Pindi");
		itemList.put("Chicken", "Chicken");
		purchase.setItemList(itemList);
		Item item = new Item();

		purchase.getGroceryList().add(item);
		purchase.getGroceryList().add(item);
		purchase.getGroceryList().add(item);
		return new ModelAndView("inventoryEntry", "purchase", purchase);
	}

	@RequestMapping(value = "/showContent", method = RequestMethod.POST)
	public ModelAndView addContent(
			@ModelAttribute("purchase") Purchase purchase, BindingResult result) {
		
		logger_c.debug("Inside /showContent addContent");
		
		Vendor vend = purchase.getVendor();
		vend.setVendorTan("AVMP0001");
		bs.saveReview(purchase);
		bs.saveAddrandVendor(purchase.getVendor());
		ModelAndView view = new ModelAndView("showDetails");
		view.addObject("purchase", purchase);
		return view;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAutoGrowNestedPaths(false);
	}

	@RequestMapping(value = "/getContact", method = RequestMethod.POST)
	public @ResponseBody
	String showVendorContact(@ModelAttribute("purchase") Purchase purchase,
			BindingResult result) {
		String str = "No Value Received";

		if (!result.hasErrors()) {
			/*
			 * contactBuilder(purchase); str = purchase.getVendorContact();
			 */
		} else {
			str = "An error has occured while binding";
		}

		return str;
	}

	@RequestMapping(value = "/getFullName", method = RequestMethod.POST)
	public @ResponseBody
	String showVendorFullName(@ModelAttribute("purchase") Purchase purchase,
			BindingResult result) {
		String str = "No Value Received";

		int vendorId = purchase.getVendor().getVendorId();
		Vendor vendor = bs.retrieveVendor(vendorId);
		purchase.setVendor(vendor);

		str = vendor.getVendorName();

		return str;
	}

	@RequestMapping(value = "/getAddress", method = RequestMethod.POST)
	public @ResponseBody
	String getVendorAddress(@ModelAttribute("purchase") Purchase purchase,
			BindingResult result) {
		Address adr = null;
		String str = "No response received";
		int vendorId = purchase.getVendor().getVendorId();

		
			adr = bs.retrieveAddress(vendorId);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			str = mapper.writeValueAsString(adr);
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

		System.out.println("Address JSON is : " + str);
		return str;
	}

	/*
	 * private void contactBuilder(Purchase purchase) {
	 * 
	 * contact =
	 * wsHelper.contactNumberClient(purchase.getVendor().getVendorId());
	 * 
	 * if (contact != null) { purchase.setVendorContact(contact); } }
	 */

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