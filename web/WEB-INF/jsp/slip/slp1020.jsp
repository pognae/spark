<html>
<head>
<meta name="selection" content="slp1020"/>

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
	$("#applcYm").val("${applcYm}");
}

function bindMask() {
	$('#applcYm').datetimepicker({
		format: 'YYYY-MM'
	});
}

/**
 * 이벤트를 바인딩한다.
 */
function bindEvent() {
    $(".slp1020-search-btn").each(function(index, element) {
        $(this).bind("click", function(event) {
        	slp1020Search();
            return false;
        });
    });

    $(".slp1020-add-btn").click(function(event) {
    	popup1();
    });

    // 테이블의 Row 클릭시 값 가져오기
    $("#tbl tr").click(function() {
        var str = ""
        var tdArr = new Array();    // 배열 선언

        // 현재 클릭된 Row(<tr>)
        var tr = $(this);
        var td = tr.children();

        // 반복문을 이용해서 배열에 값을 담아 사용할 수 도 있다.
        td.each(function(i){
            tdArr.push(td.eq(i).text());
        });

        // td.eq(index)를 통해 값을 가져올 수도 있다.
        var gnrSn = td.eq(0).text();
        var dong = td.eq(1).text();
        var ho = td.eq(2).text();
        var outptDong = td.eq(3).text();
        var outptHo = td.eq(4).text();
        var ar = td.eq(5).text();
        var mvnManSn = td.eq(6).text();
        var useYn = td.eq(7).text();
        if(useYn == '사용') {
        	useYn = 'Y';
        } else {
        	useYn = 'N';
        }

        $("#gnrSn").val(gnrSn);
        $("#dong").val(dong);
        $("#ho").val(ho);
        $("#outptDong").val(outptDong);
        $("#outptHo").val(outptHo);
        $("#ar").val(ar);
        $("#useYn").val(useYn);
    });

}

function slp1020Search(page) {
    if (page) {
        $("#page").val(page);
    }

    $("#slp1020SearchForm").attr("action", "<c:url value="/slip/slp1020Search"/>").submit();
}
</script>

</head>

<body>


<div class="slp1020">
  	<div class="page-title">

    	<div class="row">
    		<div class="col-md-12">

    		<form class="form-inline form-label-left" name="slp1020SearchForm" id="slp1020SearchForm" method="post" role="form">
				<input type="hidden" id="page" name="page" value="<c:out value="${param.page}" default="1" />" />
				<input type="hidden" id="rows" name="rows" value="<c:out value="${param.rows}" default="5" />" />

				<div class="x_panel">
					<div class="x_title">
						<h2>실별 부과내역 </h2>
                    	<div class="clearfix"></div>
					</div>

					<div class="x_content">
                       	<label class="control-label" for="search">적용년월 </label>
						<div class='input-group date' id='applcYmDatepicker'>
							<input type='text' name="applcYm" id="applcYm" class="form-control" value="${applcYm}"/>
							<span class="input-group-addon">
								 <span class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>

						<div class="form-group">
							<a href="#" class="btn btn-primary btn-sm pull-right slp1020-allPrint-btn">전체출력</a>
							<a href="#" class="btn btn-primary btn-sm pull-right slp1020-print-btn">출력</a>
							<a href="#" class="btn btn-primary btn-sm pull-right slp1020-search-btn">검색</a>
						</div>

						<br />
		                <div class="ln_solid"></div>

						<div class="col-md-12" style="margin-left:0px">
						    <div class="row">

								<table class="table table-striped" id="tbl">
								  	<thead>
								    	<tr>
								      		<th scope="col">출력 동</th>
								      		<th scope="col">출력 호</th>
								      		<th scope="col">면적</th>
								      		<th scope="col">일반관리비</th>
								      		<th scope="col">전산처리비</th>
								      		<th scope="col">입주자대표회의</th>
								      		<th scope="col">청소비</th>
								      		<th scope="col">소방관리대행료</th>
								      		<th scope="col">승강기유지보수비</th>
								      		<th scope="col">전기안전대행료</th>
								      		<th scope="col">공동전기료</th>
								      		<th scope="col">공동수도료</th>
								      		<th scope="col">예비비</th>
								      		<th scope="col">화재보험료</th>
								      		<th scope="col">장기수선충당금</th>
								      		<th scope="col">청구금액</th>
								    	</tr>
								  	</thead>
								  	<tbody>
								  		<c:forEach var="item" items="${data}" varStatus="status">
									    	<tr>
									      		<th scope="row"><c:out value="${item.outptDong}"/></th>
									      		<td><c:out value="${item.outptHo}"/></td>
									      		<td><c:out value="${item.ar}"/></td>
									      		<td><c:out value="${item.gnrlManageCst}"/></td>
									      		<td><c:out value="${item.cmptProcessCst}"/></td>
									      		<td><c:out value="${item.mvnManReprsntMtgCst}"/></td>
									      		<td><c:out value="${item.clnCst}"/></td>
									      		<td><c:out value="${item.fgtManageVrscCst}"/></td>
									      		<td><c:out value="${item.elvtrMntnceCst}"/></td>
									      		<td><c:out value="${item.elctySafeVrscCst}"/></td>
									      		<td><c:out value="${item.copertnElctyCst}"/></td>
									      		<td><c:out value="${item.copertnCptlCst}"/></td>
									      		<td><c:out value="${item.rsvfndCst}"/></td>
									      		<td><c:out value="${item.fireIrncfCst}"/></td>
									      		<td><c:out value="${item.lngtrRpairsRsvmneyCst}"/></td>
									      		<td><c:out value="${item.smAmount}"/></td>
									    	</tr>
								  		</c:forEach>
										<c:if test="${empty data}">
					                        <tr>
					                            <td colspan="16" style="text-align:center;">검색된 자료가 없습니다.</td>
					                        </tr>
				                        </c:if>
								  	</tbody>
								</table>

						    </div>
						</div>

					</div>
				</div>
				</form>

    		</div>
    	</div>
    </div>
</div>

</body>
</html>