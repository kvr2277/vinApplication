package svinbass.theinventory.helper;

import java.io.File;
import java.io.IOException;

import javax.ws.rs.core.MediaType;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.multipart.MultipartFile;

import svinbass.theinventory.model.Business;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.multipart.BodyPart;
import com.sun.jersey.multipart.MultiPart;

public class WebServiceHelper {

	public static void main(String[] args) {

		Business input = new Business();
		input.setName("Google");
		input.setIdNumber("001");

		WebServiceHelper wshelper = new WebServiceHelper();
		System.out.println("Contact number " + wshelper.contactNumberClient(input));
		File file = new File("F:/Goodies/tmp/sparrow.jpg");
	//	wshelper.fileUploadClient(file);
		
	}

	public String contactNumberClient(Business input) {

		String contactNumber = "";

		try {

			Client client = Client.create();
			WebResource webResource = client
					.resource("http://localhost:9280/NICUtil/rest/contact/post");

			ObjectMapper mapper = new ObjectMapper();
			ClientResponse response = webResource.type("application/json")
					.post(ClientResponse.class,
							mapper.writeValueAsString(input));

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			System.out.println("**Web Service Successful in helper**\n\n");
			contactNumber = response.getEntity(String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return contactNumber;
	}

	public String fileUploadClient(MultipartFile mpf) {
		
		String filePath = "";
		Client client = Client.create();
		WebResource webResource = client
				.resource("http://localhost:9280/NICUtil/rest/contact/upload");
				byte[] logo = null;

		try {
			//logo = FileUtils.readFileToByteArray(file);
			logo = mpf.getBytes();
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		// Construct a MultiPart with two body parts
		MultiPart multiPart = new MultiPart().bodyPart(new BodyPart(logo,
				MediaType.APPLICATION_OCTET_STREAM_TYPE));

		// POST the request
		try {
			ClientResponse response = webResource.type("multipart/mixed").post(
					ClientResponse.class, multiPart);
			filePath = response.getEntity(String.class);
			
			System.out.println("id is "+filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return filePath;
	}

}
