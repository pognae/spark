<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


	<ul class="dropdown-menu">
		<c:forEach var="item" items="${dropDownMenuList}" varStatus="status">
			<c:if test="${item.cnt > 0}">
				<a href="#" class="">${item.menuTitle} <span class="caret"></span></a>
				<c:import url="/navbar/thirdMenu">
					<c:param name="level" value="3"/>
					<c:param name="upperSubId">${item.menuId}</c:param>
				</c:import>
			</c:if>

			<c:if test="${item.cnt eq 0}">
				<a href="${item.pgmPath}">${item.menuTitle}</a>
			</c:if>
		</c:forEach>
	</ul>
