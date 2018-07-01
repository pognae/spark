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


	</c:if><%-- //header['X-Requested-With'] != 'XMLHttpRequest' --%>

</c:if><%-- //pageContext.request.requestURI != '/script/system.js.jsp' --%>