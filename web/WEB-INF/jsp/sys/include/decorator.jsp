<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:out value="${header['X-Requested-With']}"/>


<c:choose>

<c:when test="${header['X-Requested-With'] == 'XMLHttpRequest'}">
<decorator:body />
</c:when>

<c:otherwise>

<!DOCTYPE html>
<html lang="ko">
<head>
<!-- Meta, title, CSS, favicons, etc1111. -->
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="/images/favicon.ico" type="/image/ico" />

<title>:::SPARK:::</title>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

<!-- Font Awesome -->
<link href="/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">

<!-- NProgress -->
<link href="/vendors/nprogress/nprogress.css" rel="stylesheet">

<!-- iCheck -->
<link href="/vendors/iCheck/skins/flat/green.css" rel="stylesheet">

<!-- bootstrap-progressbar -->
<link href="/vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">

<!-- JQVMap -->
<link href="/vendors/jqvmap/dist/jqvmap.min.css" rel="stylesheet"/>

<!-- bootstrap-daterangepicker -->
<link href="/vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">

<!-- bootstrap-datetimepicker -->
<link href="/vendors/bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.css" rel="stylesheet">

<!-- Custom Theme Style -->
<!-- 
<link href="/build/css/custom.min.css" rel="stylesheet">
 -->
<link href="/build/css/custom.css" rel="stylesheet">

<link rel="stylesheet" type="text/css" media="screen" href="/css/jquery-ui-1.12.1.custom.min.css" />
<link rel="stylesheet" type="text/css" media="screen" href="/css/ui.jqgrid.css" />
<link rel="stylesheet" href="/css/ui.multiselect.css"/>
<style>
table {
	font-size:98%
}
</style>

<script type="text/javascript" src="/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/script/system.js"></script>

<script type="text/javascript" src="<c:url value="/js/jquery.meiomask.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/common.js?v="/><%=System.currentTimeMillis()%>"></script>
<script type="text/javascript" src="/js/com.js?v=<%=System.currentTimeMillis()%>"></script>


<script type="text/javascript" src="/js/jqGrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="/js/jqGrid/i18n/grid.locale-kr.js"></script>


<script type="text/javascript" src="/js/jquery.oLoader.js"></script>
<%--
<script type="text/javascript">
$(function() {
    // 날짜 선택기 디폴트 옵션을 설정한다.
    if ($.datepicker && $.datepicker.setDefaults) {
        var options = $.datepicker.regional["ko"];

        options.showOn          = "button";
        options.buttonText      = "달력";
        options.buttonImage     = "<c:url value="/images/board/day.png" />";
        options.buttonImageOnly = true;
        options.changeYear      = true;
        options.changeMonth     = true;
        options.showButtonPanel = true;
        options.dateFormat      = "yy-mm-dd";

        $.datepicker.setDefaults(options);
    }

    // 마스크 디폴트 옵션을 설정한다.
    if ($.mask && $.mask.options) {
        var options = $.mask.options

        options.selectCharsOnFocus = false;
        options.autoTab            = false;

        $.mask.options = options;
    }
});
</script>
--%>

<decorator:head />
</head>

	<body class="nav-md">
		<div class="container body">
			<div class="main_container">
				<div class="col-md-3 left_col">
					<div class="left_col scroll-view">

						<div class="navbar nav_title" style="border: 0;">
							<a href="/" class="site_title"><i class="fa fa-paw"></i> <span>SPARK</span></a>
						</div>
						<div class="clearfix"></div>
						<br />

						<!-- sidebar menu -->
						<c:import url="/comm/leftMenu"/>
						<!-- /sidebar menu -->

					</div>
				</div>

				<!-- top navigation -->
				<div class="top_nav">
					<div class="nav_menu">
						<nav>
							<div class="nav toggle">
								<a id="menu_toggle"><i class="fa fa-bars"></i></a>
							</div>

							<ul class="nav navbar-nav navbar-right">
								<li class="">
									<a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
										관리자 <span class=" fa fa-angle-down"></span>
									</a>
									<ul class="dropdown-menu dropdown-usermenu pull-right">
										<li><a href="javascript:;"> 회원정보</a></li>
										<li><a href="javascript:;">Help</a></li>
										<li><a href="/logout"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
									</ul>
								</li>
							</ul>
						</nav>
					</div>
				</div>
				<!-- /top navigation -->

				<!-- page content -->
				<div class="right_col" role="main">
					<decorator:body />
				</div>
				<!-- /page content -->

				<!-- footer content -->
				<footer>
					<div class="pull-right">
						SPARK - Made by <a href="http://www.wowpmd.co.kr" target="_blank">WOWPMD</a>
					</div>
					<div class="clearfix"></div>
				</footer>
				<!-- /footer content -->

			</div>
		</div>

		<!-- Bootstrap -->
		<!--
		<script src="/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
		 -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

		<!-- FastClick -->
		<script src="/vendors/fastclick/lib/fastclick.js"></script>

		<!-- NProgress -->
		<script src="/vendors/nprogress/nprogress.js"></script>

		<!-- validator -->
		<script src="/vendors/validator/validator.js"></script>

		<!-- Chart.js -->
		<script src="/vendors/Chart.js/dist/Chart.min.js"></script>

		<!-- gauge.js -->
		<script src="/vendors/gauge.js/dist/gauge.min.js"></script>

		<!-- bootstrap-progressbar -->
		<script src="/vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>

		<!-- iCheck -->
		<script src="/vendors/iCheck/icheck.min.js"></script>

		<!-- Skycons -->
		<script src="/vendors/skycons/skycons.js"></script>

		<!-- Flot -->
		<script src="/vendors/Flot/jquery.flot.js"></script>
		<script src="/vendors/Flot/jquery.flot.pie.js"></script>
		<script src="/vendors/Flot/jquery.flot.time.js"></script>
		<script src="/vendors/Flot/jquery.flot.stack.js"></script>
		<script src="/vendors/Flot/jquery.flot.resize.js"></script>

		<!-- Flot plugins -->
		<script src="/vendors/flot.orderbars/js/jquery.flot.orderBars.js"></script>
		<script src="/vendors/flot-spline/js/jquery.flot.spline.min.js"></script>
		<script src="/vendors/flot.curvedlines/curvedLines.js"></script>

		<!-- DateJS -->
		<script src="/vendors/DateJS/build/date.js"></script>

		<!-- JQVMap -->
		<script src="/vendors/jqvmap/dist/jquery.vmap.js"></script>
		<script src="/vendors/jqvmap/dist/maps/jquery.vmap.world.js"></script>
		<script src="/vendors/jqvmap/examples/js/jquery.vmap.sampledata.js"></script>

		<!-- bootstrap-daterangepicker -->
		<script src="/vendors/moment/min/moment.min.js"></script>
		<script src="/vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
		<script src="/vendors/bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"></script>

		<script src="/build/js/custom.js?v=<%=System.currentTimeMillis()%>"></script>

<script>
//강제로 메뉴 가리기
$(document).ready(function() {
	//$('#menu_toggle').trigger('click');
});
</script>

	</body>
</html>

</c:otherwise>
</c:choose>