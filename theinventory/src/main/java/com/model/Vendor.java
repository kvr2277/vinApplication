package com.model;

import java.io.Serializable;

public class Vendor implements Serializable{

	private static final long serialVersionUID = -7731044722233394392L;
	private int vendorId;
	private String vendorName;
	private String vendorTan;
	private String vendorType;
	private Address address;

	public Vendor() {
		address = new Address();
	}
	
	public Vendor(int vendorId, String vendorName, String vendorTan,
			String vendorType, Address address) {
		super();
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.vendorTan = vendorTan;
		this.vendorType = vendorType;
		this.address = address;
	}
	public int getVendorId() {
		return vendorId;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getVendorTan() {
		return vendorTan;
	}
	public void setVendorTan(String vendorTan) {
		this.vendorTan = vendorTan;
	}
	public String getVendorType() {
		return vendorType;
	}
	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	

	@Override
	public String toString() {
		return "Vendor [vendorId=" + vendorId + ", vendorName=" + vendorName
				+ ", vendorTan=" + vendorTan + ", vendorType=" + vendorType
				+ ", address=" + address + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + vendorId;
		result = prime * result
				+ ((vendorName == null) ? 0 : vendorName.hashCode());
		result = prime * result
				+ ((vendorTan == null) ? 0 : vendorTan.hashCode());
		result = prime * result
				+ ((vendorType == null) ? 0 : vendorType.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vendor other = (Vendor) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (vendorId != other.vendorId)
			return false;
		if (vendorName == null) {
			if (other.vendorName != null)
				return false;
		} else if (!vendorName.equals(other.vendorName))
			return false;
		if (vendorTan == null) {
			if (other.vendorTan != null)
				return false;
		} else if (!vendorTan.equals(other.vendorTan))
			return false;
		if (vendorType == null) {
			if (other.vendorType != null)
				return false;
		} else if (!vendorType.equals(other.vendorType))
			return false;
		return true;
	}

}
