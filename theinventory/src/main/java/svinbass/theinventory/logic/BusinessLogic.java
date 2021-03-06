package svinbass.theinventory.logic;

import java.io.IOException;
import java.util.List;

import svinbass.theinventory.lucene.LuceneIndexingHelper;
import svinbass.theinventory.lucene.LuceneSearchHelper;
import svinbass.theinventory.model.Purchase;
import svinbass.theinventory.model.Review;
import svinbass.theinventory.mongo.MongoHelper;
import svinbass.theinventory.persistence.PersistenceHelper;

import com.model.Vendor;
import com.model.Address;

public class BusinessLogic {
	
	PersistenceHelper ph = new PersistenceHelper();
	MongoHelper mh = new MongoHelper();

	public Address getAddress() {
		Address address = new Address();
		address.setAddressLine1("300 PARK ST");
		address.setAddressLine2("First Floor");
		address.setCity("HARRISBURGG");
		address.setState("TX");
		address.setCountry("USA");
		address.setZipcode("17110");

		return address;
	}

	public Vendor getVendor() {
		Vendor vendor = new Vendor();
		vendor.setVendorName("AMAZON");
		vendor.setVendorTan("AXYZ020P");
		vendor.setVendorType("SHO");
		vendor.setAddress(getAddress());
		return vendor;
	}
	
	public void saveAddrandVendor(Vendor vendor){
		ph.saveAddressAndVendor(vendor.getAddress(), vendor);
	}
	
	public Address retrieveAddress(int vendorId){
		Address address = null;
		address = ph.retrieveAddress(vendorId);
		return address;
	}
	
	public Vendor retrieveVendor(int vendorId){
		Vendor vendor = ph.retrieveVendor(vendorId);
		return vendor;
	}
	
	public void saveReview(Purchase purchase){
		Review rev = purchase.getReview();
		rev.setVendorId(purchase.getVendor().getVendorId());
		rev.setEmpId(purchase.getEmployee().getEmpId());
		rev.setReviewId(purchase.getEmployee().getEmpId());
		rev.setRating(3.5);
		mh.saveReview(rev);
		doLucene();
	}
	
	
	public void doLucene(){
		List<Review> reveiws = mh.findReviews();
		
		String indexDirPath = "F:/index/";
		LuceneIndexingHelper lih = new LuceneIndexingHelper(indexDirPath);
		String[] fieldIds = {"empId", "vendorId", "reviewId"};
		
		for (Review review : reveiws) {
			try {
				lih.createDocForReview( review, fieldIds);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			lih.getIndexWriter().close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		LuceneSearchHelper lsh = new LuceneSearchHelper();
		try {
			lsh.searchIndex(indexDirPath, "amazon", fieldIds);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args){
		BusinessLogic bl =new BusinessLogic();
		bl.doLucene();
	}

}
