<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:if test="${pageInfo.totalPage > 0}">
    <a href="${pageInfo.url}${pageInfo.separator}${pageInfo.pageParam}=1${pageInfo.paramString}" class="first"><img src="${imageServer}/images/admin/btn_paginate_01.gif" alt="처음" /></a>
    <c:if test="${pageInfo.startPage > 1}">
    <a href="${pageInfo.url}${pageInfo.separator}${pageInfo.pageParam}=${pageInfo.previousScalePage}${pageInfo.paramString}" class="prev"><img src="${imageServer}/images/admin/btn_paginate_02.gif" alt="이전" /></a>
    </c:if>
    <c:if test="${!empty pageInfo.pages}">
        <span>
    </c:if>
    <c:forEach items="${pageInfo.pages}" var="page" varStatus="status">
        <c:if test="${pageInfo.currentPage != page}">
            <a href="${pageInfo.url}${pageInfo.separator}${pageInfo.pageParam}=${page}${pageInfo.paramString}">${page}</a>
        </c:if>
        <c:if test="${pageInfo.currentPage == page}">
            <strong>${page}</strong>
        </c:if>
    </c:forEach>
    <c:if test="${!empty pageInfo.pages}">
        </span>
    </c:if>
    <c:if test="${pageInfo.nextScalePage <= pageInfo.totalPage}">
        <a href="${pageInfo.url}${pageInfo.separator}${pageInfo.pageParam}=${pageInfo.nextScalePage}${pageInfo.paramString}" class="next"><img src="${imageServer}/images/admin/btn_paginate_03.gif" alt="다음" /></a>
    </c:if>
</c:if>
