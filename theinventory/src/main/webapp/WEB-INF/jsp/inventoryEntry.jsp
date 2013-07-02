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
	href="<%=request.getContextPath()%>/resources/css/inventory.css"
	type="text/css" />
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/grider.js"></script>
<script type="text/javascript"
	src="http://jquery.bassistance.de/validate/jquery.validate.js"></script>
<script type="text/javascript"
	src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>


<script type="text/javascript">

var xyz = 'sparrow.jpg';
var abc = 'Vinod.jpg';
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
						"Tamil Nadu", "Tripura", "Uttaranchal",
						"Uttar Pradesh", "West Bengal" ];
				$("#datepicker").datepicker({
					dateFormat : 'dd-mm-yy'
				});
				$("#result").hide();
				$("#stateID").autocomplete({
					source : stateData
				});
				$('#imgContent').attr('src','resources/images/'+xyz);
				$('#imgContent2').attr('src','resources/images/'+abc);
				$("#commentForm").validate({
					rules : {
						//these are names and not ids :)
						state : "required",
						location : "required",
						vendor : "required",
						vendorID : "required",
						purchDate : "required"
					},
					messages : {
						state : "Please enter state name",
						location : "Please enter a location",
						vendor : "Please enter vendor name",
						vendorID : "Please enter vendor ID",
						purchDate : "Please enter purchase date"

					}
				});
				$('#table1').grider({
					countRow : true,
					countRowAdd : true
				});

				/*$('#itemName').blur(function() {
					alert('Change method is trigerred');
				});*/
				
			});

	/* attach a submit handler to the form */
	function doAjaxPost() {

		var vendorName = $('#vendor').val();
		var vendorId = $('#vendorID').val();

		/* get some values from elements on the page: */
		//   var values = $(this).serialize();
		/* Send the data using post and put the results in a div */
		$.ajax({
			url : "getContact",
			type : "post",
			data : "vendor=" + vendorName + "&vendorID=" + vendorId,
			success : function(response) {
				document.getElementById('vendorContact').value = response;
				$("#vendorContact").text(response);
				$("#result").show();
			},
			error : function(err, response) {
				alert("failure1");
				$("#result").html(
						'there is error while submitting ajax'
								+ err.responseText);
			}
		});
	};

	//using FormData() object
	function uploadFormData() {
		$('#imgResult').html('');
		$('#imgContent').html('');
		var oMyForm = new FormData();
		oMyForm.append("file", file2.files[0]);

		$.ajax({
			url : 'upload',
			data : oMyForm,
			dataType : 'text',
			processData : false,
			contentType : false,
			type : 'POST',
			success : function(response) {
				$('#imgContent').attr('src','resources/images/'+response);
			}
		});
	};
		
		//using FormData() object
		function uploadFormDataToWS() {
			$('#imgResult2').html('');
			$('#imgContent2').html('');
			var oMyForm = new FormData();
			oMyForm.append("file", file3.files[0]);

			$.ajax({
				url : 'uploadToWS',
				data : oMyForm,
				dataType : 'text',
				processData : false,
				contentType : false,
				type : 'POST',
				success : function(response) {
					$('#imgContent2').attr('src','resources/images/VIN.jpg');
				}
			});
		};
	
</script>

</head>
<title>Inventory Entry Page</title>
</head>
<body>
	<div align="left">
		<h2>Enter Inventory</h2>
	</div>
	<div align="center">
		<a href="<c:url value="/addVendor" />">Add Vendor</a>
	</div>
	<div align="right">
		<a href="<c:url value="/j_spring_security_logout" />"> Logout</a>
	</div>

	<%-- <form:form modelAttribute="groceries" method="post" id="commentForm"
		action="testFileUpload"> --%>
	<form:form modelAttribute="groceries" method="post" id="commentForm"
		action="showContent">
		<table>
			<tr>
				<td width="150"><form:label path="state">State</form:label></td>
				<td><input id="stateID" name="state" /></td>
			</tr>
			<tr>
				<td><form:label path="location">Location</form:label></td>
				<td><form:input path="location" /></td>
			</tr>
			<tr>
				<td><form:label path="vendor">Vendor Name</form:label></td>
				<td><form:input path="vendor" id="vendor" /></td>
			</tr>
			<tr>
				<td><form:label path="vendorID">Vendor ID</form:label></td>
				<td><form:input path="vendorID" id="vendorID" /></td>
				<td><input type="button" value="Get Contact"
					onclick="doAjaxPost()"><br /></td>
			</tr>
			<tr>
				<td><form:label path="purchDate">Purchase Date</form:label></td>
				<td><input type="text" name="purchDate" id="datepicker" /></td>
			</tr>
		</table>

		<div id="result">
			<table>
				<tr>
					<td width="150">Vendor Contact</td>
					<td><input id="vendorContact" name="vendorContact" /></td>
				</tr>
			</table>
		</div>

		<form:hidden path="totalPrice"></form:hidden>
		<form:hidden path="vendorContact"></form:hidden>

		<table border="1" id="table1">
			<tr>
				<th col="product">Product</th>
				<th col="quantity">Quantity</th>
				<th col="price">Price per unit</th>
				<th col="discount">Discount</th>
				<th col="subtotal" formula="price*quantity*(1 - 0.10 * discount)"
					summary="sum">Subtotal</th>
			</tr>
			<c:forEach var="prod" varStatus="grocStatus"
				items="${groceries.groceryList}" begin="0">
				<tr>
					<td><form:select path="groceryList[${grocStatus.index}].name"
							id="name${grocStatus.index}">
							<form:options items="${groceries.itemList}" />
						</form:select></td>
					<td><form:input
							path="groceryList[${grocStatus.index}].quantity"
							id="quantity${grocStatus.index}" value="1" /></td>
					<td><form:input path="groceryList[${grocStatus.index}].price"
							id="price${grocStatus.index}" value="10" /></td>
					<td><form:checkbox
							path="groceryList[${grocStatus.index}].discount"
							id="discount${grocStatus.index}" checked="checked" /></td>
					<td class="num"></td>
				</tr>
			</c:forEach>
		</table>

		<input type="submit" value="Submit Entry" />
	</form:form>

	<!-- Form 2 -->
	<form id="form2" method="post" action="upload" enctype="multipart/form-data">
		<input name="file2" id="file2" type="file" /><br />
	</form>

	<button value="Submit" onclick="uploadFormData()">Upload</button>
	<img src="#" id="imgContent" />
	<div id="imgResult"></div>

	<!-- Form 3 -->
	 <form id="form3" method="post" action="uploadToWS" enctype="multipart/form-data">
		<input name="file3" id="file3" type="file" /><br />
	</form>

	<button value="Submit" onclick="uploadFormDataToWS()">Upload to WS</button>
	<img src="#" id="imgContent2" />
	<div id="imgResult2"></div> 

</body>
</html>