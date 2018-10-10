<html>
<head>
<meta name="selection" content="rsd1020"/>

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
    $(".rsd1020-search-btn").each(function(index, element) {
        $(this).bind("click", function(event) {
        	rsd1020Search();
            return false;
        });
    });

    $(".rsd1020-add-btn").click(function(event) {
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

function rsd1020Search(page) {
    if (page) {
        $("#page").val(page);
    }

    $("#rsd1020SearchForm").attr("action", "<c:url value="/resident/rsd1020Search"/>").submit();
}
</script>

</head>

<body>


<div class="rsd1020">
  	<div class="page-title">

    	<div class="row">
    		<div class="col-md-12">

    		<form class="form-inline form-label-left" name="rsd1020SearchForm" id="rsd1020SearchForm" method="post" role="form">
				<input type="hidden" id="page" name="page" value="<c:out value="${param.page}" default="1" />" />
				<input type="hidden" id="rows" name="rows" value="<c:out value="${param.rows}" default="5" />" />

				<div class="x_panel">
					<div class="x_title">
						<h2>세대기본관리 </h2>
                    	<div class="clearfix"></div>
					</div>

					<div class="x_content">
						<div class="form-group form-group-sm">
                        	<!--
                        	<label class="control-label" for="search"> </label>
                        	 -->
                          	<input type="text" id="keyword" name="keyword" class="form-control" placeholder="">
                      	</div>

						<div class="form-group">
							<!--
							<a href="#" class="btn btn-primary btn-sm pull-right rsd1020-add-btn">추가</a>
							 -->
							<a href="#" class="btn btn-primary btn-sm pull-right rsd1020-search-btn">검색</a>
						</div>

						<br />
		                <div class="ln_solid"></div>

						<div class="col-md-12" style="margin-left:0px">
						    <div class="row">

								<table class="table table-striped" id="tbl">
								  	<thead>
								    	<tr>
								      		<th scope="col">일련번호</th>
								      		<th scope="col">동</th>
								      		<th scope="col">호</th>
								      		<th scope="col">출력 동</th>
								      		<th scope="col">출력 호</th>
								      		<th scope="col">면적</th>
								      		<th scope="col">입주자</th>
								      		<th scope="col">사용여부</th>
								    	</tr>
								  	</thead>
								  	<tbody>
								  		<c:forEach var="item" items="${data}" varStatus="status">
									    	<tr>
									      		<th scope="row"><c:out value="${item.gnrSn}"/></th>
									      		<td><c:out value="${item.dong}"/></td>
									      		<td><c:out value="${item.ho}"/></td>
									      		<td><c:out value="${item.outptDong}"/></td>
									      		<td><c:out value="${item.outptHo}"/></td>
									      		<td><c:out value="${item.ar}"/></td>
									      		<td><c:out value="${item.mvnManSn}"/></td>
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

				        <form action="/resident/rsd1020/insert" method="post">
							<table class="table table-striped">
							    <tbody>
						            <tr>
						                <td><span class="pull-right">동</span></td>
						                <td><input type="text" placeholder="" name="dong" id="dong" class="form-control" required="required"/></td>
						                <td><span class="pull-right">호</span></td>
						                <td><input type="text" placeholder="" name="ho" id="ho" class="form-control" required="required"/></td>
						                <td><span class="pull-right">출력 동</span></td>
						                <td><input type="text" placeholder="" name="outptDong" id="outptDong" class="form-control"/></td>
						                <td><span class="pull-right">출력 호</span></td>
						                <td><input type="text" placeholder="" name="outptHo" id="outptHo" class="form-control" /></td>
						            </tr>
						            <tr>
						                <td><span class="pull-right">면적</span></td>
						                <td><input type="text" placeholder="" name="ar" id="ar" class="form-control" /></td>
						                <!--
						                <td><span class="pull-right">입주자</span></td>
						                <td>
											<div class="input-group">
												<input type="text" placeholder="" name="mvnManSn" id="mvnManSn" class="form-control"/>
												<span class="input-group-btn">
													<button type="button" class="btn btn-primary rsd1010-add-btn">추가</button>
												</span>
											</div>
						                </td>
						                 -->
						                <td><span class="pull-right">사용여부</span></td>
						                <td>
											<ui:select iD="useYn" name="useYn" value="${useYn}" clazz="form-control" key="useYn" defaultValue="선택" required="required"/>
										</td>
										<td colspan="4">
											<input type="hidden" name="gnrSn" id="gnrSn" value=""/>
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