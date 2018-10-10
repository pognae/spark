<html>
<head>
<meta name="selection" content="slp1010"/>

<script type="text/javascript">
var params = {};

$(function() {
	initComp();
	// 마스크를 바인딩한다.
    bindMask();
    // 이벤트를 바인딩한다.
    bindEvent();
});

function initComp() {
    // 적용시작일자 텍스트필드에 날짜 마스크를 바인딩한다.
    $("#apclStrDt").val("${apclStrDt}");
    //$("#apclEndDt").val("${apclEndDt}");
   	$('#suplyPrice').val(0);//공급대가
   	$('#splpcAm').val(0);//공급가액
   	$('#vat').val(0);//부가세
}

function bindMask() {
    //$("#bascDt").setMask({ mask:"9999-19-39" });

    // 적용시작일자 텍스트필드에 날짜 선택기를 바인딩한다.
	$('#today').datetimepicker({
		format: 'YYYY-MM-DD'
	});
	//$('#today').val("${today}");

	/*
	$('#apclEndDtDatepicker').datetimepicker({
		format: 'YYYY-MM-DD'
	});
	 */
}

/**
 * 이벤트를 바인딩한다.
 */
function bindEvent() {
    $(".slp1010-search-btn").each(function(index, element) {
        $(this).bind("click", function(event) {
        	slp1010Search();
            return false;
        });
    });

    $(".cust-popup").each(function(index, element) {
        $(this).bind("click", function(event) {
        	popup1();
        	return false;
        });
    });

    $('#suplyPrice').focusout(function() {
    	calVat();
    });

    $(".btn-add").click(function(event) {
    	//submit();
		var suplyPrice = $('#suplyPrice').val();
		var acntMlsfc = $('#acntMlsfc').val();
		var acntSclas = $('#acntSclas').val();

		if(suplyPrice == 0) {
			alert("공급대가를 입력하세요");
			return false;
		}
		if(acntMlsfc == "") {
			alert("중분류를 선택하세요");
			return false;
		}
		if(acntSclas == "") {
			alert("소분류를 선택하세요");
			return false;
		}

    	$("#dealDate").val($("#today").val());

    });
}

function calVat() {
	var taxCls = $('#taxCls').val();//과세분류
	var suplyPrice = removeComma($('#suplyPrice').val());//공급대가
	var splpcAm = $('#splpcAm').val();//공급가액
	var vat = $('#vat').val();//부가세

	if(taxCls == '') {
		alert("과세분류를 선택하세요");
		return;
	}

	if(taxCls == 'Y') {
		//과세
		//splpcAm = Math.floor(suplyPrice / 11 * 10);
		vat = Math.floor(suplyPrice / 11);
		splpcAm = suplyPrice - vat;
		$('#suplyPrice').val(moneyComma(suplyPrice));
		$('#splpcAm').val(moneyComma(splpcAm));
		$('#vat').val(moneyComma(vat));
	} else {
		$('#suplyPrice').val(moneyComma(suplyPrice));
		$('#splpcAm').val(moneyComma(suplyPrice));
		$('#vat').val(0);
	}
}

function slp1010Search(page) {
    if (page) {
        $("#page").val(page);
    }

    $("#slp1010SearchForm").attr("action", "<c:url value="/slip/slp1010Search"/>").submit();
}

function popup1() {
	/*
	var popUrl = "/popup/bsc1022";	//팝업창에 출력될 페이지 URL
	var popOption = "width=938, height=510, resizable=no, scrollbars=no, status=no;";    //팝업창 옵션(optoin)
		window.open(popUrl,"",popOption);
	*/

    openWindow("/popup/bsc1022", "bsc1022", {
        width:938,
        height:510
    }, {
        callback:"slp1010Callback"
    });
}

function slp1010Callback(data) {
    $("#dealCust").val(data.bcncNo);
    $("#dealCustNm").val(data.bcncNm);
}

