<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">

		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/">WOWPMD</a>
		</div>

		<div id="navbar" class="navbar-collapse collapse">

			<!-- 1 depth -->
			<ul class="nav navbar-nav" id="top-menu">

				<c:forEach var="item" items="${menuList}" varStatus="status">
					<li class="dropdown">
						<c:if test="${item.cnt > 0}">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${item.menuTitle} <span class="caret"></span></a>
							<c:import url="/navbar/secondMenu">
								<c:param name="level" value="2"/>
								<c:param name="upperId" value="${item.menuId}"/>
							</c:import>
						</c:if>
						<c:if test="${item.cnt eq 0}">
							<a href="${item.pgmPath}">${item.menuTitle}</a>
						</c:if>
					</li>
				</c:forEach>

			</ul>

			<!-- 검색 -->
	        <form class="navbar-form navbar-right">
	        	<input type="text" class="form-control" placeholder="검색">
	            <button type="button" class="btn btn-success">검색</button>
	        </form>

		</div>


	</div>

</nav>

