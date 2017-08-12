package svinbass.theinventory.ws;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.multipart.MultipartFile;

import svinbass.theinventory.aws.AWSS3Helper;
import svinbass.theinventory.model.Business;

import com.model.Address;
import com.soap.vendor.VendorAddressService;
import com.soap.vendor.VendorAddressServiceProxy;
import com.soap.vendor.VendorSoapService;
import com.soap.vendor.VendorSoapServiceProxy;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.multipart.BodyPart;
import com.sun.jersey.multipart.MultiPart;

public class WebServiceHelper {

	private static final Logger logger = Logger
			.getLogger(WebServiceHelper.class);

	public static void main(String[] args) {

		Business input = new Business();
		input.setName("Google");
		input.setIdNumber("001");

		WebServiceHelper wshelper = new WebServiceHelper();
		// logger.info("Vendor Full Name :"+wshelper.getVendorFullName(input.getIdNumber()));

		// wshelper.getVendorAddress("001");
		wshelper.testJson(input);

		// logger.info("Contact number " + wshelper.contactNumberClient(input));
		// File file = new File("F:/Goodies/tmp/sparrow.jpg");
		// wshelper.fileUploadClient(file);

	}

	private void testJson(Business input) {
		String str = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			str = mapper.writeValueAsString(input);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info("JSON is : " + str);

	}

	private void parseJSON() {
		ObjectMapper mapper = new ObjectMapper();
		try {

			// read from file, convert it to user class
			Business user = mapper.readValue(new File("D:\\temp\\file.json"),
					Business.class);

			// display to console
			logger.info(user);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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

			logger.info("**Web Service Successful in helper**\n\n");
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
			// logo = FileUtils.readFileToByteArray(file);
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

			logger.info("id is " + filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return filePath;
	}

	public String getVendorFullName(int vendorId) {
		String response = null;
		try {

			VendorSoapServiceProxy proxy = new VendorSoapServiceProxy(
					"http://localhost:9280/NICUtil/services/VendorSoapService?wsdl");
			VendorSoapService port = proxy.getVendorSoapService();

			response = port.vendorName(String.valueOf(vendorId));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public Address getVendorAddress(int vendorId) {
		Address addrResp = null;
		try {

			VendorAddressServiceProxy proxy = new VendorAddressServiceProxy(
					"http://localhost:9280/NICUtil/services/VendorAddressService?wsdl");
			VendorAddressService service = proxy.getVendorAddressService();

			addrResp = service.getVendorAddress(String.valueOf(vendorId));

			logger.info("Response Addrress is : " + addrResp.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return addrResp;
	}

	public String processMultipart(MultipartFile mpf) {

		logger.info("inside processMultipart ");
		String id = UUID.randomUUID().toString();

		boolean isProcessed = false;
		boolean useS3 = true;
		String message = null;
		try {

			if (useS3) {

				final Properties props = new Properties();
				props.load(new FileInputStream("/var/www/html/app.properties"));
				String directory1 = props.getProperty("temp.directory");
				logger.info("Inside processMultipart folder1 " + directory1);

				File convFile1 = new File(directory1, mpf.getOriginalFilename());
				convFile1.createNewFile();
				logger.info("created new file in system ");
				FileOutputStream fos1 = new FileOutputStream(convFile1);
				fos1.write(mpf.getBytes());
				logger.info("written content to system file at "+directory1);
				fos1.close();
				AWSS3Helper.putFileInS3(convFile1);
			} else {

				File convFile = new File("E:/Goodies/images/VIN.jpg");
				convFile.createNewFile();
				FileOutputStream fos = new FileOutputStream(convFile);

				// storing the image to file system.
				if (convFile.isDirectory()) {
					fos.write(mpf.getBytes());
					fos.close();
				} else {
					convFile.mkdirs();
					fos.write(mpf.getBytes());
					fos.close();
				}
			}
			isProcessed = true;

		} catch (Exception e) {
			logger.info("Exception ", e);
			message = e.getMessage();
		}
		if (isProcessed) {
			logger.info("exiting processMultipart id " + id);
			return id;
		}

		logger.info("exiting processMultipart message " + message);

		return "Failed to process attachments. Reason : " + message;
	}
}
