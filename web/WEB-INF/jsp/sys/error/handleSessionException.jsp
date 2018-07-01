<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ --%>
<%-- @(#)handleSessionException.jsp 1.0 2016/04/12                          --%>
<%--                                                                        --%>
<%-- Copyright (c) 2016 WOWPMD Co., Inc. All rights reserved.          --%>
<%-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ --%>

<%-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ --%>
<%-- 세션 오류를 처리하는 화면이다.                                         --%>
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
<jsp:forward page="/index" />
</c:otherwise>
</c:choose>