package svinbass.theinventory.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Business {

	String name;
	String idNumber;
	String mobile;
	
	@XmlElement(name="name")
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name="idNumber")
	public String getIdNumber() {
		return idNumber;
	}


	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	@XmlElement(name="mobile")
	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	@Override
	public String toString() {
		return "Track [name=" + name + ", idNumber=" + idNumber + "+ mobile=" + mobile+"]";
	}

}
