<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:if test="${pageInfo.totalPage > 0}">
    <c:if test="${pageInfo.totalPage != 1}">
        <c:if test="${pageInfo.startPage > 1}">
            <a href="javascript:${pageInfo.scriptName}('${pageInfo.previousScalePage}')" class="prev"><img src="${imageServer}/images/admin/btn_page_prev.gif" alt="이전" /></a>
        </c:if>
    </c:if>
    <c:forEach items="${pageInfo.pages}" var="page" varStatus="status">
        <c:if test="${pageInfo.currentPage != page}">
            <a href="javascript:${pageInfo.scriptName}('${page}')">${page}</a>
        </c:if>
        <c:if test="${pageInfo.currentPage == page}">
            <a href="javascript://" class="selected">${page}</a>
        </c:if>
    </c:forEach>
    <c:if test="${pageInfo.nextScalePage <= pageInfo.totalPage}">
        <a href="javascript:${pageInfo.scriptName}('${pageInfo.nextScalePage}')" class="next"><img src="${imageServer}/images/admin/btn_page_next.gif" alt="다음" /></a>
    </c:if>
</c:if>