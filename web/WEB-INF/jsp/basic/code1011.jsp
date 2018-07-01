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

						<div class="col-md-4 col-sm-4">
							<div class="form-group">
	                        	<label class="control-label col-md-4 col-sm-4 col-xs-12">코드명</label>
								<div class="col-md-8 col-sm-8 col-xs-12">
	                          		<input type="text" id="search" class="form-control" placeholder="코드명">
	                        	</div>
	                      	</div>
                      	</div>

						<div class="col-md-4 col-sm-4">
							<!--
							<div class="form-group">
	                        	<label class="control-label col-md-3 col-sm-3 col-xs-12">Input</label>
								<div class="col-md-9 col-sm-9 col-xs-12">
	                          		<input type="text" class="form-control" placeholder="Default Input">
	                        	</div>
	                      	</div>
	                      	 -->
                      	</div>

						<div class="col-md-4 col-sm-4">
							<a href="#" class="btn btn-primary pull-right">검색</a>
						</div>

						<div class="col-md-12" style="margin-left:0px">
						    <div class="row">
						    	<div>
								    <table id="jqGrid"></table>
								    <div id="jqGridPager"></div>
						    	</div>
						    </div>
						</div>

<script>
	//console.log(document.body.scrollWidth);
	var width = document.body.scrollWidth - 341;
	//console.log(width);
	//$.jgrid.defaults.width = 780;
	$.jgrid.defaults.width = width;
	$.jgrid.defaults.responsive = true;
	//$.jgrid.defaults.styleUI = 'Bootstrap';
</script>

<script type="text/javascript">
$(document).ready(function () {
	var params = {};
	params.regDtm = '20180614';

/*
    $.extend($.jgrid.defaults, {
        datatype: 'json',
        jsonReader : {
            repeatitems:false,
            total: function(result) {
                //Total number of pages
                return Math.ceil(result.total / result.max);
            },
            records: function(result) {
                //Total number of records
                return result.total;
            }
        },
        prmNames: {rows: 'max', search: null},
        //height: 'auto',
        viewrecords: true,
        rowList: [10, 20, 50, 100],
        altRows: true,
        loadError: function(xhr, status, error) {
            console.log(error);
        }
    });
 */

    $("#jqGrid").jqGrid({
    	/*
        url: '<c:url value="/basic/code1010Search"/>',
        mtype: "POST",
        datatype: "json",
		//contentType: "application/json",
		ajaxGridOptions: { contentType: "application/json; charset=UTF-8" },
		//ajaxRowOptions: { contentType: "application/json; charset=UTF-8", async: true },
		//ajaxSelectOptions: { contentType: "application/json; charset=UTF-8", dataType: "JSON" },
        //data: JSON.stringify(params),
    	//headers: {'Accept':'application/json', 'Content-Type':'application/json; charset=utf-8'},
    	postData: {
		    search: function() { return $("#search").val(); },
		},
		 */
		datatype	:	"local",
        colModel: [
            { label: '기준코드명', name: 'cdIdNm', key: true, width: 150 },
            { label: '코드명', name: 'cdNm', width: 150 },
            { label: '코드', name: 'cdVldVal', key: true, width: 150 },
            { label: '적용시작일자', name: 'apclStrDt', width: 150 },
            { label: '적용종료일자', name: 'apclEndDt', width: 150 },
            { label: '사용여부', name: 'useYn', width: 75 }
        ],
		viewrecords: true,
        height: 500,//'auto',
        rowNum: 20,
        caption:"코드관리"
        //,pager: "#jqGridPager"
    });

 	fetchGridData();
 	function fetchGridData() {
		jQuery.ajax({
		    headers: {'Accept':'application/json', 'Content-Type':'application/json; charset=utf-8'},
		    type: 'POST',
		    url: '<c:url value="/basic/code1010Search"/>',
		    dataType: 'json',
		    data: JSON.stringify(params),
		    success:function(result) {
		    	console.log(JSON.stringify(result.data));
		    	alert("success");
		    	//$("#jqGrid").jqGrid('setGridParam', {data:result.data.rows});
		    },
		    error:function(e) {
		    	console.log("AAAAAAAAAAAAAA");
		    	callBackError(e);
		    }
		});
 	}




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