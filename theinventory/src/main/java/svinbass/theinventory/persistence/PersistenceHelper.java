package svinbass.theinventory.persistence;

import org.hibernate.Query;
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
		session.saveOrUpdate(address);
		session.getTransaction().commit();	
	}

	public void saveAddressAndVendor(Address address, Vendor vendor){
		if(session == null){
			createSession();
		}
		
		session.beginTransaction();
		session.saveOrUpdate(address);
		session.merge(vendor);
		session.getTransaction().commit();	
	}
	
	public Address retrieveAddress(int vendorId){
		
		Vendor vendor = retrieveVendor(vendorId);
		return vendor.getAddress();
	}
	
	public Vendor retrieveVendor(int vendorId){
		
		if(session == null){
			createSession();
		}
		
		session.beginTransaction();

		String queryString = "from Vendor where vendorId = :id";  
		Query query = session.createQuery(queryString);
		query.setInteger("id", vendorId);
		Object queryResult = query.uniqueResult();
		
		Vendor vendor = (Vendor) queryResult;
		session.getTransaction().commit();
		return vendor;
	}
}
