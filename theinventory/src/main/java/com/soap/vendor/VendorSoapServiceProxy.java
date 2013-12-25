package com.soap.vendor;

public class VendorSoapServiceProxy implements com.soap.vendor.VendorSoapService {
  private String _endpoint = null;
  private com.soap.vendor.VendorSoapService vendorSoapService = null;
  
  public VendorSoapServiceProxy() {
    _initVendorSoapServiceProxy();
  }
  
  public VendorSoapServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initVendorSoapServiceProxy();
  }
  
  private void _initVendorSoapServiceProxy() {
    try {
      vendorSoapService = (new com.soap.vendor.VendorSoapServiceServiceLocator()).getVendorSoapService();
      if (vendorSoapService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)vendorSoapService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)vendorSoapService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (vendorSoapService != null)
      ((javax.xml.rpc.Stub)vendorSoapService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.soap.vendor.VendorSoapService getVendorSoapService() {
    if (vendorSoapService == null)
      _initVendorSoapServiceProxy();
    return vendorSoapService;
  }
  
  public java.lang.String vendorName(java.lang.String vendorId) throws java.rmi.RemoteException{
    if (vendorSoapService == null)
      _initVendorSoapServiceProxy();
    return vendorSoapService.vendorName(vendorId);
  }
  
  
}