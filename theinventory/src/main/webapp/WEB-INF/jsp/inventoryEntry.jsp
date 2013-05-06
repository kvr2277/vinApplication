<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery.autocomplete.css" type="text/css" />
<script type="text/javascript"	src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript"	src="<%=request.getContextPath()%>/resources/js/grider.js"></script>
<script type="text/javascript"	src="http://jzaefferer.github.com/jquery-validation/jquery.validate.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
<!-- <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.js"></script> -->
<!-- <script type="text/javascript"
	src="http://dev.jquery.com/view/trunk/plugins/validate/jquery.validate.js"></script> 
 -->

<style type="text/css">
* {
	font-family: Verdana;
	font-size: 96%;
}

label {
	width: 10em;
	float: left;
}

label.error {
	float: none;
	color: red;
	padding-left: .5em;
	vertical-align: top;
}

p {
	clear: both;
}

.submit {
	margin-left: 12em;
}

em {
	font-weight: bold;
	padding-right: 1em;
	vertical-align: top;
}

table {
	border-collapse: collapse
}

.num {
	text-align: right
}

input {
	width: 8em;
}

td {
	padding: 2px;
}

.summary {
	text-align: right;
	font-weight: bold;
}

h2 {
	font-size: 1.3em;
	font-weight: bold;
	background: #3B84BF;
}
</style>

<script type="text/javascript">
	function capitaliseFirstLetter(string) {
		return string.charAt(0).toUpperOil() + string.slice(1);
	}

	$(document).ready(
			function() {
				var stateData = [ "Andhra Pradesh", "Arunachal Pradesh",
						"Assam", "Bihar", "Chhattisgarh", "Goa", "Gujarat",
						"Haryana", "Himachal Pradesh", "Jammu & Kashmir",
						"Jharkhand", "Karnataka", "Kerala", "Madhya Pradesh",
						"Maharashtra", "Manipur", "Meghalaya", "Mizoram",
						"Nagaland", "Orissa", "Punjab", "Rajasthan", "Sikkim",
						"Tamil Nadu", "Tripura", "Uttaranchal", "UttarPradesh",
						"WestBengal" ];
				$("#datepicker").datepicker({
					dateFormat : 'dd-mm-yy'
				});
				$("#stateId").autocomplete({
					source : stateData
				});
				$('#table1').grider({countRow: true, countRowAdd: true});
				/* $("#location").text(function(i, text) {
				    return text.substr(0,1).toUpperOil() + text.substr(1);
				}); */
				$("#commentForm").validate();
				/*$("#commentForm").validate({
					rules : {
						stateId : {
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
						stateId : "Please enter a state."
						location : "Please enter a location."
						vendor : "Please enter a vendor."
						itemName : "Please enter a item name."
					} 
				});*/
			});
</script>



</head>
<title>Inventory Entry Page</title>
</head>
<body>
	<div align="left">
		<h2>Enter Inventory</h2>
	</div>
	<div align="center">
		<a href="<c:url value="/j_spring_security_logout" />"> Logout</a>
	</div>
	<form:form method="post" id="commentForm" action="showContent">

		<table>
			<tr>
				<td width="150"><form:label path="state">State</form:label></td>
				<td><input id="stateId" name="state" class="required"
					value="Andhra Pradesh" /></td>
			</tr>
			<tr>
				<td><form:label path="location">Location</form:label></td>
				<td><form:input path="location" class="required" /></td>
			</tr>
			<tr>
				<td><form:label path="vendor">Vendor Name</form:label></td>
				<td><form:input path="vendor" id="vendor" class="required" /></td>
			</tr>
			<tr>
				<td><form:label path="purchDate">Purchase Date</form:label></td>
				<td><input type="text" name="purchDate" id="datepicker"
					class="required" /></td>
			</tr>
			
		</table>

		<br><br><br><br><br><br><br><br><br><br><br><br>
		<table border="1" id="table1">
			<tr>
				<th col="product" class="testclass">Product</th>
				<th col="price" class="testclass" summary="max">Price</th>
				<th col="quantity" class="testclass" summary="avg">Quantity</th>
				<th col="discount" class="testclass">Discount</th>
				<th col="subtotal" class="testclass"
					formula="price*quantity*(1 - 0.10 * discount)" summary="sum">Subtotal</th>
			</tr>
			<tr>
				<td><select name="det[0][product]">
						<option value="1">Rice</option>
						<option value="2">Milk</option>
						<option value="3" selected="selected">Potato</option>
						<option value="4">Tomato</option>
						<option value="5">Banana</option>
						<option value="6">Onion</option>
						<option value="7">Brinjal</option>
						<option value="8">Salt</option>
						<option value="9">Sugar</option>
						<option value="10">Oil</option>
				</select></td>
				<td><input name="det[0][price]" type="text" class="num"
					value="10" /></td>
				<td><input name="det[0][quantity]" type="text" class="num"
					value="1" /></td>
				<td><input name="det[0][discount]" type="checkbox"
					checked="checked" /></td>
				<td class="num"></td>
			</tr>
			<tr>
				<td><select name="det[1][product]">
						<option value="1">Rice</option>
						<option value="2">Milk</option>
						<option value="3">Potato</option>
						<option value="4">Tomato</option>
						<option value="5">Banana</option>
						<option value="6">Onion</option>
						<option value="7">Brinjal</option>
						<option value="8">Salt</option>
						<option value="9">Sugar</option>
						<option value="10">Oil</option>
				</select></td>
				<td><input name="det[1][price]" type="text" class="num"
					value="2.33" /></td>
				<td><input name="det[1][quantity]" type="text" class="num"
					value="3" /></td>
				<td><input name="det[1][discount]" type="checkbox" /></td>
				<td class="num"></td>
			</tr>
			<tr>
				<td><select name="det[2][product]">
						<option value="1">Rice</option>
						<option value="2">Milk</option>
						<option value="3">Potato</option>
						<option value="4">Tomato</option>
						<option value="5">Banana</option>
						<option value="6">Onion</option>
						<option value="7">Brinjal</option>
						<option value="8">Salt</option>
						<option value="9">Sugar</option>
						<option value="10">Oil</option>
				</select></td>
				<td><input name="det[2][price]" type="text" class="num"
					value="5.5" /></td>
				<td><input name="det[2][quantity]" type="text" class="num"
					value="2" /></td>
				<td><input name="det[2][discount]" type="checkbox" /></td>
				<td class="num"></td>
			</tr>
		</table>
		
		<div>
			<input type="submit" value="Submit Entry" />
		</div>
	</form:form>

</body>
</html>