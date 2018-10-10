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

    $("#frm").attr("action", "<c:url value="/acc/accAcc/acco9002" />").submit();
}

function acco9002Callback(data) {
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


<div class="bsc1020">
  	<div class="page-title">

    	<div class="row">
    		<div class="col-md-12">
				<div class="x_panel">
					<div class="x_title">
						<h2>거래처 등록</h2>
                    	<div class="clearfix"></div>
					</div>

					<div class="x_content">
						<form class="form-horizontal form-label-left input_mask" id="frm" name="frm" method="post" action="/popup/bsc1021/insert">

                      		<div class="form-group">
		                        <label class="control-label col-md-3 col-sm-3 col-xs-12">거래처코드 </label>
		                        <div class="col-md-9 col-sm-9 col-xs-12">
		                          	<input type="text" class="form-control" disabled="disabled" placeholder="자동생성">
		                        </div>
                      		</div>

                      		<div class="form-group">
                        		<label class="control-label col-md-3 col-sm-3 col-xs-12">거래처명</label>
                        		<div class="col-md-9 col-sm-9 col-xs-12">
	                          		<input type="text" class="form-control" name="bcncNm" id="bcncNm" placeholder="" required="required">
	                        	</div>
                      		</div>

                      		<div class="form-group">
		                        <label class="control-label col-md-3 col-sm-3 col-xs-12">사업자번호 </label>
		                        <div class="col-md-9 col-sm-9 col-xs-12">
		                          	<input type="text" class="form-control" name="bizrno" id="bizrno" placeholder="">
		                        </div>
                      		</div>

                      		<div class="form-group">
		                        <label class="control-label col-md-3 col-sm-3 col-xs-12">대표자명 </label>
		                        <div class="col-md-9 col-sm-9 col-xs-12">
		                          	<input type="text" class="form-control" name="rprsntvNm" id="rprsntvNm" placeholder="">
		                        </div>
                      		</div>

                      		<div class="form-group">
		                        <label class="control-label col-md-3 col-sm-3 col-xs-12">전화번호 </label>
		                        <div class="col-md-9 col-sm-9 col-xs-12">
		                          	<input type="text" class="form-control" name="telno" id="telno" placeholder="">
		                        </div>
                      		</div>

                      		<div class="form-group">
		                        <label class="control-label col-md-3 col-sm-3 col-xs-12">이메일 </label>
		                        <div class="col-md-9 col-sm-9 col-xs-12">
		                          	<input type="text" class="form-control" name="email" id="email" placeholder="">
		                        </div>
                      		</div>

                      		<div class="form-group">
		                        <label class="control-label col-md-3 col-sm-3 col-xs-12">동 </label>
		                        <div class="col-md-9 col-sm-9 col-xs-12">
		                          	<input type="text" class="form-control" name="dong" id="dong" placeholder="">
		                        </div>
                      		</div>

                      		<div class="form-group">
		                        <label class="control-label col-md-3 col-sm-3 col-xs-12">호 </label>
		                        <div class="col-md-9 col-sm-9 col-xs-12">
		                          	<input type="text" class="form-control" name="ho" id="ho" placeholder="">
		                        </div>
                      		</div>

                      		<div class="form-group">
		                        <label class="control-label col-md-3 col-sm-3 col-xs-12">입주구분 </label>
		                        <div class="col-md-9 col-sm-9 col-xs-12">
			                        <%--
			                        <ui:select iD="mvnSe" name="mvnSe" value="${param.mvnSe}" clazz="form-control" key="mvnSe" defaultValue="N" except="N"/>
			                         --%>
			                        <ui:select iD="mvnSe" name="mvnSe" value="${param.mvnSe}" clazz="form-control" key="mvnSe" defaultValue="선택" required="required"/>
		                        </div>
                      		</div>

                      		<div class="ln_solid"></div>

                      		<div class="form-group">
                        		<div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-3">
                          			<button type="button" class="btn btn-primary btn-cancel">취소</button>
                          			<!--
						   			<button type="reset" class="btn btn-primary">Reset</button>
						   			 -->
                          			<button type="submit" class="btn btn-success btn-add">등록</button>
                        		</div>
                      		</div>
                    	</form>

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

	</body>
</html>


