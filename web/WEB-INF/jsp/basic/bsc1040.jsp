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
    $("#applcYm").val("${applcYm}");
    //$("#apclEndDt").val("${apclEndDt}");
   	//$('#suplyPrice').val(0);//공급대가
   	//$('#splpcAm').val(0);//공급가액
   	//$('#vat').val(0);//부가세
}

function bindMask() {
    // 적용시작일자 텍스트필드에 날짜 선택기를 바인딩한다.
	$('#applcYm').datetimepicker({
		format: 'YYYY-MM'
	});
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
    	/*
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
 		*/
 		
 		result = txtFieldCheck() == true ? true : false;
 		if(!result) {
 			return false;
 		}
 		
 		$("#bsc1040InsertForm").attr("action", "<c:url value="/basic/bsc1040/insert"/>").submit();
 		return false;
    });

    $(".btn-search").click(function(event) {
 		$("#slp1010SearchForm").attr("action", "<c:url value="/basic/bsc1040Search"/>").submit();
 		return false;
    });

    $(".btn-create").click(function(event) {
    	if(confirm("관리비를 확정하시겠습니까?")) {
    		onChargeCostCreate();
    	}
    	
 		return false;
    });

    $(".btn-copy").click(function(event) {
    	alert("작업중....")
    	
 		return false;
    });
}

function txtFieldCheck(){
	// form안의 모든 text type 조회
	var result = true;
	var frm = $("#bsc1040InsertForm :input").not(":input[readonly]");

	frm.each(function(idx, ele) {
		if("" == $(ele).attr("value")) {
			alert($(ele).attr(id));
			alert($(ele).attr("title") + "을(를) 입력하세요");
			$(ele).focus();
			result = false;
			return false;
		};
	});
	return result;
}


