<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:choose>

<c:when test="${header['X-Requested-With'] == 'XMLHttpRequest'}">
<decorator:body />
</c:when>

<c:otherwise>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="author" content="WOWPMD" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>:::WOWPMD:::</title>

<!--Stylesheet-->

<!--[if IE 7]>
<link rel="stylesheet" href="css/fontello-ie7.css"><![endif]-->

<!-- <link href="/css/layout.css" rel="stylesheet" type="text/css"> -->

<!--[if lt IE 9]>
<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

<!--[if lt IE 8]>
<style>
/* For IE < 8 (trigger hasLayout) */
.clearfix {
    zoom:1;
}
</style>
<![endif]-->

<style type="text/css">
div.submenu1, div.submenu2, div.submenu3, div.submenu4, div.submenu5, div.submenu6,
div.submenu7, div.submenu8, div.submenu9, div.submenu10, div.submenu11, div.submenu12,
div.submenu13, div.submenu14 {
	display: block;
	float: left;
}

div.panel1,div.panel2,div.panel3,div.panel4,div.panel5,div.panel6,div.panel7,div.panel8,div.panel9,div.panel10,div.panel11,div.panel12,div.panel13,div.panel14,div.panel15,div.panel16
, div.panel17, div.panel18, div.panel19, div.panel20
	{
	padding-left: 16px;
	display: none;
	color: black;
}
.ui-menu { width: 150px; font-size:12px; }
</style>

<script>
var t, mid, menuId;
$(document).ready(function() {
	$("#leftmenu a").click(function(){
		sessionStorage.setItem('midMenu', $(this).parents("div[class*=panel]").attr('class'));
		sessionStorage.setItem('menuId', $(this).attr('id'));
	});

	$(".submenu1").click(function () {$('div[class*=panel]').not($('.panel1')).slideUp('medium');$(".panel1").slideToggle('medium');});
	$(".submenu2").click(function () {$('div[class*=panel]').not($('.panel2')).slideUp('medium');$(".panel2").slideToggle('medium');});
	$(".submenu3").click(function () {$('div[class*=panel]').not($('.panel3')).slideUp('medium');$(".panel3").slideToggle('medium');});
	$(".submenu4").click(function () {$('div[class*=panel]').not($('.panel4')).slideUp('medium');$(".panel4").slideToggle('medium');});
	$(".submenu5").click(function () {$('div[class*=panel]').not($('.panel5')).slideUp('medium');$(".panel5").slideToggle('medium');});
	$(".submenu6").click(function () {$('div[class*=panel]').not($('.panel6')).slideUp('medium');$(".panel6").slideToggle('medium');});
	$(".submenu7").click(function () {$('div[class*=panel]').not($('.panel7')).slideUp('medium');$(".panel7").slideToggle('medium');});
	$(".submenu8").click(function () {$('div[class*=panel]').not($('.panel8')).slideUp('medium');$(".panel8").slideToggle('medium');});
	$(".submenu9").click(function () {$('div[class*=panel]').not($('.panel9')).slideUp('medium');$(".panel9").slideToggle('medium');});
	$(".submenu10").click(function () {$('div[class*=panel]').not($('.panel10')).slideUp('medium');$(".panel10").slideToggle('medium');});
	$(".submenu11").click(function () {$('div[class*=panel]').not($('.panel11')).slideUp('medium');$(".panel11").slideToggle('medium');});
	$(".submenu12").click(function () {$('div[class*=panel]').not($('.panel12')).slideUp('medium');$(".panel12").slideToggle('medium');});
	$(".submenu13").click(function () {$('div[class*=panel]').not($('.panel13')).slideUp('medium');$(".panel13").slideToggle('medium');});
	$(".submenu14").click(function () {$('div[class*=panel]').not($('.panel14')).slideUp('medium');$(".panel14").slideToggle('medium');});
	$(".submenu15").click(function () {$('div[class*=panel]').not($('.panel15')).slideUp('medium');$(".panel15").slideToggle('medium');});
	$(".submenu16").click(function () {$('div[class*=panel]').not($('.panel16')).slideUp('medium');$(".panel16").slideToggle('medium');});

	if (window.sessionStorage) {
		t = sessionStorage.getItem('menu');
		mid = sessionStorage.getItem('midMenu');
		menuId = sessionStorage.getItem('menuId');

		$("#leftmenu a").removeClass('pressed');
		$("#leftmenu a[id="+ menuId +"]").addClass('pressed');

		if(menuId >= 9 && menuId <= 29){
			t = 2;
			mid = $("#leftmenu a[id="+ menuId +"]").parents("div[class*=panel]").attr('class');
		}else if(menuId >= 30 && menuId <= 39){
			t = 5;
			mid = $("#leftmenu a[id="+ menuId +"]").parents("div[class*=panel]").attr('class');
		}else{
			t = 1;
			mid = $("#leftmenu a[id="+ menuId +"]").parents("div[class*=panel]").attr('class');
		}

		if(t == 2){
			$("#left1").css({"display":"none"});
			$("#left2").css({"display":"block" });
			$("#left3").css({"display":"none" });
			$("#left4").css({"display":"none" });
			$("#left5").css({"display":"none" });
		}else if(t == 3){
			$("#left1").css({"display":"none"});
			$("#left2").css({"display":"none" });
			$("#left3").css({"display":"block" });
			$("#left4").css({"display":"none" });
			$("#left5").css({"display":"none" });
		}else if(t == 4){
			$("#left1").css({"display":"none"});
			$("#left2").css({"display":"none" });
			$("#left3").css({"display":"none" });
			$("#left4").css({"display":"block" });
			$("#left5").css({"display":"none" });
		}else if(t == 5){
			$("#left1").css({"display":"none"});
			$("#left2").css({"display":"none" });
			$("#left3").css({"display":"none" });
			$("#left4").css({"display":"none" });
			$("#left5").css({"display":"block" });
		}else{
			$("#left1").css({"display":"block"});
			$("#left2").css({"display":"none" });
			$("#left3").css({"display":"none" });
			$("#left4").css({"display":"none" });
			$("#left5").css({"display":"none" });
		}
    }

	$(".menu1").click(function() {
		$("#left1").css({"display":"block"});
		$("#left2").css({"display":"none" });
		$("#left3").css({"display":"none" });
		$("#left4").css({"display":"none" });
		$("#left5").css({"display":"none" });
	});

	$(".menu2").click(function() {
		$("#left1").css({"display":"none"});
		$("#left2").css({"display":"block" });
		$("#left3").css({"display":"none" });
		$("#left4").css({"display":"none" });
		$("#left5").css({"display":"none" });
	});

	$(".menu3").click(function() {
		$("#left1").css({"display":"none"});
		$("#left2").css({"display":"none" });
		$("#left3").css({"display":"block" });
		$("#left4").css({"display":"none" });
		$("#left5").css({"display":"none" });
	});

	$(".menu4").click(function() {
		$("#left1").css({"display":"none"});
		$("#left2").css({"display":"none" });
		$("#left3").css({"display":"none" });
		$("#left4").css({"display":"block" });
		$("#left5").css({"display":"none" });
	});

	$(".menu5").click(function() {
		$("#left1").css({"display":"none"});
		$("#left2").css({"display":"none" });
		$("#left3").css({"display":"none" });
		$("#left4").css({"display":"none" });
		$("#left5").css({"display":"block" });
	});

	$("#logout").css({
        "cursor":"pointer"
    });
});