function fnOnChangeMClass(value) {

	$.ajax({
        type: 'post',
        url: "/slip/changeClass",
        dataType: "json",
        data: {
        	groupCodeId : value,
        	codeId : 'acntMlsfc'
        },
        success: function(data) {
            //var jsondata = JSON.stringify(data);
            //console.log("성공" + jsondata);
            $('#acntSclas').empty();
            $('#acntSclas').append($('<option></option>').text("선택").attr('value', ""));

            $('#acntMlsfc').empty();
            $('#acntMlsfc').append($('<option></option>').text("선택").attr('value', ""));
            $.each(data.acntMlsfc, function(i, data) {
    			$('#acntMlsfc').append($('<option></option>').text(data.codeName).attr('value', data.codeValue));
    	    });
        },
        error: function(data){
        	console.log("에러"+data);
        	var jsonResponse = JSON.parse(data.responseText);
            //alert(jsonResponse.msg);
        }
    });
}

function fnOnChangeSClass(value) {

	$.ajax({
        type: 'post',
        url: "/slip/changeClass",
        dataType: "json",
        data: {
        	groupCodeId : value,
        	codeId : 'acntSclas'
        },
        success: function(data) {
            //var jsondata = JSON.stringify(data);
            //console.log("성공" + jsondata);
            $('#acntSclas').empty();
            $('#acntSclas').append($('<option>').text("선택"));
            $.each(data.acntSclas, function(i, data) {
    			$('#acntSclas').append($('<option></option>').text(data.codeName).attr('value', data.codeValue));
    	    });
        },
        error: function(data){
        	console.log("에러"+data);
        	var jsonResponse = JSON.parse(data.responseText);
            //alert(jsonResponse.msg);
        }
    });
}

</script>

</head>

<body>


