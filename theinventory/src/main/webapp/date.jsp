<head>
<title>Current Date and Time</title>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
<script type="text/javascript">
	function displayTime() {
		$.ajax({
			url : "Mydate.jsp",
			success : function(data) {
				$("#content").html(data);
			}
		});
		setTimeout("timeExam()", 1);
	}
</script>
</head>

<body onload="displayTime();">
	<table width="100%">
		<tr>
			<td width="100%" align="center">
				<div id="content"
					style="color: blue; font: bold 14px arial; padding-top: 140px;">
				</div>
			</td>
		</tr>
	</table>
</body>
</html>