<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ --%>
<%-- @(#)handleServiceException.jsp 1.0 2016/04/12                          --%>
<%--                                                                        --%>
<%-- Copyright (c) 2016 WOWPMD Co., Inc. All rights reserved.          --%>
<%-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ --%>

<%-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ --%>
<%-- 서비스 오류를 처리하는 화면이다.                                       --%>
<%--                                                                        --%>
<%-- @author 이동엽                                                         --%>
<%-- @version 1.0 2016/04/12                                                --%>
<%-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ --%>
<c:choose>
<c:when test="${header['X-Requested-With'] == 'XMLHttpRequest'}">
{
    "error":{
        "code":"<c:out value="${exception.code}" />",
        "message":"<c:out value="${exception.message}" />"
    }
}
</c:when>
<c:otherwise>
<html>
<head>
<script type="text/javascript">
$(function() {
    //alert("<c:out value="${exception.message}" />");

    if (window.opener) {
        // window.close();
    }
    else {
        if (window.parent) {
            // Nothing to do.
        }
        else {
            // Nothing to do.
        }
    }
});
</script>
</head>
<body>
<div>
    <div><c:out value="${exception.message}" /></div>
</div>
</body>
</html>
</c:otherwise>
</c:choose>