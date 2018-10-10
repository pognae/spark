<html>
<head>
<meta name="selection" content="rsd1010"/>

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
    //$("#apclStrDt").val("${apclStrDt}");
    //$("#apclEndDt").val("${apclEndDt}");
	$("#useYn").val("Y");
	$("#resideBeginDt").val("${resideBeginDt}");
}

function bindMask() {
	$('#resideBeginDtDatepicker').datetimepicker({
		format: 'YYYY-MM-DD'
	});
}

/**
 * 이벤트를 바인딩한다.
 */
function bindEvent() {
    $(".rsd1010-search-btn").each(function(index, element) {
        $(this).bind("click", function(event) {
        	rsd1010Search();
            return false;
        });
    });

    $(".rsd1010-add-btn").click(function(event) {
    	//popup1();
    	rsd1011Search();
    });
}

/*
function popup1(){
	var popUrl = "/popup/rsd1011";	//팝업창에 출력될 페이지 URL
	var popOption = "width=1040, height=510, resizable=no, scrollbars=no, status=no;";    //팝업창 옵션(optoin)
		window.open(popUrl,"",popOption);
}
 */

function rsd1011Search() {
    openWindow("/popup/rsd1011", "rsd1010", {
        width:1040,
        height:510
    }, {
        callback:"rsd1010Callback"
		, custDvCd:"2"		// 투자자를 의미한다.
    });
}

/**
 * 상품코드를 설정한다.
 */
function rsd1010Callback(data) {
    // 상품코드를 리셋한다.
    //cuso4130Reset();

    $("#custNo").val(data.custMgntNo);
    $("#custNm").val(data.custNm);
}

function rsd1010Search(page) {
    if (page) {
        $("#page").val(page);
    }

    $("#rsd1010SearchForm").attr("action", "<c:url value="/resident/rsd1010Search"/>").submit();
}
</script>

</head>

<body>


<div class="rsd1010">
  	<div class="page-title">

    	<div class="row">
    		<div class="col-md-12">

    		<form class="form-inline form-label-left" name="rsd1010SearchForm" id="rsd1010SearchForm" method="post" role="form">
				<input type="hidden" id="page" name="page" value="<c:out value="${param.page}" default="1" />" />
				<input type="hidden" id="rows" name="rows" value="<c:out value="${param.rows}" default="5" />" />

				<div class="x_panel">
					<div class="x_title">
						<h2>입주자 관리 </h2>
                    	<div class="clearfix"></div>
					</div>

					<div class="x_content">
						<div class="form-group form-group-sm">
                        	<!--
                        	<label class="control-label" for="search">코드명 </label>
                        	 -->
                          	<input type="text" id="keyword" name="keyword" class="form-control" placeholder="">
                      	</div>

						<div class="form-group">
							<!--
							<a href="#" class="btn btn-primary btn-sm pull-right rsd1010-add-btn">추가</a>
							 -->
							<a href="#" class="btn btn-primary btn-sm pull-right rsd1010-search-btn">검색</a>
						</div>

						<br />
		                <div class="ln_solid"></div>

						<div class="col-md-12" style="margin-left:0px">
						    <div class="row">

								<table class="table table-striped">
								  	<thead>
								    	<tr>
								      		<th scope="col">번호</th>
								      		<th scope="col">입주자번호</th>
								      		<th scope="col">입주자명</th>
								      		<th scope="col">전화번호</th>
								      		<th scope="col">차량번호</th>
								      		<th scope="col">동/호</th>
								      		<th scope="col">거주시작일자</th>
								      		<th scope="col">사용여부</th>
								    	</tr>
								  	</thead>
								  	<tbody>
								  		<c:forEach var="item" items="${data}" varStatus="status">
									    	<tr>
									      		<th scope="row"><c:out value="${item.cdIdNm}"/></th>
									      		<td><c:out value="${item.cdVldVal}"/></td>
									      		<td><c:out value="${item.cdNm}"/></td>
									      		<td><c:out value="${item.apclStrDt}"/></td>
									      		<td><c:out value="${item.apclEndDt}"/></td>
									      		<td><c:out value="${item.sortOrdr}"/></td>
									      		<td><c:out value="${item.sortOrdr}"/></td>
									      		<td><c:out value="${item.sortOrdr}"/></td>
									    	</tr>
								  		</c:forEach>
										<c:if test="${empty data}">
					                        <tr>
					                            <td colspan="8" style="text-align:center;">검색된 자료가 없습니다.</td>
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

				        <form action="/resident/rsd1010/insert" method="post">
							<table class="table table-striped">
							    <tbody>
						            <tr>
						                <td><span class="pull-right">입주자번호</span></td>
						                <td><input type="text" placeholder="자동생성" name="" id="" class="form-control" disabled="disabled"/></td>
						                <td><span class="pull-right">입주자명</span></td>
						                <td><input type="text" placeholder="" name="mvnManNm" id="mvnManNm" class="form-control" required="required"/></td>
						                <td><span class="pull-right">전화번호</span></td>
						                <td><input type="text" placeholder="" name="telno" id="telno" class="form-control" required="required" data-bind="value:replyNumber" onKeyUp="return isPhoneNumber(event)"/></td>
						                <td><span class="pull-right">차량번호</span></td>
						                <td><input type="text" placeholder="" name="vhcleNo" id="vhcleNo" class="form-control" /></td>
						            </tr>
						            <tr>
						                <td><span class="pull-right">동/호</span></td>
						                <td>
											<div class="input-group">
												<input type="text" placeholder="" name="dongHo" id="dongHo" class="form-control" required="required"/>
												<input type="hidden" name="gnrSn" id="gnrSn" />
												<span class="input-group-btn">
													<button type="button" class="btn btn-primary rsd1010-add-btn">추가</button>
												</span>
											</div>
						                </td>
						                <td><span class="pull-right">거주시작일자</span></td>
						                <td>
											<div class='input-group date' id='resideBeginDtDatepicker'>
												<input type='text' name="resideBeginDt" id="resideBeginDt" class="form-control" value="${resideBeginDt}"  />
												<span class="input-group-addon">
													 <span class="glyphicon glyphicon-calendar"></span>
												</span>
											</div>
						                </td>
						                <td><span class="pull-right">사용여부</span></td>
						                <td>
											<ui:select iD="useYn" name="useYn" value="${useYn}" clazz="form-control" key="useYn" defaultValue="선택" required="required"/>
										</td>
										<td colspan="2">
											&nbsp;
										</td>
									</tr>
									<tr>
						                <td colspan="8">
		                          			<button type="submit" class="btn btn-success btn-add pull-right">등록</button>
		                          			<button type="button" class="btn btn-primary btn-cancel pull-right">취소</button>
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