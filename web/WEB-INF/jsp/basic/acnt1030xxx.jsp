<html>
<head>
<meta name="selection" content="acco4050"/>

<script type="text/javascript">
$(function() {
	initComp();
	// 마스크를 바인딩한다.
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

function initComp() {
    //  텍스트필드에 디폴트 값을 설정한다.
    if (com.wow.fn.isEmpty($("#bascDt").val())) {
        $("#bascDt").val(com.wow.fn.formatDate(new Date()));
    }
}

function bindMask() {
    // 적용시작일자 텍스트필드에 날짜 마스크를 바인딩한다.
    $("#bascDt").setMask({ mask:"9999-19-39" });

    // 적용시작일자 텍스트필드에 날짜 선택기를 바인딩한다.
    $("#bascDt").datepicker();
}

/**
 * 이벤트를 바인딩한다.
 */
function bindEvent() {
    $(".acco4050-update-button").click(function(event) {
    	var gridList = grid.getRowList();

    	if(dbtHdlTtlAmt != crtHdlTtlAmt) {
            alert("차/대변의 합계 금액이 일치하여 합니다.");
            return;
    	}

    	if(gridList.length == 0) {
    		alert("등록할 전표를 입력하세요.");
    		return;
    	}

    });

}
</script>
</head>

<body>
<form id="frm" name="frm" method="post">
<input type="hidden" id="page" name="page" value="<c:out value="${param.page}" default="1" />" />
<input type="hidden" id="rows" name="rows" value="<c:out value="${param.rows}" default="5" />" />

<div class="">
  	<div class="page-title">
  		<!--
    	<div class="title_left">
      		<h3>코드관리</h3>
    	</div>

    	<div class="title_right">
			<div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="Search for...">
                    <span class="input-group-btn">
						<button class="btn btn-default" type="button">Go!</button>
                    </span>
				</div>
			</div>
		</div>

    	<div class="clearfix"></div>
 		-->

    	<div class="row">
    		<div class="col-md-12">
				<div class="x_panel">
					<div class="x_title">
						<h2>코드관리 </h2>
						<!--
                    	<ul class="nav navbar-right panel_toolbox">
                      		<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                      		<li class="dropdown">
                        		<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                        		<ul class="dropdown-menu" role="menu">
                          			<li><a href="#">Settings 1</a></li>
                          			<li><a href="#">Settings 2</a></li>
                        		</ul>
                      		</li>
                      		<li><a class="close-link"><i class="fa fa-close"></i></a></li>
                    	</ul>
                    	 -->
                    	<div class="clearfix"></div>
					</div>


					<div class="x_content">

						<div style="margin-left:20px">
						    <table id="jqGrid"></table>
						    <div id="jqGridPager"></div>
						</div>
<script>
	//console.log(document.body.scrollWidth);
	var width = document.body.scrollWidth - (document.body.scrollWidth / 4.6);
	console.log(width);
	//$.jgrid.defaults.width = 780;
	$.jgrid.defaults.width = width;
</script>
<script type="text/javascript">
        $(document).ready(function () {
            $("#jqGrid").jqGrid({
                url: 'http://trirand.com/blog/phpjqgrid/examples/jsonp/getjsonp.php?callback=?&qwery=longorders',
                mtype: "GET",
				styleUI : 'Bootstrap',
                datatype: "jsonp",
                colModel: [
                    { label: 'OrderID', name: 'OrderID', key: true, width: 75 },
                    { label: 'Customer ID', name: 'CustomerID', width: 150 },
                    { label: 'Order Date', name: 'OrderDate', width: 150 },
                    { label: 'Freight', name: 'Freight', width: 150 },
                    { label:'Ship Name', name: 'ShipName', width: 150 }
                ],
				viewrecords: true,
                height: 250,
                rowNum: 20,
                pager: "#jqGridPager"
            });
        });
</script>

					</div>
				</div>
    		</div>
    	</div>
    </div>
</div>

</form>
</body>
</html>