function test(data){
	if(data == 2){
		sessionStorage.setItem('menu', '2');
	}else if(data == 3){
		sessionStorage.setItem('menu', '3');
	}else if(data == 4){
		sessionStorage.setItem('menu', '4');
	}else if(data == 5){
		sessionStorage.setItem('menu', '5');
	}else{
		sessionStorage.setItem('menu', '1');
	}
}

function handleError(err, url, line) {
	alert('Error : ' + err + '\nURL: ' + url + '\nLine:' + line);
	return true;
}

window.onerror = handleError;

$(function() {
    // 이벤트를 바인딩한다.
    bindMenu();
});

function bindMenu() {
	$(".decorator-search-button").each(function(index, element) {
	    $(this).bind("click", function(event) {
	        // 대출신청을 검색하는 화면으로 이동한다.
	        goView();
	        return false;
	    });
	});

	$("#searchText").keyup(function(event){
	    if(event.keyCode == 13){
	        $(".decorator-search-button").click();
	    }
	});
}

function goView() {
	var att = $("#goAdr").val();
    if (com.wow.fn.isEmpty($("#searchText").val())) {
        alert("화면이름을 입력하여 주십시오.");
        $("#searchText").focus();
        return;
    }
    var satt = att.split('/');
    sessionStorage.setItem('menuId', $('#leftmenu a[href*='+satt[3]+']').attr('id'));

    location.href = att;
}

