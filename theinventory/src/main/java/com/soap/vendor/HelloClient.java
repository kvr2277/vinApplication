package com.soap.vendor;

public class HelloClient {
    /*@WebServiceRef(wsdlLocation="http://localhost:9280/AbcUtil/services/AnimalTypeService?wsdl")
    static AnimalTypeServiceProxy proxy;*/

    public static void main(String[] args) {
        try {
            HelloClient client = new HelloClient();
            client.doTest(args);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void doTest(String[] args) {
        try {
        	VendorSoapServiceProxy proxy = new VendorSoapServiceProxy("http://localhost:9280/NICUtil/services/VendorSoapService?wsdl");
            System.out.println("Retrieving the port from the following service: " + proxy);
            VendorSoapService port = proxy.getVendorSoapService();
            System.out.println("Invoking the sayHello operationon the port.");

            String name;
            if (args.length > 0) {
                name = args[0];
            } else {
                name = "No Name";
            }

            String response = port.vendorName(name);
            System.out.println(response);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
} 