<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ --%>
<%-- @(#)alertAndRedirect.jsp 1.0 2016/04/12                                --%>
<%--                                                                        --%>
<%-- Copyright (c) 2016 WOWPMD Co., Inc. All rights reserved.          --%>
<%-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ --%>

<%-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ --%>
<%-- 메시지를 보여주고 화면을 이동하는 화면이다.                            --%>
<%--                                                                        --%>
<%-- @author 이동엽                                                         --%>
<%-- @version 1.0 2016/04/12                                                --%>
<%-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ --%>
<html>
<head>
<script type="text/javascript">
$(function() {
    alert("<c:out value="${requestScope.message}" />");
    
    $("#redirect-form").attr("action", "<c:url value="${requestScope.action}" />").submit();
});
</script>
</head>
<body>
<div style="display:none;">
    <form id="redirect-form" name="redirect-form" method="post">
        <c:forEach items="${requestScope.parameters}" var="parameter">
        <c:forEach items="${parameter.value}" var="value">
        <textarea name="<c:out value="${parameter.key}" />"><c:out value="${value}" /></textarea>
        </c:forEach>
        </c:forEach>
    </form>
</div>
</body>
</html>