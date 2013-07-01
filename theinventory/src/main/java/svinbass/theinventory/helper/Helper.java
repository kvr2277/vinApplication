package svinbass.theinventory.helper;

import java.io.File;

import javax.ws.rs.core.MediaType;

import org.apache.commons.io.FileUtils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.multipart.BodyPart;
import com.sun.jersey.multipart.MultiPart;
 
public class Helper {
  public static void main(String[] args) throws Exception {
    final String BASE_URI = "http://localhost:9280/NICUtil/rest/contact/upload";
 
    Client c = Client.create();
    WebResource service = c.resource(BASE_URI);
    File file = new File("E:/Goodies/tmp/sparrow.jpg");
    byte[] logo = FileUtils.readFileToByteArray(file);
 
    // Construct a MultiPart with two body parts
    MultiPart multiPart = new MultiPart().
      bodyPart(new BodyPart(logo, MediaType.APPLICATION_OCTET_STREAM_TYPE));
 
    // POST the request
    try{
    ClientResponse response = service.type("multipart/mixed").post(ClientResponse.class, multiPart);
    System.out.println("Response Status : " + response.getEntity(String.class));
    }catch(Exception e){
    	e.printStackTrace();
    }
    
    
  }
}