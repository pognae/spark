<html>
<head>
<meta name="selection" content="bsc1030"/>

<script type="text/javascript">
var params = {};

$(function() {
	initComp();
	// 마스크를 바인딩한다.
    bindMask();
    // 이벤트를 바인딩한다.
    bindEvent();

    //alert("${apclStrDt}");
    /*
    console.log("${apclStrDt}");
    console.log("${param.apclStrDt}");
    console.log("${apclEndDt}");
    console.log("${param.apclEndDt}");
    */
});

function initComp() {
    // 적용시작일자 텍스트필드에 날짜 마스크를 바인딩한다.
    $("#apclStrDt").val("${apclStrDt}");
    $("#apclEndDt").val("${apclEndDt}");
}

function bindMask() {
    //$("#bascDt").setMask({ mask:"9999-19-39" });

    // 적용시작일자 텍스트필드에 날짜 선택기를 바인딩한다.
	$('#apclStrDtDatepicker').datetimepicker({
		format: 'YYYY-MM-DD'
	});
	//$('#apclStrDt').val("${apclStrDt}");

	$('#apclEndDtDatepicker').datetimepicker({
		format: 'YYYY-MM-DD'
	});
}

/**
 * 이벤트를 바인딩한다.
 */
function bindEvent() {
    $(".bsc1030-search-btn").each(function(index, element) {
        $(this).bind("click", function(event) {
        	bsc1030Search();
            return false;
        });
    });
}

function bsc1030Search(page) {
    if (page) {
        $("#page").val(page);
    }

    $("#bsc1030SearchForm").attr("action", "<c:url value="/basic/bsc1030Search"/>").submit();
}
</script>

</head>

<body>


<div class="bsc1030">
  	<div class="page-title">

    	<div class="row">
    		<div class="col-md-12">

    		<form class="form-inline form-label-left" name="bsc1030SearchForm" id="bsc1030SearchForm" method="post" role="form">
				<input type="hidden" id="page" name="page" value="<c:out value="${param.page}" default="1" />" />
				<input type="hidden" id="rows" name="rows" value="<c:out value="${param.rows}" default="5" />" />

				<div class="x_panel">
					<div class="x_title">
						<h2>코드관리 </h2>
                    	<div class="clearfix"></div>
					</div>

					<div class="x_content">
						<div class="form-group form-group-sm">
                        	<label class="control-label" for="search">코드명 </label>
                          	<input type="text" id="search" class="form-control" placeholder="">
                      	</div>

						<div class="form-group">
							<!--
							<a href="#" class="btn btn-primary btn-sm pull-right bsc1030-add-btn">추가</a>
							 -->
							<a href="#" class="btn btn-primary btn-sm pull-right bsc1030-search-btn">검색</a>
						</div>

						<br />
		                <div class="ln_solid"></div>

						<div class="col-md-12" style="margin-left:0px">
						    <div class="row">

								<table class="table table-striped">
								  	<thead>
								    	<tr>
								      		<th scope="col">기준코드</th>
								      		<th scope="col">코드</th>
								      		<th scope="col">코드명</th>
								      		<th scope="col">적용시작일</th>
								      		<th scope="col">적용종료일</th>
								      		<th scope="col">정렬순서</th>
								      		<th scope="col">적용여부</th>
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
									      		<td><c:out value="${item.useYn}"/></td>
									    	</tr>
								  		</c:forEach>
										<c:if test="${empty data}">
					                        <tr>
					                            <td colspan="7" style="text-align:center;">검색된 자료가 없습니다.</td>
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

				        <form action="/basic/bsc1030/insert" method="post">
							<table class="table table-striped">
							    <tbody>
						            <tr>
						                <td><span class="pull-right">기준코드</span></td>
						                <td><input type="text" placeholder="" name="cdIdNm" class="form-control" required="required"/></td>
						                <td><span class="pull-right">코드</span></td>
						                <td><input type="text" placeholder="" name="cdVldVal" class="form-control" required="required"/></td>
						                <td><span class="pull-right">코드명</span></td>
						                <td><input type="text" placeholder="" name="cdNm" class="form-control" required="required"/></td>
						                <td><span class="pull-right">적용시작일</span></td>
						                <td>
											<div class='input-group date' id='apclStrDtDatepicker'>
												<input type='text' name="apclStrDt" id="apclStrDt" class="form-control" value="${apclStrDt}"/>
												<span class="input-group-addon">
													 <span class="glyphicon glyphicon-calendar"></span>
												</span>
											</div>
						                </td>
						            </tr>
						            <tr>
						                <td><span class="pull-right">적용종료일</span></td>
						                <td>
											<div class='input-group date' id='apclEndDtDatepicker'>
												<input type='text' name="apclEndDt" id="apclEndDt" class="form-control" value="${apclEndDt}" />
												<span class="input-group-addon">
													 <span class="glyphicon glyphicon-calendar"></span>
												</span>
											</div>
						                </td>
						                <td><span class="pull-right">정렬순서</span></td>
						                <td><input type="text" placeholder="" name="sortOrdr" class="form-control" value=""/></td>
						                <td><span class="pull-right">적용여부</span></td>
						                <td>
											<ui:select iD="useYn" name="useYn" value="${useYn}" clazz="form-control" key="useYn" defaultValue="선택" required="required"/>
										</td>
						                <td colspan="2">
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