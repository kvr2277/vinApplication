package svinbass.theinventory.helper;

import org.codehaus.jackson.map.ObjectMapper;

import svinbass.theinventory.model.Business;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class WebServiceHelper {

	public static void main(String[] args) {
		
		Business input = new Business();
		input.setName("Google");
		input.setIdNumber("001");	
		
		WebServiceHelper wshelper = new WebServiceHelper();
		System.out.println("Contact number "+wshelper.getContactNumber(input));
	}

	public String getContactNumber(Business input) {

		String contactNumber = "";

		try {

			Client client = Client.create();
			WebResource webResource = client.resource("http://localhost:9280/NICUtil/rest/contact/post");

			ObjectMapper mapper = new ObjectMapper();
			ClientResponse response = webResource.type("application/json").post(ClientResponse.class,mapper.writeValueAsString(input));

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			}
			
			System.out.println("**Web Service Successful in helper**\n\n");
			contactNumber = response.getEntity(String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return contactNumber;
	}
}
