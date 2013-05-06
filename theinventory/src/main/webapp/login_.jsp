<%@page import="svinbass.theinventory.model.Login"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.js"></script>
<head>
<%
	Login login = (Login) request.getAttribute("login");
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>`
</head>
<body onload='document.f.j_username.focus();'>
	<font color="blue" size="5"> <marquee>Please provide
				login credentials</marquee></font>
 
	<c:if test="${not empty error}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
	<%-- <div id="msgDiv" style="font-size: 150%; color: #850F0F">
		<%
			if (login != null
					&& (login.getMsg() != null || login.getMsg() != "")) {
		%>
		<br>
		<%=login.getMsg()%></br>
		<%
			}
		%>
	</div> --%>

	<form name='f' action="<c:url value='j_spring_security_check' />" method='POST'>
 <table>
			<tr align="center">
				<td width="150">User Name:</td><td><input type='text' name='j_username' value=''><br></td>
			</tr>

			<tr align="center">
				<td>Password:</td><td><input type='password' name='j_password' /><br></td>
			</tr>

			<tr align="center">
				<td><input type="submit" value="submit" /><br></td>
			</tr>
		</table>
	</form>
</body>
</html>