$(function(){
    $( "#searchText" ).autocomplete({
		source : function( request, response ) {
             $.ajax({
                    type: 'post',
                    url: "/common/comCode/searchView",
                    dataType: "json",
                    data: { searchText : request.term },
                    success: function(data) {
                        var jsondata = JSON.stringify(data.data);
                        console.log("성공" + jsondata);
                        if (data.data.length == 0) {
                            data.data.push({
                            	menuTitle: "화면 없음",
                            	menuNm: "번호 없음",
                            	pgmPath: ""
                            });
                        }
                        response(
                            $.map(data.data, function(item) {
                                return {
                                	value: "[" + item.menuNm + "] " + item.menuTitle,
                                	label: "[" + item.menuNm + "] " + item.menuTitle,
                                	id: item.pgmPath
                                }
                            })
                        );
                        $('#goAdr').val(data.data[0].pgmPath);
                    },
                    error: function(data){
                    	console.log("에러"+data);
                    	var jsonResponse = JSON.parse(data.responseText);
                        //alert(jsonResponse.msg);
                    }
               });
        },
        //조회를 위한 최소글자수
        minLength: 2,
        select: function( event, ui ) {
            // 만약 검색리스트에서 선택하였을때 선택한 데이터에 의한 이벤트발생
        	$('#goAdr').val(ui.item.id);
        },
        focus : function(event,ui){
            return false;
      	}
    });
})

function logout() {
	sessionStorage.clear();
	window.location.href = "/index";
}

function isnull ( obj ) {
    return (typeof obj != "undefined" && obj != null && obj != "") ? false : true;
}

