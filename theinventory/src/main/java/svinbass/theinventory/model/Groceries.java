package svinbass.theinventory.model;

import java.util.Date;

public class Groceries {
	
	/*private Item item;
	private int units;*/
	private String state;
	private String location;
	private String vendor;	
	private Date purchDate;
	private String itemName;
	private String itemQty;
	private String itemPrice;
	
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public Date getPurchDate() {
		return purchDate;
	}
	public void setPurchDate(Date purchDate) {
		this.purchDate = purchDate;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemQty() {
		return itemQty;
	}
	public void setItemQty(String itemQty) {
		this.itemQty = itemQty;
	}
	public String getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
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
	
		

}
