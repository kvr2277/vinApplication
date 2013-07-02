<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="svinbass.theinventory.model.Item"%>
<%@page import="svinbass.theinventory.model.Groceries"%>
<html>
<head>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/jquery.autocomplete.css"
	type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/kiranaVendor.css"
	type="text/css" />

<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.js"></script>

<script type="text/javascript"
	src="http://jquery.bassistance.de/validate/jquery.validate.js"></script>
<script
	src="http://jquery.bassistance.de/validate/additional-methods.js"></script>
<script type="text/javascript"
	src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
	
<script type="text/javascript">
	$(document).ready(function() {
		$("#ownerdob").datepicker({
			dateFormat : 'dd/mm/yy'
		});
		$("#agencystartdate").datepicker({
			dateFormat : 'dd/mm/yy'
		});
		$("#kiranaVendorForm").validate({
			rules : {
				//these are names and not ids :)
				agencyName : {
					required : true,
					lettersonly : true
				},
				ownerName :  {
					required : true,
					lettersonly : true
				},
				ownerDob :  {
					required : true,
					dateITA: true 
					},
				shopAddr :  {
					required : true
					},
				startDate :  {
					required : true,
					dateITA: true 
					},
				contactNo :  {
					required : true,
					digits : true,
					minlength : 10
				},
				agencyIdentity :  {
					required : true
					},
				accountDetails :  {
					required : true
					}
			},
			messages : {
				agencyName : { required: 'Please enter Agency Name', lettersonly : 'Please Enter Proper Name' },
				ownerName : { required: 'Please enter Owner Name', lettersonly : 'Please Enter Proper Name' },
				ownerDob : { required: 'Please enter Owner DOB', dateITA: 'Date format dd/mm/yyyy'},
				shopAddr : { required: 'Please enter Shop Address'},
				startDate : { required: 'Please enter Agency Start Date', dateITA: 'Date format dd/mm/yyyy'},
				contactNo : { required: 'Please enter Contact Number', digits : 'Please Enter Proper phone number', minlength: 'Please enter atleast 10 digits' },
				agencyIdentity : { required: 'Please enter Agency Identity'},
				accountDetails : { required: 'Please enter Account Details'}

			}
		});

	});
</script>
</head>
<title>Kirana Vendor Page</title>
</head>
<body>
	<div align="left">
		<h2>Enter Kirana Vendor</h2>
	</div>
	<div align="center">
		<a href="<c:url value="/j_spring_security_logout" />"> Logout</a>
	</div>

	
	<form:form modelAttribute="kiranaVendor" method="post" id="kiranaVendorForm"
		action="showContent">
		<table>
			<tr>
				<td width="150"><form:label path="agencyName">Vendor Agency Name</form:label></td>
				<td><form:input path="agencyName" /></td>
			</tr>
			<tr>
				<td width="150"><form:label path="ownerName">Owner Name</form:label></td>
				<td><form:input path="ownerName" /></td>
			</tr>
			<tr>
				<td width="150"><form:label path="ownerDob">Owner DOB</form:label></td>
				<td><form:input path="ownerDob" id="ownerdob"/></td>
			</tr>
			<tr>
				<td><form:label path="shopAddr">Shop Address</form:label></td>
				<td><form:input path="shopAddr" /></td>
			</tr>
			<tr>
				<td><form:label path="startDate">Start Date of Agenecy</form:label></td>
				<td><form:input path="startDate" id="agencystartdate"/></td>
			</tr>
			<tr>
				<td><form:label path="contactNo">Contact No</form:label></td>
				<td><form:input path="contactNo" /></td>
			</tr>
			<tr>
				<td><form:label path="agencyIdentity">Identity of Agenecy</form:label></td>
				<td><form:input path="agencyIdentity" /></td>
			</tr>
			<tr>
				<td><form:label path="accountDetails">Account Details</form:label></td>
				<td><form:input path="accountDetails" /></td>
			</tr>


		</table>

		
		<input type="submit" value="Submit Entry" />
	</form:form>
</body>
</html>