<%@page import="svinbass.theinventory.model.Login"%>

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
<body>
	<div id="msgDiv" style="font-size: 150%; color: #850F0F">
		<%
			if (login != null
					&& (login.getMsg() != null || login.getMsg() != "")) {
		%>
		<br>
		<%=login.getMsg()%></br>
		<%
			}
		%>
	</div>

	<form id="form" action="login" method="post">
		<font color="blue" size="5"> <marquee>Please provide
				login credentials</marquee></font>
		<table width="100%">

			<tr align="center">
				<td>User Name:<input type="text" name="userName" /><br></td>
			</tr>

			<tr align="center">
				<td>Password:<input type="password" name="passWord"><br>
					<br> <br></td>
			</tr>

			<tr align="center">
				<td><input type="submit" value="submit" /><br></td>
			</tr>
		</table>
	</form>
</body>
</html>