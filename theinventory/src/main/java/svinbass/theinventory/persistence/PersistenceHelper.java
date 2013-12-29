package svinbass.theinventory.persistence;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import com.model.Address;
import com.model.Vendor;

public class PersistenceHelper {
	
	private Session session;
	
	private void createSession(){
		session = new Configuration().configure().buildSessionFactory().openSession();
	}
	
	public void saveAddress(Address address){
		if(session == null){
			createSession();
		}
		
		session.beginTransaction();
		session.save(address);
		session.getTransaction().commit();	
	}

	public void saveAddressAndVendor(Address address, Vendor vendor){
		if(session == null){
			createSession();
		}
		
		session.beginTransaction();
		session.save(address);
		session.save(vendor);
		session.getTransaction().commit();	
	}
}
