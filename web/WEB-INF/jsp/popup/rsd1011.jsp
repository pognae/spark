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

<title>:::WOWPMD:::</title>

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

	//$("frm").validate();
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
    $(".acco9002-search-button").each(function(index, element) {
        $(this).bind("click", function(event) {
            // 신청원장을 검색한다.
            search();
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

    /*
    // 테이블의 Row 클릭시 값 가져오기
    $("#tbl tr").click(function() {
        var str = ""
        var tdArr = new Array();    // 배열 선언

        // 현재 클릭된 Row(<tr>)
        var tr = $(this);
        var td = tr.children();

        // 반복문을 이용해서 배열에 값을 담아 사용할 수 도 있다.
        td.each(function(i){
            tdArr.push(td.eq(i).text());
        });

        // td.eq(index)를 통해 값을 가져올 수도 있다.
        var gnrSn = td.eq(0).text();
        var dong = td.eq(1).text();
        var ho = td.eq(2).text();


        alert(tr.text());

        //rsd1010Callback();

    });
    */

 	// 검색결과 행에 클릭 이벤트를 바인딩한다.
    $(".lonmApc").each(function(index, element) {
        $(this).css({
            "cursor":"pointer"
        });

        $(this).bind("click", {
        	dong:$(this).find(".dong").text(),
        	ho:$(this).find(".ho").text(),
        	gnrSn:$(this).find(".gnrSn").val()
        }, function(event) {
            // 신청원장을 설정한다.
            rsd1010Callback(event.data);
            return false;
        });
    });
}

function cancel() {
	window.close();
}

function submit() {
    if (com.wow.fn.isEmpty($("#bcncNm").val())) {
        alert("거래처명를 입력하세요");
        $("#bcncNm").focus();
        return;
    }

    if (com.wow.fn.isEmpty($("#newPassWd").val())) {
        alert("새로운 비밀번호를 입력하여 주십시오.");
        $("#newPassWd").focus();
        return;
    }

    if (com.wow.fn.isEmpty($("#newPassWdChk").val())) {
        alert("새로운 비밀번호 재확인을 입력하여 주십시오.");
        $("#newPassWdChk").focus();
        return;
    }

    if (!confirm("비밀번호를 변경하시겠습니까?")) {
        return;
    }

    $("#frm").attr("action", "<c:url value="/popup/com/common/comp0001Update" />").submit();
}

function enterSearch() {
	if (event.keyCode == 13) {
		search();
    }
    return false;
}

function search(page) {
    if (page) {
        $("#page").val(page);
    }

    $("#frm").attr("action", "<c:url value="/popup/rsd1011" />").submit();
}

function rsd1010Callback(data) {

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
		<div class="container body">
			<div class="main_container">

				<!-- page content -->
				<div class="right_col" role="main">


<div class="rsd1011">
  	<div class="page-title">

    	<div class="row">
    		<div class="col-md-12">

    		<form class="form-inline form-label-left" name="rsd1010SearchForm" id="rsd1010SearchForm" method="post" role="form">
				<input type="hidden" id="page" name="page" value="<c:out value="${param.page}" default="1" />" />
				<input type="hidden" id="rows" name="rows" value="<c:out value="${param.rows}" default="5" />" />
				<input type="hidden" id="callback" name="callback" value="<c:out value="${param.callback}" default="rsd1010Callback" />" />

				<div class="x_panel">

					<div class="x_title">
						<h2>세대기본정보</h2>
                    	<div class="clearfix"></div>
					</div>

					<div class="x_content">
						<div class="form-group form-group-sm">
                        	<!--
                        	<label class="control-label" for="search"> </label>
                        	 -->
                          	<input type="text" id="keyword" name="keyword" class="form-control" placeholder="">
                      	</div>

						<div class="form-group form-group-sm">
							<!--
							<a href="#" class="btn btn-primary btn-sm pull-right rsd1020-add-btn">추가</a>
							 -->
							<a href="#" class="btn btn-primary btn-sm pull-right rsd1020-search-btn">검색</a>
						</div>

						<br />
		                <div class="ln_solid"></div>

						<div class="col-md-12" style="margin-left:0px">
						    <div class="row">

								<table class="table table-striped" id="tbl">
								  	<thead>
								    	<tr>
								      		<th scope="col">번호</th>
								      		<th scope="col">동</th>
								      		<th scope="col">호</th>
								      		<th scope="col">출력 동</th>
								      		<th scope="col">출력 호</th>
								      		<th scope="col">면적</th>
								      		<!--
								      		<th scope="col">선택</th>
								      		 -->
								    	</tr>
								  	</thead>
								  	<tbody>
								  		<c:forEach var="item" items="${data}" varStatus="status">
									    	<tr class="lonmApc">
									      		<td>
									      			<c:out value="${status.index + 1}"/>
									      			<input type="hidden" name="gnrSn" id="gnrSn" class="gnrSn" value="<c:out value="${item.gnrSn}"/>"/>
									      		</td>
									      		<th scope="row" class="dong"><c:out value="${item.dong}"/></th>
									      		<th scope="row" class="ho"><c:out value="${item.ho}"/></th>
									      		<td><c:out value="${item.outptDong}"/></td>
									      		<td><c:out value="${item.outptHo}"/></td>
									      		<td><c:out value="${item.ar}"/></td>
									      		<!--
									      		<td>
									      			<input type="button" name="btnDongHo" id="btnDongHo" value="선택"/>
									      		</td>
									      		 -->
									    	</tr>
								  		</c:forEach>
										<c:if test="${empty data}">
					                        <tr>
					                            <td colspan="7" style="text-align:center;">검색된 자료가 없습니다.</td>
					                        </tr>
				                        </c:if>
								  	</tbody>
								</table>

						    </div>
						</div>

					</div>
				</div>
			</form>

    	</div>
    </div>
</div>

				</div>
				<!-- /page content -->

			</div>
		</div>

	</body>
</html>