<div class="slp1010">
  	<div class="page-title">

    	<div class="row">
    		<div class="col-md-12">

    		<form class="form-inline form-label-left" name="slp1010SearchForm" id="slp1010SearchForm" method="post" role="form">
				<input type="hidden" id="page" name="page" value="<c:out value="${param.page}" default="1" />" />
				<input type="hidden" id="rows" name="rows" value="<c:out value="${param.rows}" default="5" />" />

				<div class="x_panel">
					<div class="x_title">
						<h2>계정과목 관리 </h2>
                    	<div class="clearfix"></div>
					</div>

					<div class="x_content">
						<div class="form-group form-group-sm">
                        	<label class="control-label" for="search">부과 일자 </label>
							<div class='input-group date' id='apclStrDtDatepicker'>
								<input type='text' name="today" id="today" class="form-control" value="${today}"/>
								<span class="input-group-addon">
									 <span class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
							<span>&nbsp;</span>
							<a href="#" class="btn btn-primary btn-sm pull-right slp1010-search-btn">검색</a>
                      	</div>

						<!--
						<div class="form-group form-group-sm">
							<a href="#" class="btn btn-primary btn-sm pull-right slp1010-add-btn">추가</a>
							<a href="#" class="btn btn-primary btn-sm pull-right slp1010-search-btn">검색</a>
						</div>
						 -->

		                <div class="ln_solid"></div>

						<div class="col-md-12" style="margin-left:0px">
						    <div class="row">

								<table class="table table-striped">
								  	<thead>
								    	<tr>
								      		<th scope="col">일자</th>
								      		<th scope="col">과세분류</th>
								      		<th scope="col">거래형태</th>
								      		<th scope="col">거래업체</th>
								      		<th scope="col">거래내역</th>
								      		<th scope="col">대분류</th>
								      		<th scope="col">중분류</th>
								      		<th scope="col">소분류</th>
								      		<th scope="col">공급대가</th>
								      		<th scope="col">공급가액</th>
								      		<th scope="col">부가세</th>
								    	</tr>
								  	</thead>
								  	<tbody>
								  		<c:forEach var="item" items="${data}" varStatus="status">
									    	<tr>
									      		<th scope="row"><c:out value="${item.dealDate}"/></th>
									      		<td><c:out value="${item.taxCls}"/></td>
									      		<td><c:out value="${item.dealType}"/></td>
									      		<td><c:out value="${item.dealCust}"/></td>
									      		<td><c:out value="${item.dealDtls}"/></td>
									      		<td><c:out value="${item.acntLclas}"/></td>
									      		<td><c:out value="${item.acntMlsfc}"/></td>
									      		<td><c:out value="${item.acntSclas}"/></td>
									      		<td class="text-right"><fmt:formatNumber value="${item.suplyPrice}" pattern="#,###" /></td>
									      		<td class="text-right"><fmt:formatNumber value="${item.splpcAm}" pattern="#,###" /></td>
									      		<td class="text-right"><fmt:formatNumber value="${item.vat}" pattern="#,###" /></td>
									    	</tr>
								  		</c:forEach>
										<c:if test="${empty data}">
					                        <tr>
					                            <td colspan="12" style="text-align:center;">검색된 자료가 없습니다.</td>
					                        </tr>
				                        </c:if>
								  	</tbody>
								</table>

						    </div>
						</div>

					</div>
				</div>
				</form>

				<div class="x_panel">
					<div class="x_content">

				        <form action="/slip/slp1010/insert" method="post">
							<table class="table table-striped">
							    <tbody>
						            <tr>
						                <td><span class="pull-right">과세분류</span></td>
						                <td>
						                	<ui:select iD="taxCls" name="taxCls" value="${taxCls}" clazz="form-control input-sm" key="taxCls" defaultValue="선택" required="required"/>
						                </td>
						                <td><span class="pull-right">분류</span></td>
						                <td><input type="text" placeholder="시트분류" name="cntCls" id="cntCls" class="form-control input-sm"/></td>
						                <td><span class="pull-right">거래형태</span></td>
						                <td><input type="text" placeholder="" name="dealType" id="dealType" class="form-control input-sm"/></td>
						                <td><span class="pull-right">거래업체</span></td>
						                <td>
							                <div class="input-group">
												<input type="text" placeholder="" name="dealCustNm" id="dealCustNm" class="form-control input-sm" readonly="true"/>
												<input type="hidden" name="dealCust" id="dealCust"/>
												<span class="input-group-btn">
													<button type="button" class="btn btn-primary input-sm cust-popup"><i class="fa fa-search"></i></button>
												</span>
											</div>
						                </td>
						            </tr>
						            <tr>
						                <td><span class="pull-right">거래내역</span></td>
						                <td><input type="text" placeholder="" name="dealDtls" id="dealDtls" class="form-control input-sm"/></td>
						                <td><span class="pull-right">대분류</span></td>
						                <td>
											<ui:select iD="acntLclas" name="acntLclas" value="${acntLclas}" clazz="form-control input-sm" key="acntLclas" defaultValue="선택" onchange="fnOnChangeMClass(this.value)" required="required"/>
						                </td>
						                <td><span class="pull-right">중분류</span></td>
						                <td>
						                	<select id="acntMlsfc" name="acntMlsfc" class="form-control input-sm" required="required" onchange="fnOnChangeSClass(this.value)">
												<option value="">선택</option>
						                	</select>
						                </td>
						                <td><span class="pull-right">소분류</span></td>
						                <td>
						                	<select id="acntSclas" name="acntSclas" class="form-control input-sm" required="required">
												<option value="">선택</option>
						                	</select>
						                </td>
						            </tr>
						            <tr>
						                <td><span class="pull-right">공급대가</span></td>
						                <td><input type="text" placeholder="" name="suplyPrice" id="suplyPrice" class="form-control input-sm text-right" required="required" min="0" data-bind="value:replyNumber" onKeyUp="return isNumber(event)"/></td>
						                <td><span class="pull-right">공급가액</span></td>
						                <td><input type="text" placeholder="" name="splpcAm" id="splpcAm" class="form-control input-sm text-right" required="required" readonly="true"/></td>
						                <td><span class="pull-right">부가세</span></td>
						                <td><input type="text" placeholder="" name="vat" id="vat" class="form-control input-sm text-right" value="" required="required" readonly="true"/></td>
						                <td colspan="2">
		                          			<button type="submit" class="btn btn-success btn-add pull-right">등록</button>
		                          			<button type="reset" class="btn btn-primary btn-cancel pull-right">취소</button>
		                          			<input type='hidden' name="dealDate" id="dealDate" value=""/>
						                </td>
									</tr>
							    </tbody>
							</table>
				        </form>

					</div>
				</div>
    		</div>
    	</div>
    </div>
</div>

</body>
</html>