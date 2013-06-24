package svinbass.theinventory.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections15.FactoryUtils;
import org.apache.commons.collections15.ListUtils;


public class Groceries {
	
	private String state;
	private String location;
	private String vendor;	
	private String purchDate;
	private String totalPrice;
	private Map<String,String> itemList;
	//private AutoPopulatingList<Item> groceryList = new AutoPopulatingList(Item.class);
	private List<Item> groceryList = ListUtils.lazyList(new ArrayList<Item>(), FactoryUtils.instantiateFactory(Item.class));  
		
	
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getPurchDate() {
		return purchDate;
	}
	public void setPurchDate(String purchDate) {
		this.purchDate = purchDate;
	}	
	public List<Item> getGroceryList() {
		return groceryList;
	}
	public void setGroceryList(List<Item> groceryList) {
		this.groceryList = groceryList;
	}
	public Map<String, String> getItemList() {
		return itemList;
	}
	public void setItemList(Map<String, String> itemList) {
		this.itemList = itemList;
	}
	
	
		

}
