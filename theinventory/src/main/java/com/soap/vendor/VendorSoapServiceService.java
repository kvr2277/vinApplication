/**
 * VendorSoapServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.soap.vendor;

public interface VendorSoapServiceService extends javax.xml.rpc.Service {
    public java.lang.String getVendorSoapServiceAddress();

    public com.soap.vendor.VendorSoapService getVendorSoapService() throws javax.xml.rpc.ServiceException;

    public com.soap.vendor.VendorSoapService getVendorSoapService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
