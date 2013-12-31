<%@page import="svinbass.theinventory.model.Purchase"%>
<%@page import="java.util.List"%>
<%@page import="svinbass.theinventory.model.Item"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<div align="left">
		<h1>Entered Groceries</h1>
	</div>
	<div align="center">
		<a href="<c:url value="/j_spring_security_logout" />"> Logout</a>
	</div>
	<%
		Purchase purchase = (Purchase) request.getAttribute("purchase");
	%>
	<div>
		<table>
			<tr>
				<td width="150">State</td>
				<td><%=purchase.getVendor().getAddress().getState()%></td>
			</tr>
			<tr>
				<td>Location</td>
				<td><%=purchase.getPurchLocation()%></td>
			</tr>
			<tr>
				<td>Vendor Name</td>
				<td><%=purchase.getVendor().getVendorName()%></td>
			</tr>
			<tr>
				<td>Vendor ID</td>
				<td><%=purchase.getVendor().getVendorId()%></td>
			</tr>
			<%-- <tr>
				<td>Vendor Contact</td>
				<td><%=grocery.getVendorContact()%></td>
			</tr> --%>
			<tr>
				<td>Purchase Date</td>
				<td><%=purchase.getPurchDate()%></td>
			</tr>
			<c:forEach var="grocery" items="${purchase.groceryList}">
				<tr>
					<td><c:out value="${grocery.name}" /></td>
					<td><c:out value="${grocery.price}" /></td>
				<tr>
			</c:forEach>
			<tr>
				<td>Total Value (Rs.)</td>
				<td><%=purchase.getTotalPrice()%></td>
			</tr>
		</table>

	</div>
</body>
</html>