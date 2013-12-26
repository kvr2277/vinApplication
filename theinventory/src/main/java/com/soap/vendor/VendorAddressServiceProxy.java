package com.soap.vendor;

public class VendorAddressServiceProxy implements com.soap.vendor.VendorAddressService {
  private String _endpoint = null;
  private com.soap.vendor.VendorAddressService vendorAddressService = null;
  
  public VendorAddressServiceProxy() {
    _initVendorAddressServiceProxy();
  }
  
  public VendorAddressServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initVendorAddressServiceProxy();
  }
  
  private void _initVendorAddressServiceProxy() {
    try {
      vendorAddressService = (new com.soap.vendor.VendorAddressServiceServiceLocator()).getVendorAddressService();
      if (vendorAddressService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)vendorAddressService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)vendorAddressService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (vendorAddressService != null)
      ((javax.xml.rpc.Stub)vendorAddressService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.soap.vendor.VendorAddressService getVendorAddressService() {
    if (vendorAddressService == null)
      _initVendorAddressServiceProxy();
    return vendorAddressService;
  }
  
  public com.model.Address getVendorAddress(java.lang.String vendorId) throws java.rmi.RemoteException{
    if (vendorAddressService == null)
      _initVendorAddressServiceProxy();
    return vendorAddressService.getVendorAddress(vendorId);
  }
  
  
}