function showAlert(ele_id, label_txt){
	//alert(label_txt + " is null");
	// 해당 id에 focus.
	$("#" + ele_id).focus();
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

function onChargeCostCreate() {

	$.ajax({
        type: 'post',
        url: "/basic/bsc1040/create",
        dataType: "json",
        data: {
        	applcYm : $("#applcYm").val()
        },
        success: function(data) {
			if(data.data == 'success') {
				alert("관리비가 생성되었습니다.");
			} else {
				alert("관리비가 생성되지 않았습니다. 관리자에게 문의하세요.");
			}
        },
        error: function(data){
        	console.log("에러"+data);
        	var jsonResponse = JSON.parse(data.responseText);
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
							<h2>항목별 발생금액 관리 </h2>
	                    	<div class="clearfix"></div>
						</div>

						<div class="x_content">
							<div class="form-group form-group-sm">
	                        	<label class="control-label" for="search">적용년월 </label>
								<div class='input-group date' id='applcYmDatepicker'>
									<input type='text' name="applcYm" id="applcYm" class="form-control" value="${applcYm}"/>
									<span class="input-group-addon">
										 <span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
								<span>&nbsp;</span>
								<a href="#" class="btn btn-success btn-sm pull-right btn-create">관리비 생성</a>
								<a href="#" class="btn btn-success btn-sm pull-right btn-copy">전월금액 복사</a>
								<a href="#" class="btn btn-primary btn-sm pull-right btn-search">조회</a>
	                      	</div>

							<!--
			                <div class="ln_solid"></div>
							<div class="col-md-12" style="margin-left:0px">
							    <div class="row">

							    </div>
							</div>
							 -->
						</div>
					</div>
				</form>

				<div class="x_panel">
					<div class="x_content">

				        <form class="form-inline form-label-left" name="bsc1040InsertForm" id="bsc1040InsertForm" method="post" role="form">
							<input type="hidden" id="useYn" name="useYn" value="<c:out value="${data.useYn}" default="Y" />" />
							<input type="hidden" id="applcYm" name="applcYm" value="<c:out value="${applcYm}" />" />
							<table class="table table-striped">
							    <tbody>
						            <tr>
						                <td><span class="pull-right">일반관리비</span></td>
						                <td><input type="text" title="일반관리비" placeholder="" name="gnrlManageCst" id="gnrlManageCst" value="${data.gnrlManageCst}" class="form-control input-sm text-right" required="required" min="0" data-bind="value:replyNumber" onKeyUp="return isNumberDot(event)"/></td>
						                <td><span class="pull-right">전산처리비</span></td>
						                <td><input type="text" title="전산처리비"  placeholder="" name="cmptProcessCst" id="cmptProcessCst" value="${data.cmptProcessCst}" class="form-control input-sm text-right" required="required" min="0" data-bind="value:replyNumber" onKeyUp="return isNumberDot(event)"/></td>
						                <td><span class="pull-right">입주자대표회의</span></td>
						                <td><input type="text" title="입주자대표회의" placeholder="" name="mvnManReprsntMtgCst" id="mvnManReprsntMtgCst" value="${data.mvnManReprsntMtgCst}" class="form-control input-sm text-right" required="required" min="0" data-bind="value:replyNumber" onKeyUp="return isNumberDot(event)"/></td>
						                <td><span class="pull-right">청소비</span></td>
						                <td><input type="text" title="청소비" placeholder="" name="clnCst" id="clnCst" value="${data.clnCst}" class="form-control input-sm text-right" required="required" min="0" data-bind="value:replyNumber" onKeyUp="return isNumberDot(event)"/></td>
						            </tr>
						            <tr>
						                <td><span class="pull-right">소방관리대행료</span></td>
						                <td><input type="text" title="소방관리대행료" placeholder="" name="fgtManageVrscCst" id="fgtManageVrscCst" value="${data.fgtManageVrscCst}" class="form-control input-sm text-right" required="required" min="0" data-bind="value:replyNumber" onKeyUp="return isNumberDot(event)"/></td>
						                <td><span class="pull-right">승강기유지보수비</span></td>
						                <td><input type="text" title="승강기유지보수비" placeholder="" name="elvtrMntnceCst" id="elvtrMntnceCst" value="${data.elvtrMntnceCst}" class="form-control input-sm text-right" required="required" min="0" data-bind="value:replyNumber" onKeyUp="return isNumberDot(event)"/></td>
						                <td><span class="pull-right">전기안전대행료</span></td>
						                <td><input type="text" title="전기안전대행료" placeholder="" name="elctySafeVrscCst" id="elctySafeVrscCst" value="${data.elctySafeVrscCst}" class="form-control input-sm text-right" required="required" min="0" data-bind="value:replyNumber" onKeyUp="return isNumberDot(event)"/></td>
						                <td><span class="pull-right">공동전기료</span></td>
						                <td><input type="text" title="공동전기료" placeholder="" name="copertnElctyCst" id="copertnElctyCst" value="${data.copertnElctyCst}" class="form-control input-sm text-right" required="required" min="0" data-bind="value:replyNumber" onKeyUp="return isNumberDot(event)"/></td>
						            </tr>
						            <tr>
						                <td><span class="pull-right">공동수도료</span></td>
						                <td><input type="text" title="공동수도료" placeholder="" name="copertnCptlCst" id="copertnCptlCst" value="${data.copertnCptlCst}" class="form-control input-sm text-right" required="required" min="0" data-bind="value:replyNumber" onKeyUp="return isNumberDot(event)"/></td>
						                <td><span class="pull-right">예비비</span></td>
						                <td><input type="text" title="예비비" placeholder="자동계산" name="rsvfndCst" id="rsvfndCst" value="${data.rsvfndCst}" class="form-control input-sm text-right" min="0" data-bind="value:replyNumber" onKeyUp="return isNumberDot(event)" readonly/></td>
						                <td><span class="pull-right">화재보험료</span></td>
						                <td><input type="text" title="화재보험료" placeholder="" name="fireIrncfCst" id="fireIrncfCst" value="${data.fireIrncfCst}" class="form-control input-sm text-right" required="required" min="0" data-bind="value:replyNumber" onKeyUp="return isNumberDot(event)"/></td>
						                <td><span class="pull-right">장기수선충당금</span></td>
						                <td><input type="text" title="장기수선충당금" placeholder="" name="lngtrRpairsRsvmneyCst" id="lngtrRpairsRsvmneyCst" value="${data.lngtrRpairsRsvmneyCst}" class="form-control input-sm text-right" required="required" min="0" data-bind="value:replyNumber" onKeyUp="return isNumberDot(event)"/></td>
						            </tr>
						            <tr>
										<td><span class="pull-right">합계</span></td>
						                <td><input type="text" placeholder="" name="smAmount" id="smAmount" value="${data.smAmount}" class="form-control input-sm text-right" required="required" min="0" data-bind="value:replyNumber" onKeyUp="return isNumberDot(event)"/></td>
						                <td colspan="6">
		                          			<a href="#" class="btn btn-success btn-sm pull-right btn-add">등록</a>
		                          			<a href="#" class="btn btn-cancel btn-sm pull-right btn-cancel">취소</a>
						                </td>
									</tr>
							    </tbody>
							</table>
				        </form>

					</div>
				</div>

				<div class="x_panel">
					<div class="x_content">
						<h2>항목별 단위 부과금액 </h2>
                    	<div class="clearfix"></div>
						<table class="table table-striped">
						    <tbody>
					            <tr>
					                <td><span class="pull-right">일반관리비</span></td>
					                <td><input type="text" value="${unit.gnrlManageCst}" class="form-control input-sm text-right" min="0" readonly/></td>
					                <td><span class="pull-right">전산처리비</span></td>
					                <td><input type="text" value="${unit.cmptProcessCst}" class="form-control input-sm text-right" min="0" readonly/></td>
					                <td><span class="pull-right">입주자대표회의</span></td>
					                <td><input type="text" value="${unit.mvnManReprsntMtgCst}" class="form-control input-sm text-right" min="0" readonly/></td>
					                <td><span class="pull-right">청소비</span></td>
					                <td><input type="text" value="${unit.clnCst}"  class="form-control input-sm text-right" min="0" readonly/></td>
					            </tr>
					            <tr>
					                <td><span class="pull-right">소방관리대행료</span></td>
					                <td><input type="text" value="${unit.fgtManageVrscCst}" class="form-control input-sm text-right" min="0" readonly/></td>
					                <td><span class="pull-right">승강기유지보수비</span></td>
					                <td><input type="text" value="${unit.elvtrMntnceCst}" class="form-control input-sm text-right" min="0" readonly/></td>
					                <td><span class="pull-right">전기안전대행료</span></td>
					                <td><input type="text" value="${unit.elctySafeVrscCst}" class="form-control input-sm text-right" min="0" readonly/></td>
					                <td><span class="pull-right">공동전기료</span></td>
					                <td><input type="text" value="${unit.copertnElctyCst}" class="form-control input-sm text-right" min="0" readonly/></td>
					            </tr>
					            <tr>
					                <td><span class="pull-right">공동수도료</span></td>
					                <td><input type="text" value="${unit.copertnCptlCst}" class="form-control input-sm text-right" min="0" readonly/></td>
					                <td><span class="pull-right">예비비</span></td>
					                <td><input type="text" value="${unit.rsvfndCst}" class="form-control input-sm text-right" min="0" readonly/></td>
					                <td><span class="pull-right">화재보험료</span></td>
					                <td><input type="text"  value="${unit.fireIrncfCst}" class="form-control input-sm text-right" min="0" readonly/></td>
					                <td><span class="pull-right">장기수선충당금</span></td>
					                <td><input type="text" value="${unit.lngtrRpairsRsvmneyCst}" class="form-control input-sm text-right" min="0" readonly/></td>
					            </tr>
						    </tbody>
						</table>

					</div>
				</div>

    		</div>
    	</div>
    </div>
</div>

</body>
</html>