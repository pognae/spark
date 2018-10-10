<html>
<head>
<meta name="selection" content="bsc1010"/>

<script type="text/javascript">
var params = {};

$(function() {
	initComp();
	// 마스크를 바인딩한다.
    bindMask();
    // 이벤트를 바인딩한다.
    bindEvent();

    //alert("${apclStrDt}");
    //$("bsc1010SearchForm").validate();
});

function initComp() {
    // 적용시작일자 텍스트필드에 날짜 마스크를 바인딩한다.
    //$("#apclStrDt").val("${apclStrDt}");
    //$("#apclEndDt").val("${apclEndDt}");
}

function bindMask() {
    //$("#bascDt").setMask({ mask:"9999-19-39" });

    // 적용시작일자 텍스트필드에 날짜 선택기를 바인딩한다.
    /*
	$('#apclStrDtDatepicker').datetimepicker({
		format: 'YYYY-MM-DD'
	});
	//$('#apclStrDt').val("${apclStrDt}");

	$('#apclEndDtDatepicker').datetimepicker({
		format: 'YYYY-MM-DD'
	});
	 */
}

/**
 * 이벤트를 바인딩한다.
 */
function bindEvent() {
    $(".bsc1010-search-btn").each(function(index, element) {
        $(this).bind("click", function(event) {
        	bsc1010Search();
            return false;
        });
    });
}

function bsc1010Search(page) {
    if (page) {
        $("#page").val(page);
    }

    $("#bsc1010SearchForm").attr("action", "<c:url value="/basic/bsc1010Search"/>").submit();
}
</script>

</head>

<body>


<div class="bsc1010">
  	<div class="page-title">

    	<div class="row">
    		<div class="col-md-12">

    		<form class="form-inline form-label-left" name="bsc1010SearchForm" id="bsc1010SearchForm" method="post" role="form">
				<input type="hidden" id="page" name="page" value="<c:out value="${param.page}" default="1" />" />
				<input type="hidden" id="rows" name="rows" value="<c:out value="${param.rows}" default="5" />" />

				<div class="x_panel">
					<div class="x_title">
						<h2>계정과목 관리 </h2>
                    	<div class="clearfix"></div>
					</div>

					<div class="x_content">
						<div class="form-group form-group-sm">
                        	<label class="control-label" for="search">코드명 </label>
                          	<input type="text" id="search" class="form-control" placeholder="">
                      	</div>

						<div class="form-group">
							<!--
							<a href="#" class="btn btn-primary btn-sm pull-right bsc1010-add-btn">추가</a>
							 -->
							<a href="#" class="btn btn-primary btn-sm pull-right bsc1010-search-btn">검색</a>
						</div>

						<br />
		                <div class="ln_solid"></div>

						<div class="col-md-12" style="margin-left:0px">
						    <div class="row">

								<table class="table table-striped">
								  	<thead>
								    	<tr>
								      		<th scope="col">그룹코드</th>
								      		<th scope="col">그룹코드명</th>
								      		<th scope="col">코드</th>
								      		<th scope="col">코드명</th>
								      		<th scope="col">정렬순서</th>
								      		<th scope="col">상위그룹코드</th>
								      		<th scope="col">상위코드</th>
								      		<th scope="col">사용여부</th>
								    	</tr>
								  	</thead>
								  	<tbody>
								  		<c:forEach var="item" items="${data}" varStatus="status">
									    	<tr>
									      		<th scope="row"><c:out value="${item.groupCodeId}"/></th>
									      		<td><c:out value="${item.groupCodeNm}"/></td>
									      		<td><c:out value="${item.codeId}"/></td>
									      		<td><c:out value="${item.codeNm}"/></td>
									      		<td><c:out value="${item.sortOrdr}"/></td>
									      		<td><c:out value="${item.upperGroupCodeId}"/></td>
									      		<td><c:out value="${item.upperCodeId}"/></td>
									      		<td><c:out value="${item.useYn}"/></td>
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

				        <form action="/basic/bsc1010/insert" method="post">
							<table class="table table-striped">
							    <tbody>
						            <tr>
						                <td><span class="pull-right">그룹코드</span></td>
						                <td><input type="text" placeholder="" name="cdIdNm" class="form-control" required="required"/></td>
						                <td><span class="pull-right">그룹코드명</span></td>
						                <td><input type="text" placeholder="" name="cdVldVal" class="form-control" required="required"/></td>
						                <td><span class="pull-right">코드</span></td>
						                <td><input type="text" placeholder="" name="cdNm" class="form-control" required="required"/></td>
						                <td><span class="pull-right">코드명</span></td>
						                <td><input type="text" placeholder="" name="cdNm" class="form-control" required="required"/></td>
						            </tr>
						            <tr>
						                <td><span class="pull-right">정렬순서</span></td>
						                <td><input type="text" placeholder="" name="sortOrdr" class="form-control" required="required"/></td>
						                <td><span class="pull-right">상위그룹코드</span></td>
						                <td><input type="text" placeholder="" name="sortOrdr" class="form-control" value=""/></td>
						                <td><span class="pull-right">상위코드</span></td>
						                <td><input type="text" placeholder="" name="sortOrdr" class="form-control" value=""/></td>
						                <td><span class="pull-right">사용여부</span></td>
						                <td>
											<ui:select iD="useYn" name="useYn" value="${useYn}" clazz="form-control" key="useYn" defaultValue="선택" required="required"/>
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