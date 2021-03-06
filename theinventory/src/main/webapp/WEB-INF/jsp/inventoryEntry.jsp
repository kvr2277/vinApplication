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
				$("#vendorName").hide();
				$("#vendorAddress").hide();
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
					/* 	vendor : "required", */
						vendorID : "required",
						purchDate : "required"
					},
					messages : {
						state : "Please enter state name",
						location : "Please enter a location",
						/* vendor : "Please enter vendor name", */
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

		/* var vendorName = $('#vendor').val(); */
		var vendorId = $('#vendorID').val();

		/* get some values from elements on the page: */
		//   var values = $(this).serialize();
		/* Send the data using post and put the results in a div */
		$.ajax({
			url : "getContact",
			type : "post",
			/* data : "vendor=" + vendorName + "&vendorID=" + vendorId, */
			data : "vendor.vendorId=" + vendorId,
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
	
	
	function ajaxGetFullName() {

		/* var vendorName = $('#vendor').val(); */
		var vendorId = $('#vendorID').val();
		
		$.ajax({
			url : "getFullName",
			type : "post",	
			data : "vendor.vendorId=" + vendorId, 
			success : function(response) {
				document.getElementById('vendorFullName').value = response;
				$("#vendorFullName").text(response);
				$("#vendorName").show();
			},
			error : function(err, response) {
				alert("failure1");
				$("#vendorName").html(
						'there is error while submitting ajax'
								+ err.responseText);
			}
		});
	};
	
	
	function ajaxGetAddress() {

		/* var vendorName = $('#vendor').val(); */
		var vendorId = $('#vendorID').val();

		/* get some values from elements on the page: */
		//   var values = $(this).serialize();
		/* Send the data using post and put the results in a div */
		$.ajax({
			url : "getAddress",
			type : "post",
			dataType: 'json',
			data : "vendor.vendorId=" + vendorId, 
			success : function(response) {
				document.getElementById('addrLine1').value = response.addressLine1;
				document.getElementById('addrLine2').value = response.addressLine2;
				document.getElementById('venCity').value = response.city;
				document.getElementById('venState').value = response.state;
				document.getElementById('venCountry').value = response.country;
				document.getElementById('venZipcode').value = response.zipcode;
				$("#addrLine1").text(response.addressLine1);
				$("#addrLine2").text(response.addressLine2);
				$("#venCity").text(response.city);
				$("#venState").text(response.state);
				$("#venCountry").text(response.country);
				$("#venZipcode").text(response.zipcode); 
				
				$("#vendorAddress").show();
			},
			error : function(err, response) {
				alert("failure1");
				$("#vendorAddress").html(
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
	<form:form modelAttribute="purchase" method="post" id="commentForm"
		action="showContent">
		<table>
			<tr>
				<td>Purchase Date15</td>
				<td><input type="text" name="purchDate" id="datepicker" /></td>
			</tr>
			<tr>
				<td>Location1</td>
				<td><form:input id="location" path="purchLocation" /></td>
			</tr>
			<tr>
				<td width="150">State</td>
				<td><input id="stateID" name="state" /></td>
			</tr>
			<tr>
				<td><form:label path="employee.empId">Entered By (Emp ID)</form:label></td>
				<td><form:input path="employee.empId" id="empId"/></td>
			</tr>
			<tr>
				<td>Vendor ID</td>
				<td><form:input path="vendor.vendorId" id="vendorID" /></td>
				<!-- <td><input type="button" value="Get Contact"
					onclick="doAjaxPost()"><br /></td> -->
					<td><input type="button" value="Get Full Name"
					onclick="ajaxGetFullName()"><br /></td>
			</tr>
		</table>

		<!-- <div id="result">
			<table>
				<tr>
					<td width="150">Vendor Contact</td>
					<td><input id="vendorContact" name="vendorContact" /></td>
					<td><input type="button" value="Get Full Name"
					onclick="ajaxGetFullName()"><br /></td>
				</tr>
			</table>
		</div> -->
		
		<div id="vendorName">
			<table>
				<tr>
					<td width="150">Vendor Full Name</td>
					<td><form:input path="vendor.vendorName" id="vendorFullName"/></td>
					<td><input type="button" value="Get Address"
					onclick="ajaxGetAddress()"><br /></td>
				</tr>
			</table>
		</div>
		
		<div id="vendorAddress">
			<table>
				<tr>
					<td width="150">Address Line1</td>
					<td><form:input path="vendor.address.addressLine1" id="addrLine1"/><!-- <input id="addrLine1" name="addrLine1" /> --></td>
				</tr>
				<tr>
					<td width="150">Address Line2</td>
					<td><form:input path="vendor.address.addressLine2" id="addrLine2"/><!-- <input id="addrLine2" name="addrLine2" /> --></td>
				</tr>
				<tr>
					<td width="150">City</td>
					<td><form:input path="vendor.address.city" id="venCity"/><!-- <input id="venCity" name="venCity" /> --></td>
				</tr>
				<tr>
					<td width="150">State</td>
					<td><form:input path="vendor.address.state" id="venState"/><!-- <input id="venState" name="venState" /> --></td>
				</tr>
				<tr>
					<td width="150">Country</td>
					<td><form:input path="vendor.address.country" id="venCountry"/><!-- <input id="venCountry" name="venCountry" /> --></td>
				</tr>
				<tr>
					<td width="150">Zip Code</td>
					<td><form:input path="vendor.address.zipcode" id="venZipcode"/><!-- <input id="venZipcode" name="venZipcode" /> --></td>
				</tr>
			</table>
		</div>
		
		<form:textarea id="reviewId" path="review.comment" rows="5" cols="30" />
		
		
		<form:hidden path="totalPrice"></form:hidden> 
		<form:hidden path="review.vendorId"></form:hidden> 
		<form:hidden path="employee"></form:hidden> 
		<%-- <form:hidden path="vendorContact"></form:hidden> --%>
		<%-- <form:hidden path="vendor.address.addressLine1"></form:hidden> 
		<form:hidden path="vendor.address.addressLine2"></form:hidden> 
		<form:hidden path="vendor.address.city"></form:hidden> 
		<form:hidden path="vendor.address.state"></form:hidden> 
		<form:hidden path="vendor.address.country"></form:hidden>
		<form:hidden path="vendor.address.zipcode"></form:hidden> 
		<form:hidden path="employee"></form:hidden> --%>
		

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
				items="${purchase.groceryList}" begin="0">
				<tr>
					<td><form:select path="groceryList[${grocStatus.index}].name"
							id="name${grocStatus.index}">
							<form:options items="${purchase.itemList}" />
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

<!-- <script>
$('#reviewId').blur(function () {
	alert("Hi");
	 alert($('purcahse.vendor.vendorId').val());
	 document.getElementById('review.vendorId').value= $(vendor.vendorId).val();
	 alert($(review.vendorId).val());
	
	});
	</script> -->
</body>
</html>