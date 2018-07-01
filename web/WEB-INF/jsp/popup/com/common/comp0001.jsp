<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body style="width:1024px">
	<form name="comp0001-search-form" id="comp0001-search-form" method="post" style="width:1024px">
		<input type="hidden" name="id" />
		<div class="kContent" style="width:1024px">
			<div class="" >
				<div class="content_header">
					<h1><span>비밀번호 변경</span></h1>
				</div>
				<br>
				<div class="" style="width:1024px">
					<div style="margin-left: 5px;">
						<p style="color: red;">▶ 현재  비밀번호를 입력한 후, 새로 사용할 비밀번호를 입력해 주세요.</p>
					</div>
					<div class="tableDetail">
						<table>
							<colgroup>
								<col width="15%" />
								<col width="85%" />
							</colgroup>
							<tbody class="">
								<tr>
									<%-- <th style="font-size:12px; vertical-align: middle;">고객구분</th>
									<td>
										<ui:select iD="custDvCd" name="custDvCd" clazz="search2" key="custDvCd" defaultValue="전체" value="${param.custDvCd}"/>
									</td> --%>
									<th>회원 아이디</th>
									<td>
										<input type="text" size="15" class="text" disabled value="${userLoginInfo.stafId}"/>
										<input type="hidden" size="15" class="text" id="stafId" name="stafId" value="${userLoginInfo.stafId}"/>
										<!-- <a href="#" class="btn3_search">
											<span></span>
										</a>
										<input type="text" size="30" class="text" disabled="disabled"/> -->
									</td>
								</tr>
								<tr>
									<th>현재 비밀번호</th>
									<td>
										<input type="password" size="15" id="passWd" name="passWd"/>
										<input type="hidden" size="15" id="pwTmp" name="pwTmp" value="${pwTmp}"/>
									</td>
								</tr>
								<tr>
									<th>새로운 비밀번호</th>
									<td>
										<input type="password" size="15" id="newPassWd" name="newPassWd"/>
									</td>
								</tr>
								<tr>
									<th>새로운 비밀번호 재확인</th>
									<td>
										<input type="password" size="15" id="newPassWdChk" name="newPassWdChk"/>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div align="center">
					<a href="#" class="btn comp0001-update-button">
						<span>비밀번호 변경하기</span>
					</a>
					<!-- <a href="#" class="btn cusp0001-search-button">
						<span>다음에 변경하기</span>
					</a> -->
				</div>
				<div style="margin-left: 5px;">
					<p style="color: red;">비밀번호 설정 시, 꼭 알아두세요!</p>
					<p>· 반드시 8~12자의 영문, 숫자, 특수문자 중 2가지 이상으로 조합하셔야 합니다.</p>
					<p>· 아이디 및 아이디를 포함한 문자/숫자는 비밀번호로 사용할 수 없습니다.</p>
					<p>· 동일한 문자열을 3번 이상 연속 배치할 수 없습니다. 예)aaa, 111</p>
					<p>· 생일, 주민등록번호 등 타인이 알아내기 쉬운 비밀번호는 사용을 자제해 주시기 바랍니다.</p>
					<p>· 주기성 문자 (abcd, 1234 등) 및 키보드상의 연속된 배열(asdf, qwerty 등)로 구성된 비밀번호는 사용을 자제해 주시기 바랍니다.</p>
					<p>· 한달에 한 번 정도 주기적으로 비밀번호 변경을 해주시기 바랍니다.</p>
				</div>
			</div>
		</div>
	</form>
	<script type="text/javascript">
	lstCttDt('${lstCttDt}');
	
	function lstCttDt(lstCttDt) {
		if (lstCttDt == "1") {
			alert("접속일자가 30일 지났습니다. 비밀번호를 변경해 주세요.");
			$("#passWd").focus();
		}
	}
	
	$(function() {
	    // 이벤트를 바인딩한다.
	    bindEvent();
	});

	
	/**
	 * 이벤트를 바인딩한다.
	 */
	function bindEvent() {
	    // 조회 버튼에 클릭 이벤트를 바인딩한다.
	    $(".comp0001-update-button").each(function(index, element) {
	        $(this).bind("click", function(event) {
	            // 신청원장을 검색한다.
	            comp0001Update();
	            return false;
	        });
	    });
	}
	
	function comp0001Update() {
	    if (com.nara.fn.isEmpty($("#passWd").val())) {
	        alert("현재 비밀번호를 입력하여 주십시오.");
	        $("#passWd").focus();
	        return;
	    }
	    
	    if (com.nara.fn.isEmpty($("#newPassWd").val())) {
	        alert("새로운 비밀번호를 입력하여 주십시오.");
	        $("#newPassWd").focus();
	        return;
	    }
	    
	    if (com.nara.fn.isEmpty($("#newPassWdChk").val())) {
	        alert("새로운 비밀번호 재확인을 입력하여 주십시오.");
	        $("#newPassWdChk").focus();
	        return;
	    }
	    
	    if ($("#passWd").val() != $("#pwTmp").val()) {
	    	alert("현재비밀번호를 확인하여 주십시오.");
	    	$("#passWd").focus();
	    	return;
	    }
	    
	    if ($("#passWd").val() == $("#newPassWd").val()) {
	    	alert("현재비밀번호와 새로운비밀번호가 동일합니다.");
	    	$("#newPassWd").focus();
	    	return;
	    }
	    
	    if ($("#newPassWd").val() != $("#newPassWdChk").val()) {
	    	alert("변경하실 비밀번호를 확인하여 주십시오.");
	    	$("#newPassWd").val("");
	    	$("#newPassWdChk").val("");
	    	$("#newPassWd").focus();
	    	return;
	    }
	    
	    if (!confirm("비밀번호를 변경하시겠습니까?")) {
	        return;
	    }
	    
	    $("#comp0001-search-form").attr("action", "<c:url value="/popup/com/common/comp0001Update" />").submit();
	}
	</script>
</body>