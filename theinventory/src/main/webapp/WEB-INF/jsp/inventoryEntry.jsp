<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" href="/css/jquery.autocomplete.css"
	type="text/css" />
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.js"></script>
<!-- <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.js"></script> -->
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
<script type="text/javascript" src="http://dev.jquery.com/view/trunk/plugins/validate/jquery.validate.js"></script>


<script type="text/javascript">
	$(document).ready(
			function() {
				var data = [ "Andhra Pradesh", "Arunachal Pradesh", "Assam",
						"Bihar", "Chhattisgarh", "Goa", "Gujarat", "Haryana",
						"Himachal Pradesh", "Jammu & Kashmir", "Jharkhand",
						"Karnataka", "Kerala", "Madhya Pradesh", "Maharashtra",
						"Manipur", "Meghalaya", "Mizoram", "Nagaland",
						"Orissa", "Punjab", "Rajasthan", "Sikkim",
						"Tamil Nadu", "Tripura", "Uttaranchal", "UttarPradesh",
						"WestBengal" ];
				$("#datepicker").datepicker(); 
				/*$("#form").validate({
					rules : {
						state : {
							required : true
						},
						location : {
							required : true
						},
						vendor : {
							required : true
						},
						itemName : {
							required : true
						}
					} ,
					messages : {
						state : "Please enter a state."
						location : "Please enter a location."
						vendor : "Please enter a vendor."
						itemName : "Please enter a item name."
					} 
				});*/
			});
</script>


</script>
</head>
<title>Inventory Entry Page</title>
</head>
<body>
	<h2>Enter Inventory</h2>
	<form:form method="post" action="showContent">

		<table>
			<tr>
				<td><form:label path="state">State</form:label></td>
				<td><input id="state" name="state" /></td>
			</tr>
			<tr>
				<td><form:label path="location">Location</form:label></td>
				<td><form:input path="location" /></td>
			</tr>
			<tr>
				<td><form:label path="vendor">Vendor Name</form:label></td>
				<td><form:input path="vendor" /></td>
			</tr>
			<tr>
				<td><form:label path="purchDate">Purchase Date</form:label></td>
				<td><input type="text" name="purchDate" id="datepicker" /></td>
			</tr>
			<tr>
				<td><form:label path="itemName">Item Name</form:label></td>
				<td><form:input path="itemName" /></td>
			</tr>
			<tr>
				<td><form:label path="itemQty">Item Quantity</form:label></td>
				<td><form:input path="itemQty" /></td>
			</tr>
			<tr>
				<td><form:label path="itemPrice">Price Per Unit</form:label></td>
				<td><form:input path="itemPrice" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit Entry" /></td>
			</tr>
		</table>
	</form:form><!-- 
	<div id="hiDiv" >
		<br> Hi </br>
	</div> -->
	
</body>
</html>