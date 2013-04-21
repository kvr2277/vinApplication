<%@page import="svinbass.theinventory.model.Groceries"%>
<html>
 <body>
  <h1>Entered groceries</h1>
  <%Groceries grocery = (Groceries)request.getAttribute("groceries"); %>
  <div>
  <br>State : <%=grocery.getState() %>
  <br>Location : <%=grocery.getLocation() %>
  <br>Vendor Name : <%=grocery.getVendor() %>
  <br>Purchase Date : <%=grocery.getPurchDate() %>
  <br>Item Name : <%=grocery.getItemName() %>
  <br>Item Quantity : <%=grocery.getItemQty() %>
  <br>Item Price : <%=grocery.getItemPrice() %>
  
  
  </div>
 </body>
</html>