function dataSource_transport ( dst_id, dst_url, dst_params ) {
    var command = /([0-9a-zA-Z]+):([0-9a-zA-Z]+)=([0-9a-zA-Z_#@./-]+)/g;
    var ds_maps = {};
    if (isnull(dst_params)) {
    	var dst_params = {};
    }
    if ( command.test(dst_url) ) {
        if ( isnull(dst_url) ) {
        	dst_params.dataSourceCommand = "ds:list=empty";
        } else {
        	ds_maps = (typeof dst_url=='string') ? [dst_url] : dst_url; dst_params.dataSourceCommand = ds_maps.join(',');
        	dst_url = '<c:url value="/admin/dstl/DATASOURCE-TRANSPORT-LAYER.do" />';
        }
    } else {
    	dst_url = '<c:out value="${pageContext.request.contextPath}" />'+ dst_url +'.do';
    }

    jQuery.ajax({
        headers: {'Accept':'application/json', 'Content-Type':'application/json; charset=utf-8'},
        type: 'POST',
        url: dst_url,
        dataType: 'json',
        data: JSON.stringify(dst_params),
        success: function (result) {
        	try {
        		ds_callback_success(dst_id, result);
        	} catch(e) {}
        },
        error: function (result) {
        	try {
        		ds_callback_error(dst_id, result);
        	} catch(e) {}
        }
    });
}
</script>

<decorator:usePage id="thePage"/>
<% String selection = thePage.getProperty("meta.selection"); %>

<decorator:head />

	</head>
	<body>
  		<div id="wrap">
  			<!-- 헤더 -->
  			<div id="header">
				<div id="logo">
					<img src="/images/logo_view.png" alt="" width="150" height="50"/>
				</div>
				<div id="searchTag" class="searchtag">
					<input id="searchText" name="searchText" type="text" size="25" class="text"/>
					<input id="goAdr" name="goAdr" type="hidden" size="15" class="text"/>
					<a href="#" class="btn3_search decorator-search-button">
						<span></span>
					</a>
				</div>
				<div id="gnb_blank">
					<ul class="topmenu" id="topmenu" name="topmenu">
						<li class="topmenu menu1"><a href="#" onclick="test(4);"><img src="/images/menu/top_menu04.png" />회계</a></li>
						<!--
						<li class="topmenu menu2"><a href="#" onclick="test(1);"><img src="/images/menu/top_menu01.png" />회계</a></li>
						<li class="topmenu menu3"><a href="#" onclick="test(2);"><img src="/images/menu/top_menu02.png" />여신</a></li>
						<li class="topmenu menu4"><a href="#" onclick="test(3);"><img src="/images/menu/top_menu03.png" />ERP</a></li>
						-->
						<li class="topmenu menu5"><a href="#" onclick="test(5);"><img src="/images/menu/top_menu05.png" />관리</a></li>
						<li class="gnb_log" style="float:right;">
							<img class="circle" src="/images/profile.jpg" alt="" />
								${userLoginInfo.stafName} <span id="logout" onclick="logout()">로그아웃</span>
							<!--
							<img src="/images/setup.png" alt="" />
							 -->
						</li>
					</ul>
				</div>
			</div>

	  		<div id="leftmenu">
				<div id="left1">
					<div id="leftmenu_title">회계</div>
					<div>
						<ul class="leftmenu" id="leftmenu" name="leftmenu">
							<div class="submenu10">
								<p><a href="#">- 회계</a></p>
								<div class="panel10">
									<li><a id="101" href="/acc/accAcc/acco4050">일반전표</a></li>
									<!--
									<li><a id="102" href="/acc/accAcc/acco4050">대체전표</a></li>
									 -->
									<li><a id="103" href="/acc/accAcc/acco4051">일마감</a></li>
									<li><a id="104" href="/acc/accAcc/acco4010">전표총괄표</a></li>
									<li><a id="105" href="/acc/accAcc/acco4020">대차대조표</a></li>
									<li><a id="106" href="/acc/accAcc/acco4030">손익계산서</a></li>
									<!--
									<li><a id="105" href="/acc/accAcc/acco4040">일일거래명세서</a></li>
									<li><a id="106" href="/acc/accAcc/acco4050">대체전표</a></li>
									 -->
								</div>
							</div>
						</ul>
					</div>
				</div>

	  			<%--
	  			<div id="left2">
					<div id="leftmenu_title">고객</div>
					<div>
						<ul class="leftmenu" id="leftmenu" name="leftmenu">
							<div class="submenu1">
								<p><a href="#">- 대출자관리</a></p>
								<div class="panel1">
									<li><a id="1" href="/cus/cusLnd/cuso0010">고객정보(대출상세정보)</a></li>
									<li><a id="2" href="/cus/cusLnd/cuso0020">투자자정보(투자상세정보)</a></li>
								</div>
							</div>
							<div class="submenu2">
								<p><a href="#">- 투자자관리</a></p>
								<div class="panel2">
									<li><a id="3" href="/cus/cusInv/cuso0110">투자상품</a></li>
									<li><a id="4" href="/cus/cusInv/cuso0130">투자상품이율정보</a></li>
									<li><a id="5" href="/cus/cusInv/cuso0120">투자수수료율기본등록</a></li>
									<li><a id="6" href="/cus/cusInv/cuso4120">대출 투자자별 내역조회</a></li>
									<li><a id="7" href="/cus/cusInv/cuso4130">투자자 투자내역조회</a></li>
									<li><a id="8" href="/cus/cusInv/cuso4140">투자자전체조회</a></li>
								</div>
							</div>
						</ul>
					</div>
				</div>

				<div id="left3">
					<div id="leftmenu_title">여신</div>
					<div>
						<ul class="leftmenu" id="leftmenu" name="leftmenu">
							<div class="submenu3">
								<p><a href="#">- 대출신청</a></p>
								<div class="panel3">
									<li><a id="9" href="/loa/loaReq/loao4010">신청스케쥴조회</a></li>
									<li><a id="10" href="/loa/loaReq/loao0010">대출신청</a></li>

									<li><a id="11" href="/loa/loaCol/loar0003">대출신청서</a></li>
									<li><a id="12" href="/loa/loaCol/loar0002">대출약정서</a></li>
									<li><a href="/loa/loaCol/loar0004">대출계약서</a></li>

								</div>
							</div>
							<div class="submenu4">
								<p><a href="#">- 대출실행</a></p>
								<div class="panel4">
									<li><a id="13" href="/loa/loaExc/loao0110">대출실행</a></li>
								</div>
							</div>
							<div class="submenu5">
								<p><a href="#">- 담보관리</a></p>
								<div class="panel5">
									<li><a id="14" href="/loa/loaClt/loao0210">시가추정</a></li>
									<li><a id="15" href="/loa/loaClt/loao0230">담보원장</a></li>
								</div>
							</div>
							<div class="submenu6">
								<p><a href="#">- 대출관리</a></p>
								<div class="panel6">
									<li><a id="16" href="/loa/loaLnd/loao0310">스케쥴관리</a></li>
									<li><a id="17" href="/loa/loaLnd/loao0320">이율정보관리</a></li>
									<li><a id="18" href="/loa/loaLnd/loao0340">수수료관리</a></li>
									<li><a id="19" href="/loa/loaLnd/loao0350">취소</a></li>
									<li><a id="20" href="/loa/loaLnd/loao0360">종합채권관리(투자자)</a></li>
									<li><a id="21" href="/loa/loaLnd/loao0370">종합채권관리(대출자)</a></li>
									<li><a id="22" href="/loa/loaLnd/loao0380">투자상품원장</a></li>
									<li><a id="23" href="/loa/loaLnd/loao0390">상환예정스케쥴</a></li>

									<li><a id="24" href="/lam/lamLoa/lamo0110">연체관리</a></li>

								</div>
							</div>
							<div class="submenu7">
								<p><a href="#">- 대출상환</a></p>
								<div class="panel7">
									<li><a id="25" href="/loa/loaRpa/loao0410">대출상환</a></li>
								</div>
							</div>
							<div class="submenu8">
								<p><a href="#">- 조회</a></p>
								<div class="panel8">
									<li><a id="26" href="/loa/loaSch/loao0510">계좌상태조회</a></li>
									<li><a id="27" href="/loa/loaSch/loao0520">대출거래내역</a></li>
									<li><a id="28" href="/loa/loaSch/loao4510">이자계산내역</a></li>
									<li><a id="29" href="/loa/loaSch/como0110">상품코드관리</a></li>
								</div>
							</div>
						</ul>
					</div>
				</div>

				<div id="left4">
					<div id="leftmenu_title">ERP</div>
					<div>
						<ul class="leftmenu" id="leftmenu" name="leftmenu">
							<div class="submenu9">
								<p><a href="#">- CMS(예치금)</a></p>
								<div class="panel9">
									<li><a href="/erp/erpCms/erpo0040">예치금 입출금</a></li>
									<li><a href="/erp/erpCms/erpo4010">예치금거래내역</a></li>
									<li><a href="/erp/erpCms/erpo4020">예치금집계내역조회</a></li>
								</div>
							</div>
						</ul>
					</div>
				</div>
				 --%>


				<div id="left5">
					<div id="leftmenu_title">관리</div>
					<div>
						<ul class="leftmenu" id="leftmenu" name="leftmenu">
							<!--
							<div class="submenu11">
								<p><a href="#">- 사전관리</a></p>
								<div class="panel11">
									<li><a id="30" href="/lam/lamLoa/lamo0010">사전관리조회</a></li>
								</div>
							</div>
							<div class="submenu12">
								<p><a href="#">- 연체관리</a></p>
								<div class="panel12">
									<li><a id="31" href="/lam/lamLoa/lamo0110">연체관리</a></li>
								</div>
							</div>
							<div class="submenu13">
								<p><a href="#">- 여신 및 담보현황조회</a></p>
								<div class="panel13">
									<li><a id="32" href="/lam/lamLoa/lamo0210">여신 및 담보현황조회</a></li>
								</div>
							</div>
							<div class="submenu14">
								<p><a href="#">- DM</a></p>
								<div class="panel14">
									<li><a id="33" href="/lam/lamDvm/lamo0410">DM일괄발송대상추출</a></li>
									<li><a id="34" href="/lam/lamDvm/lamo0420">DM발송내역생성</a></li>
									<li><a id="35" href="/lam/lamDvm/lamo0430">DM발송의뢰</a></li>
									<li><a id="36" href="/lam/lamDvm/lamo0440">DM반송등록</a></li>
									<li><a id="37" href="/lam/lamDvm/lamo0450">DM제외고객관리</a></li>
									<li><a id="38" href="/lam/lamDvm/lamo0460">행안부주소정보일괄등록</a></li>
									<li><a id="39" href="/lam/lamDvm/lamo0470">DM수임통지자동생성</a></li>
								</div>
							</div>
							 -->
							<div class="submenu15">
								<p><a href="#">- 공통코드</a></p>
								<div class="panel15">
									<li><a id="1501" href="/lam/lamDvm/lamo0410">공통코드 관리</a></li>
									<li><a id="1502" href="/lam/lamDvm/lamo0410">공통코드 적용</a></li>
								</div>
							</div>
							<div class="submenu16">
								<p><a href="#">- 계정과목</a></p>
								<div class="panel16">
									<li><a id="1601" href="/lam/lamDvm/lamo0410">계정과목</a></li>
								</div>
							</div>
						</ul>
					</div>
				</div>

			</div>

			<div id="content">
				<decorator:body />
			</div>

			<div id="footer"></div>

  		</div>

		<script>
		window.onload = function(){
			$(":text").each(function(){

				$(this).focus(function(){
					var len = $(this).val().length * 2;
					this.setSelectionRange(len, len);
				})
			});
			$("."+mid).css({"display":"block"});
		}
		</script>
	</body>
</html>

</c:otherwise>
</c:choose>
