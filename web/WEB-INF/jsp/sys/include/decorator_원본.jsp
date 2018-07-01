<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:choose>

<c:when test="${header['X-Requested-With'] == 'XMLHttpRequest'}">
<decorator:body />
</c:when>

<c:otherwise>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="WOWPMD">
<link rel="icon" href="../../favicon.ico">
<title>WOWPMD</title>

<link href="/vendors/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="/vendors/bootstrap-3.3.7/css/bootstrap-theme.css" rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="/css/navbar-fixed-top.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="/js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
  <script src="/js/html5shiv.min.js"></script>
  <script src="/js/respond.min.js"></script>
<![endif]-->

<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="/js/jquery-1.12.4.min.js"><\/script>')</script>

<script src="/vendors/bootstrap-3.3.7/js/bootstrap.min.js"></script>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="/js/ie10-viewport-bug-workaround.js"></script>

</head>

<body>
	<!-- Fixed navbar -->
	<c:import url="/navbar"/>
	<!-- Fixed navbar -->

	<!--
	<div class="container">
	 -->

	    <c:import url="/breadcrumb"/>


		<decorator:body />
	<!--
	</div>
	 -->

</body>
</html>

</c:otherwise>
</c:choose>