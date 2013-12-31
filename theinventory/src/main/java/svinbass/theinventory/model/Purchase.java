package svinbass.theinventory.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections15.FactoryUtils;
import org.apache.commons.collections15.ListUtils;

import com.model.Vendor;

public class Purchase {
	
	private int purchaseId;
	private Date purchDate;
	private String purchLocation;
	private Employee employee;
	private Vendor vendor;
	private Map<String,String> itemList;
	private List<Item> groceryList = ListUtils.lazyList(new ArrayList<Item>(), FactoryUtils.instantiateFactory(Item.class));
	private String totalPrice;
	private int tempVendorId;
	
	public Purchase(){
		employee = new Employee();
		vendor = new Vendor();
	}
	
	public Purchase(int purchaseId, Date purchDate, String purchLocation,
			Employee employee, Vendor vendor, Map<String, String> itemList,
			List<Item> groceryList, String totalPrice, int tempVendorId) {
		super();
		this.purchaseId = purchaseId;
		this.purchDate = purchDate;
		this.purchLocation = purchLocation;
		this.employee = employee;
		this.vendor = vendor;
		this.itemList = itemList;
		this.groceryList = groceryList;
		this.totalPrice = totalPrice;
		this.tempVendorId = tempVendorId;
	}

	

	public int getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}
	public Date getPurchDate() {
		return purchDate;
	}
	public void setPurchDate(Date purchDate) {
		this.purchDate = purchDate;
	}
	public String getPurchLocation() {
		return purchLocation;
	}
	public void setPurchLocation(String purchLocation) {
		this.purchLocation = purchLocation;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Vendor getVendor() {
		return vendor;
	}
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	public Map<String, String> getItemList() {
		return itemList;
	}
	public void setItemList(Map<String, String> itemList) {
		this.itemList = itemList;
	}
	public List<Item> getGroceryList() {
		return groceryList;
	}
	public void setGroceryList(List<Item> groceryList) {
		this.groceryList = groceryList;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getTempVendorId() {
		return tempVendorId;
	}
	public void setTempVendorId(int tempVendorId) {
		this.tempVendorId = tempVendorId;
	}
	@Override
	public String toString() {
		return "Purchase [purchaseId=" + purchaseId + ", purchDate="
				+ purchDate + ", purchLocation=" + purchLocation
				+ ", employee=" + employee + ", vendor=" + vendor
				+ ", itemList=" + itemList + ", groceryList=" + groceryList
				+ ", totalPrice=" + totalPrice + ", tempVendorId="
				+ tempVendorId + "]";
	}  
	
}
