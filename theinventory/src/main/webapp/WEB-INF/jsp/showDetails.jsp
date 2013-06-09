<%@page import="svinbass.theinventory.model.Groceries"%>
<%@page import="java.util.List"%>
<%@page import="svinbass.theinventory.model.Item"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<div align="left">
		<h1>Entered groceries</h1>
	</div>
	<div align="center">
		<a href="<c:url value="/j_spring_security_logout" />"> Logout</a>
	</div>
	<%
		Groceries grocery = (Groceries) request.getAttribute("groceries");
		List<Item> itemList = grocery.getItemList();
	%>
	<div>
		<table>
			<tr>
				<td width="150">State</td>
				<td><%=grocery.getState()%></td>
			</tr>
			<tr>
				<td>Location</td>
				<td><%=grocery.getLocation()%></td>
			</tr>
			<tr>
				<td>Vendor Name</td>
				<td><%=grocery.getVendor()%></td>
			</tr>
			<tr>
				<td>Purchase Date</td>
				<td><%=grocery.getPurchDate()%></td>
			</tr>
			<%-- <tr>
				<td>Item Name</td>
				<td><%=grocery.getItemName()%></td>
			</tr> 
			<tr>
				<td>Item Quantity</td>
				<td><%=grocery.getItemQty()%></td>
			</tr> --%>
			<tr>
				<td>Total Value (Rs.)</td>
				<td><%=grocery.getTotalPrice()%></td>
			</tr>
		</table>

	</div>
</body>
</html>