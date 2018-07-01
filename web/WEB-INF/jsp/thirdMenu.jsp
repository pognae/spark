<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


	<li class="dropdown-submenu">
		<c:forEach var="item" items="${dropDownMenuList}" varStatus="status">
			<c:if test="${item.cnt > 0}">
				<a href="#" class="test">${item.menuTitle} <span class="caret"></span></a>
				<%--
				<c:import url="/dropDownMenu">
					<c:param name="level" value="${item.ll}"/>
					<c:param name="upperId" value="${item.menuId}"/>
				</c:import>
				 --%>
			</c:if>

			<c:if test="${item.cnt eq 0}">
				<a href="${item.pgmPath}">${item.menuTitle}</a>
			</c:if>
		</c:forEach>
	</li>
