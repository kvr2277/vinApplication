package svinbass.theinventory.model;

public class Item {
	private String name;
	private double price;
	private String type;
	private int quantity;
	private boolean discount;

	public Item() {

	}

	public Item(String name, double price, String type) {
		super();
		this.name = name;
		this.price = price;
		this.type = type;
	}

	public Item(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isDiscount() {
		return discount;
	}

	public void setDiscount(boolean discount) {
		this.discount = discount;
	}

}
