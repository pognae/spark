<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.wowpmd.util.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://www.wowpmd.co.kr/taglib/ui" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<!-- Meta, title, CSS, favicons, etc1111. -->
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>:::SPARK:::</title>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

<!-- bootstrap-progressbar -->
<link href="/vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">

<!-- bootstrap-daterangepicker -->
<link href="/vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">

<!-- Custom Theme Style -->
<link href="/build/css/custom.min.css" rel="stylesheet">

<link rel="stylesheet" type="text/css" media="screen" href="/css/jquery-ui-1.12.1.custom.min.css" />
<link rel="stylesheet" type="text/css" media="screen" href="/css/ui.jqgrid.css" />
<link rel="stylesheet" href="/css/ui.multiselect.css"/>

<script type="text/javascript" src="/js/jquery-1.12.4.min.js"></script>
<!--
<script type="text/javascript" src="/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="/js/additional-methods.min.js"></script>
<script type="text/javascript" src="/js/messages_ko.min.js"></script>
 -->
<script type="text/javascript" src="/script/system.js"></script>

<script type="text/javascript" src="<c:url value="/js/jquery.meiomask.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/common.js?v="/><%=System.currentTimeMillis()%>"></script>
<script type="text/javascript" src="/js/com.js?v=<%=System.currentTimeMillis()%>"></script>

<!-- Bootstrap -->
<!--
<script src="/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

<!-- NProgress -->
<script src="/vendors/nprogress/nprogress.js"></script>

<!-- bootstrap-progressbar -->
<script src="/vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>

<!-- bootstrap-daterangepicker -->
<script src="/vendors/moment/min/moment.min.js"></script>
<script src="/vendors/bootstrap-daterangepicker/daterangepicker.js"></script>

<script type="text/javascript">
$(function() {
    // 컴포넌트를 초기화한다.
    initComp();

    bindMask();

    // 이벤트를 바인딩한다.
    bindEvent();

    /*
	$('#regDtm').datepicker({
		  showon: 'button'
			//,inline: true
     	, buttonText: 'Show Date'
     	, buttonImageOnly: true
     	, buttonImage:'/images/btn_icon_calendar.gif'
		, showButtonPanel: true
		});
	$('#regDtm').val(
		$.datepicker.formatDate('yy-mm-dd', new Date())
	);
	 */

	$("frm").validate();
});

function bindMask() {

}

/**
 * 컴포넌트를 초기화한다.
 */
function initComp() {

}

/**
 * 이벤트를 바인딩한다.
 */
function bindEvent() {
    // 조회 버튼에 클릭 이벤트를 바인딩한다.
    $(".bsc1022-search-btn").each(function(index, element) {
        $(this).bind("click", function(event) {
        	bsc1022Search();
            return false;
        });
    });

	/*
    $(".btn-add").each(function(index, element) {
        $(this).bind("click", function(event) {
            // 신청원장을 검색한다.
            submit();
            return false;
        });
    });
     */

    $(".btn-cancel").each(function(index, element) {
        $(this).bind("click", function(event) {
            cancel();
            return false;
        });
    });

 	// 검색결과 행에 클릭 이벤트를 바인딩한다.
    $(".bcncList").each(function(index, element) {
        $(this).css({
            "cursor":"pointer"
        });

        $(this).bind("click", {
        	bcncNo:$(this).find(".bcncNo").text(),
        	bcncNm:$(this).find(".bcncNm").text()
        }, function(event) {
            // 신청원장을 설정한다.
            slp1010Callback(event.data);
            return false;
        });
    });
}

function bsc1022Search(page) {
    if (page) {
        $("#page").val(page);
    }

    $("#bsc1022SearchForm").attr("action", "<c:url value="/popup/bsc1022Search"/>").submit();
}

function cancel() {
	window.close();
}

function enterSearch() {
	if (event.keyCode == 13) {
		search();
    }
    return false;
}

function slp1010Callback(data) {
	if (opener) {
        try {
            var callback = eval("opener." + $("#callback").val());

            if (callback) {
                callback.call(window, data);
            }
        } finally {
            window.close();
        }
    }
}

function winclose() {
	var callback = eval("opener." + $("#callback").val());
	callback.call(window, data);
}

window.onload = function() {
	//winResize();
}

</script>
</head>

<body style="width:1024px; background: #ffffff" onload="winResize1();" onunload="winclose();">
<form class="form-inline" name="bsc1022SearchForm" id="bsc1022SearchForm" method="post" role="form">
<input type="hidden" id="page" name="page" value="<c:out value="${param.page}" default="1" />" />
<input type="hidden" id="rows" name="rows" value="<c:out value="${param.rows}" default="5" />" />
<input type="hidden" id="callback" name="callback" value="<c:out value="${param.callback}" default="slp1010Callback" />" />

		<div class="container body">
			<div class="main_container">

				<!-- page content -->
				<div class="right_col" role="main">


<div class="bsc1022">
  	<div class="page-title">

    	<div class="row">
    		<div class="col-md-12">
				<div class="x_panel">
					<div class="x_title">
						<h2>거래처 등록</h2>
                    	<div class="clearfix"></div>
					</div>

					<div class="x_content">
						<div class="form-group">
                        	<label class="control-label" for="search">코드명 </label>
                          	<input type="text" id="search" class="form-control" placeholder="">
                      	</div>

						<div class="form-group">
							<a href="#" class="btn btn-primary btn-sm pull-right bsc1022-search-btn">검색</a>
						</div>

						<br />
		                <div class="ln_solid"></div>

						<div class="col-md-12" style="margin-left:0px">
						    <div class="row">

								<table class="table table-striped">
								  	<thead>
								    	<tr>
								      		<th scope="col">거래처코드</th>
								      		<th scope="col">거래처명</th>
								      		<th scope="col">전화번호</th>
								      		<th scope="col">사업자등록번호</th>
								      		<th scope="col">동</th>
								      		<th scope="col">호</th>
								      		<th scope="col">입주여부</th>
								    	</tr>
								  	</thead>
								  	<tbody>
								  		<c:forEach var="item" items="${data}" varStatus="status">
									    	<tr class="bcncList">
									      		<th scope="row" class="bcncNo"><c:out value="${item.bcncNo}"/></th>
									      		<td class="bcncNm"><c:out value="${item.bcncNm}"/></td>
									      		<td><c:out value="${item.telno}"/></td>
									      		<td><c:out value="${item.bizrno}"/></td>
									      		<td><c:out value="${item.dong}"/></td>
									      		<td><c:out value="${item.ho}"/></td>
									      		<td><c:out value="${item.mvnSe}"/></td>
									    	</tr>
								  		</c:forEach>
										<c:if test="${empty data}">
					                        <tr>
					                            <td colspan="7" style="text-align: center;">검색된 자료가 없습니다.</td>
					                        </tr>
				                        </c:if>
								  	</tbody>
								</table>

						    </div>
						</div>

					</div>
				</div>
    		</div>
    	</div>
    </div>
</div>

				</div>
				<!-- /page content -->

			</div>
		</div>

</form>
	</body>
</html>


