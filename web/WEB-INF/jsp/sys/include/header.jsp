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

<c:if test="${header['X-Requested-With'] != 'XMLHttpRequest'}">

<%--
<script src="/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/script/system.js"></script>

<script type="text/javascript" src="<c:url value="/js/jquery.meiomask.js" />"></script>
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
--%>

</c:if><%-- //header['X-Requested-With'] != 'XMLHttpRequest' --%>

</c:if><%-- //pageContext.request.requestURI != '/script/system.js.jsp' --%>