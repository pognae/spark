<html>
<head>
<meta name="selection" content="bsc1020"/>
<style>
table {
	font-size:98%
}
</style>


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
    //  텍스트필드에 디폴트 값을 설정한다.
}

function bindMask() {
    // 적용시작일자 텍스트필드에 날짜 마스크를 바인딩한다.
    //$("#bascDt").setMask({ mask:"9999-19-39" });

    // 적용시작일자 텍스트필드에 날짜 선택기를 바인딩한다.
    //$("#bascDt").datepicker();
}

/**
 * 이벤트를 바인딩한다.
 */
function bindEvent() {
    $(".bsc1020-search-btn").each(function(index, element) {
        $(this).bind("click", function(event) {
        	bsc1020Search();
            return false;
        });
    });

    $(".bsc1020-add-btn").click(function(event) {
    	addRow();
    });
}

function bsc1020Search(page) {
    if (page) {
        $("#page").val(page);
    }
    $("#bsc1020SearchForm").attr("action", "<c:url value="/basic/bsc1020Search"/>").submit();
}
/*
function cuso0020Search() {
    openWindow("/popup/cus/cusLnd/cusp0001", "cusp0001", {
        width:1050,
        height:350
    }, {
        callback:"cusp0001Callback"
        , custDvCd:"2"
    });
}
 */

</script>

</head>

<body>
<form class="form-inline" name="bsc1020SearchForm" id="bsc1020SearchForm" method="post" role="form">
<input type="hidden" id="page" name="page" value="<c:out value="${param.page}" default="1" />" />
<input type="hidden" id="rows" name="rows" value="<c:out value="${param.rows}" default="5" />" />

<div class="bsc1020">
  	<div class="page-title">

    	<div class="row">
    		<div class="col-md-12">
				<div class="x_panel">
					<div class="x_title">
						<h2>거래처관리 </h2>
                    	<div class="clearfix"></div>
					</div>

					<div class="x_content">
						<div class="form-group">
                        	<label class="control-label" for="search">코드명 </label>
                          	<input type="text" id="search" class="form-control" placeholder="">
                      	</div>

						<div class="form-group">
							<a href="#" class="btn btn-primary btn-sm pull-right bsc1020-add-btn">추가</a>
							<a href="#" class="btn btn-primary btn-sm pull-right bsc1020-search-btn">검색</a>
						</div>

						<div class="col-md-12" style="margin-left:0px">
						    <div class="row">

								<table class="table table-striped">
								  	<thead>
								    	<tr>
								      		<th scope="col">거래처코드</th>
								      		<th scope="col">거래처명</th>
								      		<th scope="col">전화번호</th>
								      		<th scope="col">사업자등록번호</th>
								      		<th scope="col">사업자번호</th>
								      		<th scope="col">동</th>
								      		<th scope="col">호</th>
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
									      		<td><c:out value="${item.useYn}"/></td>
									      		<td><c:out value="${item.useYn}"/></td>
									    	</tr>
								  		</c:forEach>
										<c:if test="${empty data}">
					                        <tr>
					                            <td colspan="7" style="text-align: center;">검색된 자료가 없습니다.</td>
					                        </tr>
				                        </c:if>
								  	</tbody>
								</table>

						    </div>
						</div>

					</div>
				</div>
    		</div>
    	</div>
    </div>
</div>
</form>
</body>
</html>