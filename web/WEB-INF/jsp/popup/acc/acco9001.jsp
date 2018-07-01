<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<title>:::WOWPMD:::</title>

<body style="width:1024px; background: #ffffff" onload="winResize();" onunload="winclose();">
	<div id="popup">
	<form name="frm" id="frm" method="post" style="width:1024px">
	<input type="hidden" id="page" name="page" value="<c:out value="${params.page}" default="1" />" />
	<input type="hidden" id="rows" name="rows" value="<c:out value="${params.rows}" default="10" />" />
	<input type="hidden" id="callback" name="callback" value="<c:out value="${params.callback}" default="popupCallback" />" />
		<div id="cont_title">
			<ul>
				<li>계정과목 조회</li>
			</ul>
		</div>
		<div id="cont_table1">
			<table class="bordered">
				<colgroup>
					<col width="10%" />
					<col width="" />
					<col width="10%" />
				</colgroup>
				<thead>
					<tr>
						<th></th>
						<th></th>
						<th></th>
						<!--
						<th class="borderless"></th>
						<th></th>
						 -->
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>계정과목</td>
						<td>
							<input type="text" size="30" class="input_text00" id="searchText" name="searchText" value="${params.searchText}" onkeypress="enterSearch()"/>
						</td>
						<td class="bordered_right">
							<a href="#" class="btn btn11 acco9001-search-button">
								<span>조회</span>
							</a>
						</td>
					</tr>
				</tbody>
			</table>
		</div>

		<br>

		<div id="cont_table2">
			<table class="bordered">
				<colgroup>
					<col width="10%" />
					<col width="45%" />
					<col width="45%" />
				</colgroup>
				<thead>
					<tr>
						<th>순번</th>
						<th>계정과목</th>
						<th>계정과목명</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${data}" var="item" varStatus="status">
						<tr class="lonmApc bordered_center">
		                    <td><c:out value="${item.rowNum}"/></td>
		                    <td class="acctCode"><c:out value="${item.acctCode}" /></td>
		                    <td class="acctAbbr bordered_left"><c:out value="${item.acctAbbr}" /></td>
						</tr>
					</c:forEach>
					<c:if test="${empty data}">
						<tr class="bordered_center">
						    <td colspan="3">조회된 내역이 없습니다.</td>
						</tr>
					</c:if>
				</tbody>
			</table>
			<div>
				<ui:pagination page="${page}" rows="${rows}" count="${count}" pages="${pages}" views="10" function="search" />
			</div>
		</div>
	</form>
	</div>
	<script type="text/javascript">
	$(function() {
	    // 컴포넌트를 초기화한다.
	    initComp();

	    bindMask();

	    // 이벤트를 바인딩한다.
	    bindEvent();
	});

	function bindMask() {
		// 고객관리번호 마스크처리
// 		$("#custMgntNo").setMask({ mask:"99-999999"});
// 		$(".custMgntNo").setMask({ mask:"99-999999"});
	}

	/**
	 * 컴포넌트를 초기화한다.
	 */
	function initComp() {
//         $("#custMgntNo").focus();
	}

	/**
	 * 이벤트를 바인딩한다.
	 */
	function bindEvent() {
	    // 조회 버튼에 클릭 이벤트를 바인딩한다.
	    $(".acco9001-search-button").each(function(index, element) {
	        $(this).bind("click", function(event) {
	            // 신청원장을 검색한다.
	            search();
	            return false;
	        });
	    });

	 	// 검색결과 행에 클릭 이벤트를 바인딩한다.
	    $(".lonmApc").each(function(index, element) {
	        $(this).css({
	            "cursor":"pointer"
	        });

	        $(this).bind("click", {
	        	acctCode:$(this).find(".acctCode").text(),
	        	acctAbbr:$(this).find(".acctAbbr").text()
	        }, function(event) {
	            // 신청원장을 설정한다.
	            acco9001Callback(event.data);
	            return false;
	        });
	    });
	}

	function enterSearch() {
		if (event.keyCode == 13) {
			search();
	    }
	    return false;
	}

	function search(page) {
	    if (page) {
	        $("#page").val(page);
	    }

	    $("#frm").attr("action", "<c:url value="/acc/accAcc/acco9001" />").submit();
	}

	function acco9001Callback(data) {
		if (opener) {
	        try {
	            var callback = eval("opener." + $("#callback").val());

	            if (callback) {
	                callback.call(window, data);
	            }
	        } finally {
	            window.close();
	        }
	    }
	}

	function winclose() {
		//$(opener.location).attr("href", "javascript:popupCallBack();");
		var callback = eval("opener." + $("#callback").val());
		callback.call(window, data);
	}

	window.onload = function() {
		winResize();
	}

	</script>
</body>