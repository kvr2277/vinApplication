package svinbass.theinventory.helper;

import java.util.ArrayList;
import java.util.List;

import svinbass.theinventory.model.Item;

public class ItemFillerHelper {
	
	private List<Item> itemList;

	public List<Item> getItemList() {
		itemList = new ArrayList<Item>();
		itemList.add(new Item("Rice", "Kgs"));
		itemList.add(new Item("Banana", "Dozens"));
		itemList.add(new Item("Milk", "Litres"));
		itemList.add(new Item("Oranges", "Units"));
		itemList.add(new Item("Biscuits", "Units"));
		itemList.add(new Item("Wheat", "Kgs"));
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
	
	
}
