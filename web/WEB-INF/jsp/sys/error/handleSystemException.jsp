<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ --%>
<%-- @(#)handleSystemException.jsp 1.0 2016/04/12                           --%>
<%--                                                                        --%>
<%-- Copyright (c) 2016 WOWPMD Co., Inc. All rights reserved.          --%>
<%-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ --%>

<%-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ --%>
<%-- 시스템 오류를 처리하는 화면이다.                                       --%>
<%--                                                                        --%>
<%-- @author 이동엽                                                         --%>
<%-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ --%>
<c:choose>
<c:when test="${header['X-Requested-With'] == 'XMLHttpRequest'}">
{
    "error":{
        "code":"",
        "message":"시스템 오류가 발생하였습니다."
    }
}
</c:when>

<c:otherwise>
<html>
<head>
<script type="text/javascript">
$(function() {
    //alert("시스템 오류가 발생하였습니다.");

    if (window.opener) {
        // window.close();
    } else {
        if (window.parent) {
            // Nothing to do.
        } else {
            // Nothing to do.
        }
    }
});
</script>
</head>
<body>
<div>
    <div>시스템 오류가 발생하였습니다.</div>
    <br />
    <div><c:out value="${exception.message}" /></div>
    <br />
    <c:forEach items="${exception.stackTrace}" var="item">
    <div>at <c:out value="${item}" /></div>
    </c:forEach>
</div>
</body>
</html>
</c:otherwise>

</c:choose>