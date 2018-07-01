<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<title>:::WOWPMD:::</title>

<body style="width:1024px; background: #ffffff" onload="winResize();" onunload="winclose();">
	<div id="popup">
	<form name="frm" id="frm" method="post" style="width:1024px">
	<input type="hidden" id="page" name="page" value="<c:out value="${params.page}" default="1" />" />
	<input type="hidden" id="rows" name="rows" value="<c:out value="${params.rows}" default="5" />" />
	<input type="hidden" id="callback" name="callback" value="<c:out value="${params.callback}" default="popupCallback" />" />
		<div id="cont_title">
			<ul>
				<li>마감 조회</li>
			</ul>
		</div>
		<div id="cont_table1">
			<table class="bordered">
				<colgroup>
					<col width="10%" />
					<col width="" />
					<col width="40%" />
				</colgroup>
				<thead>
					<tr>
						<th></th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>조회일자</td>
						<td>
							<input type="text" name="regDtm" id="regDtm" size="15" class="text" value="${regDtm}" style="vertical-align:top"/>
						</td>
						<td class="bordered_right">
							<a href="#" class="btn btn11 acco9002-search-button">
								<span>조회</span>
							</a>
							<a href="#" class="btn btn13 acco9002-search-button">
								<span>마감</span>
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
						<th>일자</th>
						<th>미마감건수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${data}" var="item" varStatus="status">
						<tr class="lonmApc bordered_center">
		                    <td><c:out value="${item.rowNum}"/></td>
		                    <td class="acctCode"><c:out value="${item.regDtm}" /></td>
		                    <td class="acctAbbr bordered_right"><c:out value="${item.cnt}" /></td>
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

		$('#regDtm').datepicker({
			  showon: 'button'
				//,inline: true
	     	, buttonText: 'Show Date'
	     	, buttonImageOnly: true
	     	, buttonImage:'/images/btn_icon_calendar.gif'
			, showButtonPanel: true
			});
		$('#regDtm').val(
			$.datepicker.formatDate('yy-mm-dd', new Date())
		);
	});

	function bindMask() {

	}

	/**
	 * 컴포넌트를 초기화한다.
	 */
	function initComp() {

	}

	/**
	 * 이벤트를 바인딩한다.
	 */
	function bindEvent() {
	    // 조회 버튼에 클릭 이벤트를 바인딩한다.
	    $(".acco9002-search-button").each(function(index, element) {
	        $(this).bind("click", function(event) {
	            // 신청원장을 검색한다.
	            search();
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

	    $("#frm").attr("action", "<c:url value="/acc/accAcc/acco9002" />").submit();
	}

	function acco9002Callback(data) {
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
		var callback = eval("opener." + $("#callback").val());
		callback.call(window, data);
	}

	window.onload = function() {
		winResize();
	}

	</script>
</body>