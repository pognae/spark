<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ page import="com.wowpmd.util.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://www.wowpmd.co.kr/taglib/ui" %>

<c:if test="${pageContext.request.requestURI != '/WEB-INF/jsp/script/system.js.jsp'}">

<%
//System.out.println("****************" + request.getRequestURI());
%>

<c:if test="${header['X-Requested-With'] != 'XMLHttpRequest'}">

<!--
<link rel="stylesheet" type="text/css" href="/css/common.css"/>
<link rel="stylesheet" type="text/css" href="/css/layout.css"/>
<link rel="stylesheet" type="text/css" href="/css/jquery-ui-1.10.4.custom.min.css"/>
<link rel="stylesheet" type="text/css" href="/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" href="/css/tabs.css"/>
<link rel="stylesheet" type="text/css" href="/css/grid.css" />
<link rel="stylesheet" type="text/css" href="/css/pace.css" />

<style type="text/css">
.ui-datepicker { font-size:12px; width:200px; }
.ui-datepicker select.ui-datepicker-year { width:40%; font-size:11px; }
.ui-datepicker select.ui-datepicker-month { width:40%; font-size:11px; }
.ui-datepicker-trigger {padding:0px 5px 0px 5px; vertical-align:middle;}
</style>
 -->

<script src="/js/jquery-1.11.0.min.js" 		 type="text/javascript"></script>
<!--
<script src="/js/i18n/grid.locale-kr.js" 	 type="text/javascript"></script>
<script src="/js/jquery.jqGrid.min.js" 		 type="text/javascript"></script>
<script src="/js/jquery-ui-1.10.4.custom.js" type="text/javascript"></script>
<script src="/js/jquery.ui.datepicker-ko.js" type="text/javascript"></script>
<script src="/js/jquery.ui.widget.js" 		 type="text/javascript"></script>
<script src="/js/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="/js/jquery.fileupload.js" 		 type="text/javascript"></script>
<script src="/js/jquery.fileDownload.js" 	 type="text/javascript"></script>
<script src="/js/jquery.dynatree.js"		 type="text/javascript"></script>
<script src="/js/html2canvas.js"			 type="text/javascript"></script>

<script src="/js/superfish.js"				 type="text/javascript"></script>
<script src="/js/supersubs.js"				 type="text/javascript"></script>
<script src="/js/hoverIntent.js"			 type="text/javascript"></script>
<script src="/js/jquery.simplemodal.1.4.4.min.js" type="text/javascript"></script>
<script src="/js/progressBar.js" 			 type="text/javascript"></script>
<script src="/js/pace.min.js" 				 type="text/javascript"></script>
<script src="/js/jquery.oLoader.js" 		 type="text/javascript"></script>

<script type="text/javascript" src="<c:url value="/js/jquery.form.min.js" />"></script>

<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.8.3/jquery.js"></script>
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/underscore.js/1.8.3/underscore.js"></script>
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/backbone.js/1.1.2/backbone.js"></script>
<script type="text/javascript" src="//rawgit.com/nhnent/tui.code-snippet/1.2.0/code-snippet.js"></script>
<script type="text/javascript" src="//rawgit.com/nhnent/tui.component.pagination/1.0.3/pagination.js"></script>
<script type="text/javascript" src="/js/grid.js"></script>
<script type="text/javascript" src="/js/vue.min.js"></script>
<script type="text/javascript" src="/js/prototype.js"></script>
<script type="text/javascript" src="/js/effects.js"></script>
<script type="text/javascript" src="/js/tracker.js"></script>
<script src="/js/ozPrint.js" 				 			   type="text/javascript"></script>
 -->

<script type="text/javascript" src="<c:url value="/js/jquery.meiomask.js" />"></script>

<script type="text/javascript" src="/script/system.js"></script>
<script type="text/javascript" src="<c:url value="/js/common.js?v="/><%=System.currentTimeMillis()%>"></script>
<script type="text/javascript" src="/js/com.js?v=<%=System.currentTimeMillis()%>"></script>
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

</c:if><%-- //header['X-Requested-With'] != 'XMLHttpRequest' --%>

</c:if><%-- //pageContext.request.requestURI != '/script/system.js.jsp' --%>