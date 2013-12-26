/**
 * VendorAddressServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.soap.vendor;

public class VendorAddressServiceServiceLocator extends org.apache.axis.client.Service implements com.soap.vendor.VendorAddressServiceService {

    public VendorAddressServiceServiceLocator() {
    }


    public VendorAddressServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public VendorAddressServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for VendorAddressService
    private java.lang.String VendorAddressService_address = "http://localhost:9280/NICUtil/services/VendorAddressService";

    public java.lang.String getVendorAddressServiceAddress() {
        return VendorAddressService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String VendorAddressServiceWSDDServiceName = "VendorAddressService";

    public java.lang.String getVendorAddressServiceWSDDServiceName() {
        return VendorAddressServiceWSDDServiceName;
    }

    public void setVendorAddressServiceWSDDServiceName(java.lang.String name) {
        VendorAddressServiceWSDDServiceName = name;
    }

    public com.soap.vendor.VendorAddressService getVendorAddressService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(VendorAddressService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getVendorAddressService(endpoint);
    }

    public com.soap.vendor.VendorAddressService getVendorAddressService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.soap.vendor.VendorAddressServiceSoapBindingStub _stub = new com.soap.vendor.VendorAddressServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getVendorAddressServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setVendorAddressServiceEndpointAddress(java.lang.String address) {
        VendorAddressService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.soap.vendor.VendorAddressService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.soap.vendor.VendorAddressServiceSoapBindingStub _stub = new com.soap.vendor.VendorAddressServiceSoapBindingStub(new java.net.URL(VendorAddressService_address), this);
                _stub.setPortName(getVendorAddressServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("VendorAddressService".equals(inputPortName)) {
            return getVendorAddressService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://vendor.soap.com", "VendorAddressServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://vendor.soap.com", "VendorAddressService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("VendorAddressService".equals(portName)) {
            setVendorAddressServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
