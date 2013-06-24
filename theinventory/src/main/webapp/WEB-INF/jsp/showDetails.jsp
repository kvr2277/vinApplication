<%@page import="svinbass.theinventory.model.Groceries"%>
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
		Groceries grocery = (Groceries) request.getAttribute("groceries");
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
			<c:forEach var="grocery" items="${groceries.groceryList}">
				<tr>
					<td><c:out value="${grocery.name}" /></td>
					<td><c:out value="${grocery.price}" /></td>
				<tr>
			</c:forEach>
			<tr>
				<td>Total Value (Rs.)</td>
				<td><%=grocery.getTotalPrice()%></td>
			</tr>
		</table>

	</div>
</body>
</html>