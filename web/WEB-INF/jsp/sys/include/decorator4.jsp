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
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="WOWPMD">
<link rel="icon" href="../../favicon.ico">
<title>WOWPMD</title>

<!-- Bootstrap core CSS -->
<link href="/vendors/bootstrap-4.0.0/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="/vendors/bootstrap-4.0.0/navbar-top-fixed.css" rel="stylesheet">

<!-- Bootstrap core JavaScript ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="/js/jquery-1.12.4.min.js"><\/script>')</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<!--
<script src="/vendors/tether-1.4.0/tether.min.js"></script>
 -->
<script src="/vendors/bootstrap-4.0.0/js/bootstrap.min.js"></script>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="/js/ie10-viewport-bug-workaround.js"></script>

</head>
<body>

<nav class="navbar navbar-toggleable-md navbar-inverse fixed-top bg-inverse">
	<a class="navbar-brand" href="#">Fixed navbar</a>
	<div class="collapse navbar-collapse" id="navbarCollapse">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active">
				<a class="nav-link" href="#">Home </a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="#">Link</a>
			</li>
			<li class="nav-item">
				<a class="nav-link disabled" href="#">Disabled</a>
			</li>
		</ul>

		<form class="form-inline mt-2 mt-md-0">
			<input class="form-control mr-sm-2" type="text" placeholder="Search">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
		</form>
	</div>
</nav>

<!--
<decorator:body />

<div class="container">
	Main component for a primary marketing message or call to action
	<div class="jumbotron">
		<h1>Navbar example</h1>
		<p>
			This example is a quick exercise to illustrate how the default, static and fixed to top navbar work. It includes the responsive CSS and HTML, so it also adapts to your viewport and device.
		</p>
		<p>
			To see the difference between static and fixed top navbars, just scroll.
		</p>
		<p>
			<a class="btn btn-lg btn-primary" href="../../components/#navbar" role="button">View navbar docs &raquo;</a>
		</p>
	</div>
</div>
 -->



</body>
</html>

</c:otherwise>
</c:choose>