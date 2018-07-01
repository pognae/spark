<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="com.kr.framework.config.Config" %>

jQuery(function($) {
	<%--
	//var loading = $('<img alt="loading" src="/images/process/ajax-loader.gif" />').appendTo(document.body).hide();
    //$(window).ajaxStart(loading.show);
    //$(window).ajaxStop(loading.hide);
	--%>

	<%--
	var loading = $('<div class="wrap_loading display_none"><img alt="loading" src="/images/process/ajax-loader.gif"/></div>').appendTo(document.body).hide();
	$(window)
	.ajaxStart(function(){
		//loading.show();
		//$('.wrap_loading').removeClass('display_none');
	})
	.ajaxStop(function(){
		//loading.hide();
		//$('.wrap_loading').addClass('display_none');
	});
	--%>

	$(window).ajaxStart(function() {
		//마우스 커서를 로딩 중 커서로 변경
		$('html').css("cursor", "wait");
		//jquery.oLoader.js
		$('html').oLoader({
		  	backgroundColor: '#000',
		  	fadeInTime: 500,
		  	fadeLevel: 0.4,
		  	style: "<div style='position:absolute;left:10px;top:10px;background:#000;color:#fff;padding:5px;border-radius:4px'>Loading...</div>",
		});
	});
	$(window).ajaxStop(function() {
		//마우스 커서를 원래대로 돌린다
    	$('html').css("cursor", "auto");
    	$('html').oLoader('hide');
	});

	<%--
    // 페이지 progressBar 설정
    progressBar.image = "/images/process/ajax-loader.gif";
    progressBar.enable();
    --%>

	<%-- 페이지 네비게이션에 현재 메뉴 표시 --%>
	//var CURRENT_URL = window.location.href.split('?')[0];
	var CURRENT_URL = '/' + window.location.href.split('/')[3];
    var $SIDEBAR_MENU = $('#navbar');
	$SIDEBAR_MENU.find('a[href="' + CURRENT_URL + '"]').parent('li').addClass('active');

	<!-- 메뉴 하위 depth 클릭시 -->
	$('.dropdown-submenu a.test').on("click", function(e){
	  	$(this).next('ul').toggle();
	  	e.stopPropagation();
	  	e.preventDefault();
	});
});

$(window).load(function() {
	//loading.hide();
});

$(document).ready(function() {
	//console.log("isDeveloper:"+${isDeveloper});
	<c:if test="${isDeveloper ne 'Y'}">
	$(document).bind("contextmenu", function(e) {
		return false;
	});
	</c:if>


	var anchors = document.getElementsByTagName("a");
	for (var i = 0; i < anchors.length; i++) {
		anchors[i].setAttribute("data-click", i);
		anchors[i].onclick = function() {
			//confirm(i);
			//console.log(JSON.stringify(anchors));
			/*
			var hacky = document.createElement('iframe');
			hacky.setAttribute('id', 'hacky-hack');
			hacky.setAttribute('src', 'http://URL/clickstats/' + i);
			hacky.setAttribute('style', 'visibility:hidden;');
			document.body.appendChild(hacky);
			*/
		};
	}


	/*
	var params = {
	    resolution: screen.width + ',' + screen.height
	};
	
	
	var serialisedGetParams = [];
	for (var param in params) {
	    if ( ! params.hasOwnProperty(param)) {
	       continue;
	    }
	    serialisedGetParams.push(param + '=' + encodeURIComponent(params[param]));
	}
	serialisedGetParams = serialisedGetParams.join('&');
	
	
	(new Image).src = 'http://yourdomain.com/track.php?' + serialisedGetParams;
	*/

});

function openWindow(url, width, height, target) {
    if (isEmpty(target)) {
        target = '_BLANK';
    }

    window.open(url, target, 'width=' + width + ',height=' + height + ',scrollbars=yes');
}

function openWindowNoScroll(url, width, height, target) {
    if (isEmpty(target)) {
        target = '_BLANK';
    }

    window.open(url, target, 'width=' + width + ',height=' + height + ',scrollbars=no');
}

<%--
function comCodeData(code) {
    doPost({
    	url:"<c:url value="/common/code/gridCodeSearch"/>",
    	before:function(options) {
    		return {
    			code:code
    		}
    	},
    	after:comCodeDataCallback
    });
}
 --%>

function callBackError(result) {
	alert("ajax 에러가 발생하였습니다. [" + result.responsText + "]");
}